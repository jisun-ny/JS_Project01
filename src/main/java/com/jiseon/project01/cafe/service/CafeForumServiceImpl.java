package com.jiseon.project01.cafe.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jiseon.project01.cafe.dao.CafeForumDao;
import com.jiseon.project01.cafe.dto.CafeForumDto;

@Service
public class CafeForumServiceImpl implements CafeForumService {
	
	@Autowired
	private CafeForumDao forumDao;

	@Override
	public void saveContent(CafeForumDto dto, HttpServletRequest request) {
		//업로드된 파일의 정보를 가지고 있는 MultipartFile객체의 참조값 얻어오기
		MultipartFile image = dto.getImage();
		//원본 파일명 -> 저장할 파일 이름 만들기 위해서 사용해줌
		String oriFileName = image.getOriginalFilename();
		//파일 크기 -> 다운로드가 없으므로 여기에선 필요 없다.
		long fileSize = image.getSize();
		
		// webapp/upload 폴더까지의 실제 경로(서버의 파일 시스템 상에서의 경로)
		String realPath = request.getServletContext().getRealPath("/resources/upload");
		//db에 저장할 저장할 파일의 상세경로
		String filePath = realPath + File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload = new File(filePath);
		if(!upload.exists()) {
			upload.mkdir(); //폴더 생성
		}
		//저장할 파일의 이름을 구성한다. -> 우리가 직접 구성해줘야한다.
		String saveFileName = System.currentTimeMillis() + oriFileName;
		
		try {
			//upload폴더에 파일을 저장한다.
			image.transferTo(new File(filePath + saveFileName));
			System.out.println();//임시 출력
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//dto에 업로드 된 파일 정보를 담는다.
		// -> 이때 parameter로 넘어온 dto에는 title, content, image가 들어있다.
		// 추가할 것 -> id, imagePath
		// -> num, regdate는 DB에 저장되면서 자동으로 들어간다.
		String id = (String)request.getSession().getAttribute("id");
		dto.setWriter(id);
		//사진 다운 기능이 없기 때문에 orgFileName, saveFileName, FileSize 저장할 필요 없다.
		//imagePath만 저장해주면 됨
		dto.setImagePath("/resources/upload/" + saveFileName);
		
		//CafeForumDao를 이용해서 DB에 저장하기
		forumDao.insert(dto);
		
		
				
		
	}

	@Override
	public void getList(HttpServletRequest request) {
		//[페이징 처리]
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT = 5;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT = 5;
		
		//보여줄 페이지의 번호를 일단 1이라고 초기값 설정
		int pageNum = 1;
		//페이지 번호가 파라미터로 전달되는지 읽어와본다,
		String strPageNum = request.getParameter("pageNum");
		//만일 페이지 번호가 파라미터로 넘어온다면
		if( strPageNum != null) {
			//숫자로 바꿔서 보여울 페이지 번호로 지정한다.
			pageNum = Integer.parseInt(strPageNum);
		}
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1)* PAGE_ROW_COUNT;
		//보여줄 페이지의 마지막 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;
		
		// CafeForumDto 객체에 startRowNum, endRowNum을 담는다.
				CafeForumDto dto = new CafeForumDto();
				dto.setStartRowNum(startRowNum);
				dto.setEndRowNum(endRowNum);

				
		//[검색 키워드 처리]
		//검색키워드를 사용할 수도 있고 안 사용할 수도 있다. ( 즉 파라미터로 값이 넘어올 수도, 안 넘어올 수도)
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		
		//만일 키워드가 넘어오지않는다면
		if(keyword == null ) {
			keyword="";
			condition="";
		}
		//특수 기호를 인코딩한 키워드를 미리 준비한다.
		String encodedK = URLEncoder.encode(keyword);
		
		
		
		//만일 검색 키워드가 넘어온다면
		if(! keyword.equals("")) {
			//검색 조건이 무엇이냐에 따라 분기하기			
			if(condition.equals("title_content")) { // 제목+내용 검색인 경우
				//검색 키워드를 CafeForumDto에 담아서 전달
				dto.setTitle(keyword);
				dto.setContent(keyword);
			} else if (condition.equals("title")) { //제목 검색
				dto.setTitle(keyword);
			} else if (condition.equals("writer")) { //작성자 검색
				dto.setWriter(keyword);
			}
		}
		//파일 목록을 select 해온다 (검색 키워드가 있는 경우 키워드에 부합하는 전체 글)
		List<CafeForumDto> list = forumDao.getList(dto);
		
		//전체 글의 갯수(검색 키워드가 있는 경우 키워드에 부합하는 전체 글의 갯수)
		int totalRow = forumDao.getCount(dto);
		
		//하단 시작 페이지 번호
		int startPageNum = 1 + ((pageNum- 1)/ PAGE_DISPLAY_COUNT) *PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		
		//전체 페이지의 갯수 구하기
		int totalPageCount = (int)Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지의 갯수보다 크게 계산되엇으면 잘못된 것이다.
		if(endPageNum > totalPageCount) {
			endPageNum = totalPageCount; //보정해준다
		}
		
		//응답에 필요한 데이터를 view page에 전달하기 위해 request scope에 담는다.
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("encodedK", encodedK);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("condition", condition);
		
		
	}

	@Override
	public void getDetail(HttpServletRequest request) {
		//자세히 보여줄 글 번호를 읽어온다
		int num = Integer.parseInt(request.getParameter("num"));
		//조회수 올리기
		forumDao.addViewCount(num);
		
		/*검색 키워드 관련 처리*/
		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		//만일 키워드가 넘어오지 않는다면
		if(keyword == null) {
			keyword = "";
			condition = "";
		}
		//cafeForumDto 객체를 생성해서
		CafeForumDto dto = new CafeForumDto();
		//자세히 보여줄 글 번호를 넣어준다.
		dto.setNum(num);
		//만일 검색어 키워드가 넘어온다면 
		if(! keyword.equals("")) {
			//검색 조건이 무엇이냐에 따라 분기
			if( condition.equals("title_content")) { //제목+내용 검색인 경우
				//검색 키워드를 CafeDto에 담아서 전달한다.
				dto.setTitle(keyword);
				dto.setContent(keyword);
			} else if (condition.equals("title")) { //제목 검색
				dto.setTitle(keyword);
			} else if (condition.equals("writer")) { //작성자 검색
				dto.setWriter(keyword);
			} 			
		}
		
		//글 하나의 정보를 얻어온다
		CafeForumDto resultDto = forumDao.getDataWKey(dto);
		//특수 기호를 인코딩한 키워드를 미리 준비한다.
		String encodedK = URLEncoder.encode(keyword);
		
		//requestScope에 글 하나의 정보 담기
		request.setAttribute("dto", resultDto);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
	}

	@Override
	public void updateContent(CafeForumDto dto, HttpServletRequest request) {
		//업로드된 파일의 정보를 가지고 있는 MultipartFile객체의 참조값 얻어오기
				MultipartFile image = dto.getImage();
				//원본 파일명 -> 저장할 파일 이름 만들기 위해서 사용해줌
				String oriFileName = image.getOriginalFilename();
				//파일 크기 -> 다운로드가 없으므로 여기에선 필요 없다.
				long fileSize = image.getSize();
				
				// webapp/upload 폴더까지의 실제 경로(서버의 파일 시스템 상에서의 경로)
				String realPath = request.getServletContext().getRealPath("/resources/upload");
				//db에 저장할 저장할 파일의 상세경로
				String filePath = realPath + File.separator;
				//디렉토리를 만들 파일 객체 생성
				File upload = new File(filePath);
				if(!upload.exists()) {
					upload.mkdir(); //폴더 생성
				}
				//저장할 파일의 이름을 구성한다. -> 우리가 직접 구성해줘야한다.
				String saveFileName = System.currentTimeMillis() + oriFileName;
				
				try {
					//upload폴더에 파일을 저장한다.
					image.transferTo(new File(filePath + saveFileName));
					System.out.println();//임시 출력
				}catch(Exception e){
					e.printStackTrace();
				}
				
				//dto에 업로드 된 파일 정보를 담는다.
				// -> 이때 parameter로 넘어온 dto에는 title, content, image가 들어있다.
				// 추가할 것 -> id, imagePath
				// -> num, regdate는 DB에 저장되면서 자동으로 들어간다.
				String id = (String)request.getSession().getAttribute("id");
				dto.setWriter(id);
				//사진 다운 기능이 없기 때문에 orgFileName, saveFileName, FileSize 저장할 필요 없다.
				//imagePath만 저장해주면 됨
				dto.setImagePath("/resources/upload/" + saveFileName);
				
				//CafeForumDao를 이용해서 DB에 저장하기
				forumDao.update(dto);
		
	}

	@Override
	public void deleteContent(int num, HttpServletRequest request) {
		//세션에서 로그인된 아이디를 읽어오기
		String id = (String)request.getSession().getAttribute("id");
		//주어진 num이용해서 정보 얻어오기
		CafeForumDto dto = forumDao.getData(num);
		/*//글 작성자와 로그인된 아이디가 다르다면
		if(! id.equals(dto.getWriter())) {
			//예외를 발생시켜서 삭제가 안되도록 한다,
			throw new NotDeleteException("남의 글을 지우지 마세요");
		}
		*/
		//글 삭제하기
		forumDao.delete(num);
	}

	// 글 수정 폼에 필요한 값을 HttpServletRequest에 담아주는 메소드
	@Override
	public void getData(HttpServletRequest request) {
		//수정할 글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		//수정할 글의 정보를 얻어와서
		CafeForumDto dto = forumDao.getData(num);
		//request에 담아준다.
		request.setAttribute("dto", dto);
		
	}

}
