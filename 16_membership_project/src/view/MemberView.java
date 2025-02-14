package view;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

// View : 사용자에게 보여지는 역할을 하는 클래스/객체
// - 보여줄 화면을 출력 / 필요한 데이터를 입력
public class MemberView {

	Member member = new Member();
	
	private MemberService service = null;
	private Scanner sc = null;
	BufferedReader br = null;

	/**
	 * 기본 생성자 - MemberView 객체 생성 시 필드 초기화 - MemberService 초기화 시 예외가 발생할 수 있으므로 try -
	 * catch 작성
	 */
	public MemberView() {

		try {
			// 객체 생성 중 발생한 예외를 View에 모아서 처리
			service = new MemberServiceImpl();

			// 키보드를 입력 받기 위한 스트림 생성
			sc = new Scanner(new InputStreamReader(System.in, "UTF-8"));

		} catch (Exception e) {

			System.out.println("*** 프로그램 실행 중 오류 발생 ***");
			e.printStackTrace();
			System.exit(0); // 프로그램 종료
		}
	}

	// -------------------------------------------------------------------
	// [메인 메뉴]
	public void mainMenu() {

		int input = 0;
		do {

			try {
				// 메뉴 출력 후 입력된 번호를 반환 받기
				System.out.println("\n===== 회원 관리 프로그램 =====\n");

				System.out.println("1. 회원 가입(추가)");
				System.out.println("2. 회원 전체 조회");
				System.out.println("3. 이름 검색(동명이인 있으면 모두 조회)");
				System.out.println("4. 특정 회원 사용 금액 누적하기");
				System.out.println("5. 회원 정보 수정");
				System.out.println("6. 회원 탈퇴");
				System.out.println("0. 종료");

				System.out.print("메뉴 선택 >>> ");

				// 입력 받은 문자열을 int 형태로 변환
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼에 남은 문자열 제거
				System.out.println(); // 줄바꿈

				// 선택된 메뉴 번호에 따라 case 선택
				switch (input) {
				case 1: addMember(); break;
				case 2: selectAll(); break;
				case 3:	selectName(); break;
				case 4: updateAmount();	break;
				case 5:	updateMember();	break;
				case 6:	deleteMember();	break;
				case 0:	System.out.println("*** 프로그램 종료 ***");	break;
				default:	System.out.println("### 메뉴에 작성된 번호만 입력 해주세요 ###");
				}
				System.out.println("==========================================");

				
				/* 모든 메서드에서 던져진 예외를 모아서 처리*/
			} catch (NumberFormatException e) {
				System.out.println("\n### 숫자만 입력 해주세요 ###\n");
				input = -1; // 첫 반복에서 종료되지 않게 값 변경

			} catch (IOException e) {
				System.out.println("\n### 입출력 관련 예외 발생 ###\n");
				e.printStackTrace(); // 예외 추적

			} catch (Exception e) { // 나머지 예외 처리
				e.printStackTrace();
			}

		} while (input != 0);
	}

	
	
	
	// ---------------------------------------------------------------
	// [1. 회원 가입(추가)]
	public void addMember() throws IOException {
		System.out.println("\n----- 회원 가입(추가) -----\n");
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		System.out.print("휴대폰 번호(- 제외) : ");
		String phone = sc.nextLine();
		
		// 
		if(service.addMember(name, phone)==false) {
			System.out.println("### 중복되는 휴대폰 번호가 존재합니다 ###");
		}else {
			service.addMember(name, phone);
		}
	}

	
	
	
	// -------------------------------------------------------------
	// [2. 회원 전체 조회]
	public void selectAll() {
		System.out.println("\n----- 회원 전체 조회 -----\n");
		
		// view <-> service <-> dao
		
		List<Member> memberList = service.getMemberList();
		
		// 회원 목록 출력
		memberList.stream().forEach(member->System.out.println(member));
	}

	
	
	
	// ------------------------------------------------------------------------
	// [3. 이름으로 검색(동명이인)]
	public void selectName() throws IOException {
		System.out.println("\n----- 이름 검색(동명이인 있으면 모두 조회) -----\n");
		
		System.out.print("검색할 이름 입력 : ");
		String name = sc.nextLine();
		
		if(service.selectName(name).isEmpty()) {
			
			System.out.println("### 검색 결과가 없습니다 ###");
		}
		for(Member mb : service.selectName(name)) {
			System.out.println(mb);
		}
	}

	
	
	
	// ------------------------------------------------------------
	// [4. 특정 회원 사용 금액 누적하기]
	public void updateAmount() throws IOException {
		System.out.println("\n----- 특정 회원 사용 금액 누적하기 -----\n");

		System.out.print("회원 이름 입력 : ");
		String name = sc.nextLine();
		
		// 회원목록에서 입력한 회원과 이름이 일치하는 회원을 모두 저장하는 리스트
		List<Member> memberList = service.selectName(name);
		
		// 리스트 내에 이름이 일치하는게 없다면 아래 출력
		if(memberList.isEmpty()) {
			System.out.println("### 이름이 일치하는 회원이 존재하지 않습니다 ###");
		}
		// 리스트 내부의 개수가 하나일 때
		else if(memberList.size()==1){
			System.out.print("누적할 금액 입력 :");
			int amount = sc.nextInt();
			Member target = memberList.get(0);
			
			System.out.println(service.updateAmount(target, amount));
			
			
		}
		
		// 리스트 내부의 객체 개수가 2개 이상일 때
		else {
			
			int i = 1;
			// 리스트 내부에 존재하는 요소의 이름과 번호 출력
			for(Member mb : memberList) {
				System.out.printf("%d) %s (%s)\n",i,mb.getName(),mb.getPhone());
				i += 1;
			}
			// 리스트 요소 중 하나 선택
			System.out.print("선택할 회원의 번호를 입력 : ");
			int num = sc.nextInt();
			
			System.out.print("누적할 금액 입력 :");
			int amount = sc.nextInt();
			Member target = memberList.get(num-1);
			
			System.out.println(service.updateAmount(target, amount));
		}
	
	}
	
	
	
	

	// -----------------------------------------------------------------
	// [5.회원 정보 수정]
	public void updateMember() throws IOException {
		
		System.out.println("\n----- 회원 정보 수정 -----\n");

		System.out.print("회원 이름 입력 : ");
		String name = sc.nextLine();
		
		
// 회원목록에서 입력한 회원과 이름이 일치하는 회원을 모두 저장하는 리스트
		List<Member> memberList = service.selectName(name);
		
		// 리스트 내에 이름이 일치하는게 없다면 아래 출력
		if(memberList.isEmpty()) {
			System.out.println("### 이름이 일치하는 회원이 존재하지 않습니다 ###");
		}
		
		
		// 리스트 내부의 개수가 하나일 때
		else if(memberList.size()==1){
			System.out.print("수정할 전화번호 입력 :");
			String phone = sc.nextLine();
			Member target = memberList.get(0);
			
			System.out.println(service.updateMember(target, phone));
		}
		
		
		// 리스트 내부의 객체 개수가 2개 이상일 때
		else {
			System.out.println("*** 대상 회원을 선택 해주세요 ***");
			System.out.println();
			
			int i = 1;
			// 리스트 내부에 존재하는 요소의 이름과 번호 출력
			for(Member mb : memberList) {
				System.out.printf("%d) %s (%s)\n",i,mb.getName(),mb.getPhone());
				i += 1;
			}
			// 리스트 요소 중 하나 선택
			System.out.print("선택할 회원의 번호를 입력 : ");
			int num = sc.nextInt();
			sc.nextLine(); // 입력 버퍼 지우기
			
			System.out.print("수정할 전화번호 입력 : ");
			String phone = sc.nextLine();
			Member target = memberList.get(num-1);
			
			System.out.println(service.updateMember(target, phone));
		}
		
	}

	
	
	
	
	// ------------------------------------------------------------
	// [6.회원 탈퇴]
	public void deleteMember() throws IOException {
		
		System.out.println("----- 회원 탈퇴 -----");
		System.out.println();
		
		System.out.print("회원 이름 입력 : ");
		String name = sc.nextLine();
		
		
    // 회원목록에서 입력한 회원과 이름이 일치하는 회원을 모두 저장하는 리스트
		List<Member> memberList = service.selectName(name);
		
		// 리스트 내에 이름이 일치하는게 없다면 아래 출력
		if(memberList.isEmpty()) {
			System.out.println("### 이름이 일치하는 회원이 존재하지 않습니다 ###");
		}
		
		
		// 리스트 내부의 개수가 하나일 때
		else if(memberList.size()==1){
			
			// target에 객체 넣기
			Member target = memberList.get(0);
			
			// 정말 탈퇴 처리 할 것인지 y/n 입력 받기
			System.out.print("정말 탈퇴 처리 하시겠습니까? (y/n) : ");
			char answer = sc.next().charAt(0);
			
			// y를 입력하면 탈퇴 처리
			if(answer=='y') {
				System.out.println(service.deleteMember(target));
			} 
			// n을 입력하면 탈퇴 취소
			else if(answer=='n') {
				System.out.println("### 탈퇴 취소 ###");
			}
			// y도 n도 아닌 다른 문자 입력 시
			else {
				System.out.println("### 잘못 입력 하셨습니다. 다시 시도 해주세요 ###");
			}
		}
		
		
		// 리스트 내부의 객체 개수가 2개 이상일 때
		else {
			System.out.println("*** 대상 회원을 선택 해주세요 ***");
			System.out.println();
			
			int i = 1;
			// 리스트 내부에 존재하는 요소의 이름과 번호 출력
			for(Member mb : memberList) {
				System.out.printf("%d) %s (%s)\n",i,mb.getName(),mb.getPhone());
				i += 1;
			}
			// 리스트 요소 중 하나 선택
			System.out.print("선택할 회원의 번호를 입력 : ");
			int num = sc.nextInt();

			// 정말 탈퇴 처리 할 것인지 y/n 입력 받기
			System.out.print("정말 탈퇴 처리 하시겠습니까? (y/n) : ");
			char answer = sc.next().charAt(0);
			
			// target에 객체 넣기
			Member target = memberList.get(num-1);
			
			// y를 입력하면 탈퇴 처리
			if(answer=='y') {
				System.out.println(service.deleteMember(target));
			} 
			// n을 입력하면 탈퇴 취소
			else if(answer=='n') {
				System.out.println("### 탈퇴 취소 ###");
			}
			// y도 n도 아닌 다른 문자 입력 시
			else {
				System.out.println("### 잘못 입력 하셨습니다. 다시 시도 해주세요 ###");
			}

		}

	}

	
}