package staticmember;

public class StaticMemberRun {
	public static void main(String[] args) {
		
		System.out.println("Korean.nationalCode : "+Korean.nationalCode);
		/* 아래 있는 코드가 수행되기 전!!
		 * 이니 static이 붙은 멤버가 static 영역에 
		 * 클래스명.필드명/ 클래스명.메서드명()
		 * 형태로 할당되어 있음!
		 */
		
		Korean k1 = new Korean("김철수", "051212-3456789");
		Korean k2 = new Korean("이미영", "001009-4232323");
		
		//국가 코드 세팅
//		k1.setNationalCode(82);
//		k2.setNationalCode(82);
		//korean 객체가 생성될 때 마다 세팅하기 불편함..
		// -> korean 클래스에 nationalCode 필드에 값을 명시적으로 초기화
		
		
		System.out.println(k1.toString());
		System.out.println(k2.toString());
		
		// 만약 이미 생성된 모든 Korean 객체의 
		// nationalCode를 수정해야 하는 경우
//		k1.setNationalCode(1);
//		k2.setNationalCode(1);
		// -> 생성된 모든 Korean 객체에 하나하나 접근해서 값 수정
		// (어떻게 5천만번 하지??)
		// -> static을 이용해서 문제  해결!!!!
		
		// static 변수 값 변경
		// -> 모든 Korean 객체의 nationalCode가 일괄 변경
		Korean.nationalCode = 1000;
		
		System.out.println("-------------------------------");
		System.out.println("Korean.nationalCode : "+Korean.nationalCode );
		System.out.println(k1.toString());
		System.out.println(k2.toString());
		
		
		
		System.out.println("-------------------------------");
		System.out.println("[초기화 블럭 확인]");
		
		Korean k3 = new Korean();
		Korean k4 = new Korean();
		System.out.println(k3.toString());
		System.out.println(k4.toString());
		
		
		
		
		
		
		
	}
}
