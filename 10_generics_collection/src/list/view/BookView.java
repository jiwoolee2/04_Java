package list.view;

import java.util.List;
import java.util.Scanner;

import list.dto.BookDTO;
import list.service.BookService;

/** 
 *  도서 관리 프로그램 입/출력 담당 클래스(User Interface)
 *  
 */
public class BookView {

	private Scanner sc = new Scanner(System.in);
	private BookService service = new BookService();
	
	
	/** 
	 *  프로그램 메인 메뉴
	 */
	public void displayMenu() {
		
		int input = 0; // 메뉴 번호를 저장할 변수
		
		do {
			System.out.println("\n***** 도서 관리 프로그램 *****\n");
			System.out.println("1. 전체 조회");
			System.out.println("2. index 번호로 조회");
			System.out.println("3. 책 추가하기");
			System.out.println("4. 책 수정하기(가격)");
			System.out.println("5. 책 제거하기");
			System.out.println("6. 제목이 일치하는 책 한 권 조회하기");
			System.out.println("7. 제목이 일치하는 책 제거하기");
			System.out.println("8. 출판사가 일치하는 책 모두 조회하기");
			System.out.println("9. 저자가 일치하는 책 모두 조회하기");
			System.out.println("10. 검색어가 제목, 저자에 포함된 모든 책 조회하기");
			System.out.println("11. bookList 제목 오름차순으로 정렬시키기");
			System.out.println("0. 종료");
			System.out.println(); // 줄 바꿈
			
			System.out.print("메뉴 번호 입력 >>");
			input = sc.nextInt();
			sc.nextLine(); // 입력 버퍼에 남아있는 문자열 제거(오류 방지)
			System.out.println("---------------------------");
			
			switch(input) {
			case 1: selectAll();   break;
			case 2: selectIndex(); break;
			case 3: addBook(); break;
//			case 4: modifyBookPrice(); break;
			case 4: modifyBookPrice2222(); break;
			case 5: removeBook(); break;
			case 6: selectTitle(); break;			
			case 7: removeBookTitle(); break;			
			case 8: selectPublisherAll(); break;			
			case 9: selectAuthorAll(); break;			
			case 10: searchBook(); break;		
			case 11: bookListSorting(); break;		
			
			case 0: System.out.println("*** 프로그램이 종료됩니다 ***");break;
			default: System.out.println("@@@ 메뉴 번호 잘못 입력 @@@");;
			}
			
		} while(input != 0);
		

	}



	/**
	 * 1. 전체 조회
	 * - BookService의 필드 bookList를 얻어와
	 * 일반 for문을 이용하여 모든 요소 정보 출력
	 * 단, bookList에 저장된 정보가 없으면 
	 *  "저장된 책이 존재하지 않습니다" 출력
	 */
	private void selectAll() {
		
		System.out.println("\n### 전체 조회 ###\n");
		
		List<BookDTO> list = service.getBookList();
		
		// 전달 받은 list에 데이터가 있는지 확인
		
		// int size() : 저장된 객체의 개수 반환
	
//		if(list.size() == 0) {
//			System.out.println("저장된 책이 존재하지 않습니다");
//			return;
//		} 
		
	// boolean isEmpty() : 저장된 객체가 없으면 true 반환
		if(list.isEmpty()) {
			System.out.println("저장된 책이 존재하지 않습니다");
			return;
		}
		
		// 모든 요소 정보 출력
		for(int i=0 ; i<list.size() ; i++) {
			System.out.println(i + ")" + list.get(i));
		}
	}
	
	
	
	/**
	 * 2. 조회하려는 책의 index 번호를 입력 받아 책 정보 출력
	 * 
	 * - BookService로 부터 index 번째 BookDTO 반환 받기
	 * 
	 * - 단, index 번호가 bookList의 범위를 초과하면
	 * "해당 인덱스에 책이 존재하지 않습니다" 출력
	 */
	private void selectIndex() {
		
		System.out.println("\n### index 번호로 조회 ###\n");
		
		System.out.print("조회할 책 index 번호 : ");
		int index = sc.nextInt();
		
		BookDTO book = service.selectIndex(index);
		
		if(book ==null) {
			System.out.println("해당 인덱스에 책이 존재하지 않습니다");
			return;
		}
		
		System.out.println("제목 : " + book.getTitle());
		System.out.println("저자 : " + book.getAuthor());
		System.out.println("가격 : " + book.getPrice());
		System.out.println("출판사 : " + book.getPublisher());

	}
	
	
	
	/**
	 * 3. 책 정보(제목, 저자, 가격, 출판사)를 입력받아
	 *  BookService의 bookList에 마지막 요소로 추가
	 *  단, 모든 정보가 일치하는 책은 추가 X(중복 저장 X)
	 */
	private void addBook() {
		
		System.out.println("\n### 책 추가하기 ###\n");
		
		// 제목 입력받기
		System.out.print("제목 : ");
		 //엔터가 입력되기 전까지의 문자열 얻어오기(띄어쓰기 가능)
		String title = sc.nextLine();
		
		// 저자 입력받기
		System.out.print("저자 : ");
		String author = sc.nextLine();
		
		// 가격 입력받기
		System.out.print("가격 : ");
		int price = sc.nextInt();
		sc.nextLine(); // 입력 버퍼 남은 문자열 제거
		// -> sc.next(); / sc.nextInt() 등을 수행 후 무조건 작성
		
		// 출판사 입력받기
		System.out.print("출판사 : ");
		String publisher = sc.nextLine();
		
		
		// service 호출
		boolean result = 
			service.addBook(new BookDTO(title, author, price, publisher));
		
		if(result) { // 추가 성공
			System.out.println(title+" 책이 추가되었습니다");
			
		}else { // 추가 실패
			System.out.println(title + "은/는 이미 존재하는 책 입니다");
			
		}
	}
	
	
	


	/**
	 * 4. 인덱스 번호를 입력 받아 
	 * - 해당 인덱스에 책이 없다면 -> "해당인덱스에 책이 존재하지 않습니다"
	 * - 해당 인덱스에 책이 있다면
	 *  -> 
	 *  1) "수정할 가격 입력 :" + 스캐너로 가격 입력
	 * 	2) 입력받은 index번째 요소의 가격 수정
	 *  3) "[책제목] 가격이 [이전가격]->[수정된 가격]으로 수정되었습니다"
	 *  를 출력
	 */
	private void modifyBookPrice() {
		
		System.out.println("\n### 책 가격 수정하기 ###\n");
		
		// 가격을 수정할 인덱스 번호 입력받기
		System.out.print("가격을 수정할 인덱스 번호를 입력하세요 : ");
		int index = sc.nextInt();
		
		// index번째 요소 얻어오기(없으면 null)
		BookDTO target = service.selectIndex(index);
		
		// 해당 인덱스에 요소가 없는경우
		if(target == null) {
			System.out.println("해당 인덱스에 책이 존재하지 않습니다");
			return;
		}
		// 있는 경우 요소를 얻어옴
		System.out.print("수정할 가격 입력하세요 : ");
		int newPrice = sc.nextInt();
	
		int oldPrice = target.getPrice(); // 기존 가격
		target.setPrice(newPrice); // 새 가격으로 변경
		
		System.out.printf("[%s] 가격이 [%d]->[%d]으로 수정되었습니다",target.getTitle(),oldPrice,newPrice);
		
	}


	/* 다른 풀이 방법*/
	private void modifyBookPrice2222() {
		
		System.out.println("\n### 책 가격 수정하기 ###\n");
		
		// 가격을 수정할 인덱스 번호 입력받기
		System.out.print("가격을 수정할 인덱스 번호를 입력하세요 : ");
		int index = sc.nextInt();
		
		// 인덱스 범위 초과시 
		if(service.checkIndex(index)==false) {
			System.out.println("해당 인덱스에 책이 존재하지 않습니다");
			return;
		}
		
		// 수정할 가격 입력받기
		System.out.print("수정할 가격 입력하세요 : ");
		int newPrice = sc.nextInt();
		
		// 서비스 홏풀
		System.out.println(service.modifyBookPrice22222(index,newPrice));
	}
	
	
	/** 
	 * 5. index 번호를 입력 받아 책 제거
	 *  단, 해당 index에 책이 없으면
	 *  "해당 인덱스에 책이 존재하지 않습니다" 출력
	 * 
	 * 있으면 "[책제목] 책이 제거되었습니다" 출력
	 */
	private void removeBook() {
		
		System.out.println("\n### 책 제거하기(index) ###\n");
		
		// 제거할 인덱스 번호 입력받기
		System.out.print("제거할 책 인덱스 번호 : ");
		int index = sc.nextInt();
		
		// 서비스 호출
		BookDTO target = service.removeBook(index);
		
		if(target == null) {
			System.out.println("해당 인덱스에 책이 존재하지 않습니다");
		}else {
			System.out.printf("[%s] 책이 제거되었습니다\n",
					target.getTitle());
		}
	}
	
	

  /**
   * 6. 제목이 일치하는 책 정보 출력
   * 단, 제목이 일치하는 책이 없다면 "검색 결과 없음" 출력
   */
	private void selectTitle() {
		
		System.out.println("\n### 제목이 일치하는 책 조회하기 ###\n");
		
		// 제목 입력받기
		System.out.print("찾고싶은 책 제목을 입력하세요 : ");
		String title = sc.nextLine();
		
		
		// 서비스 호출
		BookDTO book = service.selectTitle(title);
		
		if(book == null) {
			System.out.println("검색 결과 없음");
		} else {
			System.out.println("제목 : " + book.getTitle());
			System.out.println("저자 : " + book.getAuthor());
			System.out.println("가격 : " + book.getPrice());
			System.out.println("출판사 : " + book.getPublisher());
		}
	}

	
	/**
	 * 7. 제목이 일치하는 책 정보 제거하기
	 */
	private void removeBookTitle() {
		
		System.out.println("\n### 제목이 일치하는 책 정보 제거하기 ###\n");
		
		// 제목 입력받기
		System.out.print("찾고싶은 책 제목을 입력하세요 : ");
		String title = sc.nextLine();
		
		System.out.println(service.removeBookTitle(title).getTitle()+"이/가 제거되었습니다"); 
		
	}

	
	/**
	 * 8. 출판사가 일치하는 책 정보 조회하기
	 */
	private void selectPublisherAll() {
		
		System.out.println("\n### 출판사가 일치하는 책 조회하기 ###\n");
		
		// 출판사 입력받기
		System.out.print("찾고싶은 책의 출판사를 입력하세요 : ");
		String publisher = sc.nextLine();
		
		System.out.println(service.selectPublisherAll(publisher));
	}
	
	
	
	
	/**
	 * 9. 저자가 일치하는 책 모두 조회하기
	 */
	private void selectAuthorAll() {
		
		System.out.println("\n### 저자가 일치하는 책 모두 조회하기 ###\n");
		
		// 출판사 입력받기
		System.out.print("찾고싶은 책의 저자를 입력하세요 : ");
		String author = sc.nextLine();
		
		System.out.println(service.selectAuthorAll(author));
		
	}


	
	
	/**
	 * 10. 검색어가 제목, 저자에 포함된 모든 책 조회하기
	 */
	private void searchBook() {
		
		System.out.println("\n### 검색어가 제목, 저자에 포함된 모든 책 조회하기 ###\n");
		
		// 제목이나 저자 입력받기
		System.out.print("찾고싶은 책의 제목이나 저자를 입력하세요 : ");
		String searchWord = sc.nextLine();
		
		System.out.println(service.searchBook(searchWord));
	}






	/**
	 * 11. bookList 제목 오름차순으로 정렬시키기
	 */
	private void bookListSorting() {
		
		System.out.println("\n### 제목 오름차순 정렬시키기 ###\n");
		
		service.bookListSorting();
		
	}










	
	
	
	
	
	
}
