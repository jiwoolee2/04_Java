package list.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import list.dto.BookDTO;

/**
 * 도서 관리 프로그램 기능 제공 클래스
 */
public class BookService {

	
	
	private List<BookDTO> bookList = new ArrayList<BookDTO>();
	
	
	
	// 기본 생성자
	public BookService() {
		
		// 샘플 데이터 추가
		bookList.add(new BookDTO("소년이 운다", "한강" , 15000 , "창비"));
		bookList.add(new BookDTO("초역 부처의 말", "코이케 류노스케" , 17800 , "포레스트북스"));
		bookList.add(new BookDTO("채식주의자", "한강" , 15000 , "창비"));
		bookList.add(new BookDTO("작별하지 않는다", "한강" , 16800 , "문학동네"));
		bookList.add(new BookDTO("모순", "양귀자" , 13000 , "쓰다"));
	}

	
	
	// 타입이 List<BookDTO>인 bookList(리스트)를 얻을 수 있는 getter함수
	public List<BookDTO> getBookList() {
		return bookList;
	}

	
	
	/** 
	 * 전달 받은 index 번째 bookList 요소 반환
	 * 단, 범위 초과 시 null(참조하는 요소가 없다) 반환
	 * @param index
	 * @return
	 */
	public BookDTO selectIndex(int index) {
		
		// 입력한 index가 범위를 벗어났을 때 null 반환
		if(checkIndex(index) == false) return null;
		return bookList.get(index);
	}
	
	
	
	/**
	 * 전달 받은 index가 정상 범위인지 확인
	 * @param index
	 * @return
	 */
	public boolean checkIndex(int index) {
		
		// 입력한 index가 범위를 벗어났을 때 false반환
		if(index<0 || index>=bookList.size()) {
			return false;
		}
		
		// 정상 범위일 경우 true 반환
		return true;
	}
	
	
	
	/**
	 * 전달 받은 book을 bookList에 추가
	 * 단, 책 정보가 모두 일치하는 책이 있다면 추가 X
	 * @param book
	 * @return 추가 O : true,  추가 X : false
	 */
	public boolean addBook(BookDTO book) {
		
		// 책 정보 비교 방법 1) 필드 값 하나씩 꺼내서 비교
		// 책 정보 비교 방법 2) equals() 오버라이딩 후 이용
		// 책 정보 비교 방법 3) equals() 오버라이딩 후 contains 이용
		
//		for(BookDTO b : bookList) {
//			if(b.equals(book)) return false;
//		}
		
		// BookDTO의 equals()를 오버라이딩 했기 때문에
		// List가 제공하는 contains() (포함하는지 확인) 사용 가능
		if(bookList.contains(book)) return false;
		
		return bookList.add(book); // 컬렉션은 크기 제한이 없어서 무조건 추가 성공! (true)
		
	}
	
	
	/**
	 * 전달 받은 index번째 요소의 가격을 newPrice로 수정
	 * @param index
	 * @param newPrice
	 * @return "[책제목] 가격이 [이전가격]->[수정된 가격]으로 수정되었습니다"
	 *  를 출력
	 */
	public String modifyBookPrice22222(int index, int newPrice) {
		
		BookDTO target = bookList.get(index); // index 번째 요소 얻어오기
		
		int oldPrice = target.getPrice(); 		// 이전 가격 저장
		
		target.setPrice(newPrice); 						// 새 가격으로 수정
		
		
		return String.format("[%s] 가격이 [%d]->[%d]으로 수정되었습니다",
				target.getTitle(),oldPrice,newPrice);
	}
	
	
	/**
	 * index번째 BookDTO 제거
	 * 단, 없으면 null 반환
	 * 제거 성공하면 제거된 BookDTO 반환
	 * @param index
	 * @return index
	 */
	public BookDTO removeBook(int index) {
		
		if(checkIndex(index)==false) return null;
		
		// bokkLIst에서 index 번째 요소를 제거
		// == index 번째 요소를 뽑아냄
		return bookList.remove(index);
		}
	
	
	
	/**
	 * 제목이 일치하는 책의 정보 반환
	 * @param title : 찾으려는 책 제목
	 * @return 책의 정보
	 */
	public BookDTO selectTitle(String title) {
		
		for(BookDTO book : bookList) {
			if(book.getTitle().equals(title)) {
				return book;
			}
		}
		return null;
	}
		
	
	
	/**
	 * 제목이 일치하는 책 제거후 책 정보 반환
	 * @param title : 제거하려는 책 제목
	 */
	public BookDTO removeBookTitle(String title) {
		int i = 0;
		for(BookDTO book : bookList) {
			if(book.getTitle().equals(title)) {
				bookList.remove(i);
				return book;
			}
			i +=1 ;
		}
		return null;
	}
	
	
	
	/**
	 * 입력받은 값과 출판사가 일치하는 책을 모두 찾아 리스트로 반환
	 * @param publisher
	 */
	public List selectPublisherAll(String publisher) {
		
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		for(BookDTO book : bookList) {
			if(book.getPublisher().equals(publisher)) {
				list.add(book);
			}
		}
		return list;
	}
	
	
	
	/**
	 * 입력받은 값과 같은 작가를 가지는 
	 * 책을 모두 찾아 리스트로 반환
	 * @param author
	 */
	public List selectAuthorAll(String author) {
		
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		for(BookDTO book : bookList) {
			if(book.getAuthor().equals(author)) {
				list.add(book);
			}
		}
		return list;
	}
	
	
	/**
	 * 입력받은 값이 제목, 저자에 포함된 모든 책 찾아서 리스트로 반환
	 * @param searchWord
	 * @return
	 */
	public List searchBook(String searchWord) {
		
		List<BookDTO> list = new ArrayList<BookDTO>();
		
		for(BookDTO book : bookList) {
			if(book.getAuthor().contains(searchWord) | book.getTitle().contains(searchWord)) {
				list.add(book);
			}
		}
		return list;
	}
	
	
	/**
	 * 제목 오름차순 정렬
	 */
	public void bookListSorting() {
		
		Collections.sort(bookList);
		// 원본 리스트가 정렬된 형태로 변경됨
		
	}
	
	
	
	
	
	
	
}
