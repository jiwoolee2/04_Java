package condition.service;

import java.util.Scanner;

/**
 * 기능(예제, 연습문제) 제공용 클래스
 */

public class conditionService {
	
	// 필드(field) == 멤버 변수 = 인스턴스 변수
	// 해당 클래스(객체) 내에서 언제든지, 어디서든지 사용 가능한 변수
	Scanner sc = new Scanner(System.in);
	
	/**
	 * 1~10 사이 난수(정수)가
	 * 짝수인지 홀수인지 검사
	 * 
	 */
	public void method1() {
		
		// Math.random() : 0.0이상 1.0 미만 난수 발생
		int randomNumber = (int)(Math.random()*10+1);
		
		// 2로 나눴을 때 나머지가 0인지 아닌지 결과를 저장
		// == 0이면 짝수(true), 1이면 홀수(false)
		boolean result = randomNumber % 2 == 0;
		
		System.out.println("randomNumber :"+randomNumber);
		
		if(result) {
			System.out.println("짝수 입니다.");
		} else {
			System.out.println("홀수 입니다.");
		}
	}
	
	
	/**
	 * 나이를 입력 받아 "어린이", "청소년", "성인" 구분
	 * <pre>
	 * 13세 이하 "어린이"
	 * 13세 초과 19세 이하 "청소년"
	 * 19세 초과 "성인"
	 * </pre>
	 */
	public void method2() {
		System.out.print("나이 입력 : ");
		int age = sc.nextInt();
		
		if(age<=13) {
			System.out.println("어린이");
		} else if(age<=19) {
			System.out.println("청소년");
		} else {
			System.out.println("성인");
		}
		
	}
	
	/**
	 * 나이를 입력 받아 "어린이", "청소년", "성인" 구분
	 * <pre>
	 * 13세 이하 "어린이"
	 * 13세 초과 19세 이하 "청소년"
	 * - 14~16세 : 청소년(중)
	 * - 17~19세 : 청소년(고)
	 * 19세 초과 "성인"
	 * 0미만 100 이상 "잘못 입력하셨습니다"
	 * </pre>
	 */
	
	public void method3() {
		System.out.print("나이 입력 : ");
		int age = sc.nextInt();
		
		
		if(age<0 | age>=100) {
			System.out.println("잘못입력하셨습니다.");
			return ;
		 }
		String result;
		
		if(age<=13) {
			result = "어린이";
		} 
		else if(age<=19) {
			result = "청소년" + (age>=17? "(고)" : "(중)");
		} 
		else {
			result = "성인";
		}
		System.out.println(result);
	}
	
	/**
	 * conditionService에 작성된 메서드를 골라서 실행하는 메서드
	 */
	public void displayMenu() {
		
		System.out.println("1. method1()");
		System.out.println("2. method2()");
		System.out.println("3. method3()");
		
		System.out.print("메뉴 번호 입력 >>");;
		int input = sc.nextInt();
		
		// (중요) 같은 클래스 내 필드, 메서드는 이름만 부르면 호출 가능!
		switch(input) {
		case 1 : method1(); break;
		case 2 : method2(); break;
		case 3 : method3(); break;
		default : System.out.println("없는 메뉴 번호 입니다");
		}
	}
	
	
	/** [성적 판별기]
	 * <pre>
	 * 중간고사, 기말고사, 과제 점수를 입력 받아 성적 부여
	 * 
	 * - 중간고사 (40%), 기말고사(50%), 과제(10%)
	 * 
	 * - 입력 시 각각 100점 만점으로 입력 받음
	 * 
	 * - 합산된 점수에 따라 성적 부여
	 * 
	 * 95점 이상 : A+
	 * 90점 이상 : A
	 * 85점 이상 : B+
	 * 80점 이상 : B
	 * 75점 이상 : C+
	 * 70점 이상 : C
	 * 65점 이상 : D+
	 * 60점 이상 : D
	 * 나머지    : F
	 * 
	 * 
	 * [실행 화면]
	 * 이름 : 홍길동
	 * 중간고사 점수(40%) : 100
	 * 기말고사 점수(50%) : 80
	 * 과제 점수(10%) : 50
	 * 
	 * 홍길동의 최종 점수 : 85.0점
	 * 성적 : B+
	 *</pre>
	 */
	
	
	public void grade() {
		
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("중간고사 점수(40%) : ");
		int midExam = sc.nextInt();
		
		System.out.print("기말고사 점수(50%) : ");
		int finalExam = sc.nextInt();
		
		System.out.print("과제 점수(10%) : ");
		int assignment = sc.nextInt();
		
		double score = midExam*0.4+finalExam*0.5+assignment*0.1;
		System.out.printf("%s의 최종 점수 : %.1f\n",name,score);
		
//		String result = (score>=95 ? "A+" : (score>=90 ? "A" : (score>=85 ? "B+" : (score>=80 ? "B" : (score>=75 ? "C+" : 
//				(score>=70 ? "C" : (score>=65 ? "D+" : (score>=60 ? "D" : "F"))))))));
//		
		
		String result;
		
		switch((int)score/5) {
		
		
		case 20, 19: result = "A+"; break;
		case 18: result = "A"; break;
		case 17: result = "B+"; break;
		case 16: result = "B"; break;
		case 15: result = "C+"; break;
		case 14: result = "C"; break;
		case 13: result = "D+"; break;
		case 12: result = "D"; break;
		default: result = "F"; break;
		}
		
		System.out.println("성적 : "+result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
