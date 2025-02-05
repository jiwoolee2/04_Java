package section01.service;

import section01.dto.Child;
import section01.dto.Parent;

public class PolymorphismService {
	
	/* [매개 변수의 다형성]
	 * 
	 * - 매개 변수의 타입을 "부모 타입"으로 설정하여
	 * 	 모든 '자식 객체 참조 주소'를 전달 받을 수 있도록 함
	 * 	(다형성 - 업캐스팅 적용)
	 * 
	 * * 매개 변수(Parameter) : 메서드 호출 시 전달된 값을 저장하는 변수 
	 */
	
	/* * 객체 지향 프로그래밍
	 * - 현실은 객체간의 상호작용으로 사건이 발생한다는 개념을
	 *	 컴퓨터로 옮겨놓은 것
	 * - 객체는 속성/기능으로 이루어짐
	 * 
	 * * class : 객체의 속성과 기능을 정의한 문서
	 * 
	 * * instance : class에 작성된 내용을 토대로 메모리에 생성된 객체
	 */

	/* * 참조변수 instanceof 클래스명
	 * 
	 * - 참조 변수가 참조하는 객체가 "클래스명"으로 만들어진 객체가 맞을경우
	 *   true, 아님 false
	 *   
	 * - 상속 관계도 파악이 가능(자식 객체 내부의 부모 객체도 인인식)  
	 */
	
	/** 객체의 타입, 필드 정보를 출력하는 메서드
	 * @param obj : Object 또는 Object를 상속 받은 객체 참조 변수
	 * (Object는 모든 클래스/객체의 최상위 부모)
	 */
	public void printObject(Object obj) {
		
		// obj에 전달 받을 수 있는 타입 : Object, Parent, Child
		
		String result = null;
		
		if(obj instanceof Child) {
			// obj가 참조하는 객체의 타입이 Child 또는 Child를 상속 받은 자식 타입인 경우
			
			// Child로 다운 캐스팅
			Child c= (Child)obj;
			
			result = "[Child Type]\n";
			result += c.getLastName()+"/"+c.getMoney()+"/"+c.getCar();
		}
		else if(obj instanceof Parent) {
			// obj가 참조하는 객체의 타입이 Parent 또는 Parent를 상속 받은 자식 타입인 경우
			
			// Parent로 다운 캐스팅
			Parent p = (Parent)obj;
			
			result = "[Parent Type]\n";
			result += p.getLastName() + " / " + p.getMoney();
		}
//		else if(obj instanceof Object) {
//			// obj가 참조하는 객체의 타입이 Object 또는 Object를 상속 받은 자식 타입인 경우
//			result = "[Object Type] 필드 없음";
//		}
		else{
			// obj가 참조하는 객체의 타입이 Object 또는 Object를 상속 받은 자식 타입인 경우
			// -> 모든 클래스/객체는 Object의 자식이기 때문에 else에 모두 걸림
			result = "[Object Type] 필드 없음";
		}

		System.out.println("-------------------------");
		System.out.println(result);
		System.out.println("-------------------------");
	}
	
	
	
	// 매개 변수의 다형성 확인 
	public void test1() {
		
		Object obj 		= new Object();
		Parent parent = new Parent("김",500);
		Child child 	= new Child("이",6000,"소나타");
		
		printObject(obj); 	 // 같은 Object 타입 전달 가능
		printObject(parent); // Object의 자식 타입인 Parent 전달 가능
		printObject(child);  //Object의 후손 타입인 Child 전달 가능
	}
	
	
	/* [반환형의 다형성]
	 * - 메서드의 반환형을 부모 타입을 설정하여
	 *   return 되는 자식 객체의 주소를 참조할 수 있게 함
	 *   
	 *  - 다형성의업캐스팅 적용
	 */
	
	/**
	 * 전달받은 num 값에 따라 알맞은 객체를 생성/반환하는 메서드
	 * @param num : 1-CHild ,2-Parent ,나머지-Object6
	 * @return 생성된 객체 참조 주소 반환
	 */
	public Object getInstance(int num) {
		
		// if, for, while 에서
		// {} 내부 코드가 한줄인 경우 {} 생략 가능
		if(num==1) return new Child();
		
		if(num==2) return new Parent();
		
		return new Object();
	}
	
	
	// 반환형의 다양성 확인
	public void test2() {
		
		// getInstance()의 반환형은 Object
		Object o1 = getInstance(1); // Child 객체 반환
		Object o2 = getInstance(2); // Parent 객체 반환
		Object o3 = getInstance(3); // Object 객체 반환
		
		// 매개변수의 다형성 + 반환형의 다형성
		printObject(o1);
		printObject(o2);
		printObject(o3);
	}
	
	/*[바인딩(Binding)]
	 * - 메서드 호출부와 살제 실행할 메서드 코드를 연결
	 * 
	 * [정적 바인딩]
	 * - 프로그램 실행 전
	 *   컴파일 단계에서 메서드와 메서드 호출부를 연결
	 * 
	 * [동적 바인딩]
	 * - 프로그램 실행 중(Runtime)
	 *   정적 바인딩 된 메서드를 실제 객체 타입을 기준으로 연결
	 *   
	 * - 왜 사용?
	 * 	1) 다운 캐스팅의 번거로움을 없앰
	 * 	2) 재정의된 메서드(조금 더 효율적, 알맞는 기능)을 수행  
	 * * 상속, 오버라이딩, 업캐스팅(+메모리 구조)  
	 */
	
	// 바인딩 확인
	public void test3() {
		
		Parent p1 = new Parent("김",10); // 부모가 부모를 참조하는 일반적인 형태
		Child c1 = new Child("최",20,"캐스퍼"); // 얘도 마찬가지. 다형성 적용 x
		
		Parent p2 = new Child("박",30,"포터"); 
		// 부모가 자식을 참조하는 다형성 업캐스팅 적용
		
		// 정적 바인딩 : 프로그램 실행 x, 컴파일 단계에서 연결
		System.out.println("p1.toString() : "+p1.toString());
		System.out.println("c1.toString() : "+c1.toString());
		
		// (정적일 경우) -> section01.dto.Parent.toString()에 연결
		// (동적일 경우) -> section01.dto.Child.toString()에 연결
		System.out.println("p2.toString() : "+p2.toString());
	}
	
	
	/* [객체 배열의 다형성]
	 * - 부모 타입의 참조 변수를 묶음으로 다루어서
	 *   배열 각 요소에 업캐스팅 적용
	 * 
	 * * 객체 배열 : 객체 참조 변수의 묶음
	 * - 참조 변수는 객체의 주소를 저장함
	 */
	
	// 객체 배열의 다형성 확인
	public void test4() {
		
		// Object 객체 참조 변수 5칸짜리 배열 생성해서
		// Object[] 참조 변수 arr에 배열 주소 대입
		Object[] arr = new Object[5];
		
		for(int i=0 ; i<arr.length ; i++) {
													// 1 2 3 1 2 반복
			arr[i] = getInstance(i%3+1);
			
			// 0,3 : Child
			// 1,4 : Parent
			// 2   : Object
		}
		
		System.out.println("-----------------------------");
		
		
		// 객체 배열의 다형성 + 동적 바인딩
		
		
		// * 동적 바인딩
		// - 실행 중 메서드 호출부 연결이 실제 참조하는 객체의 
		// 	 오버라이딩 된 메서드로 연결(오버라이딩 된 메서드가 우선권)
		
		for(Object obj : arr) {
			
			// 실제 참조 중인 객체의 toString()이 호출됨
			System.out.println(obj.toString());
		}
		
		System.out.println("----------------------");
		System.out.println("[동적 바인딩이 없었더라면]");
		
		for(Object obj : arr) {
				
			// 참조 객체 타입 검사 후 다운 캐스팅
				if(obj instanceof Child) {
					System.out.println(((Child)obj).toString());
				}
				else if(obj instanceof Parent) {
					System.out.println(((Parent)obj).toString());
				}
				else {
					System.out.println(obj.toString());
				}
			}
		
		/* debug 모드
		 * - 이클립스 등 IDE에서 제공하는 기능으로 코드 수행 중
		 * 	 지정된 지점(break point)에서 실행을 멈추고
		 * 		해당 시점에 존재하는 변수/필드 값을 확인하는 모드
		 * ctrl + shift + b
		 */
	}
	
	
	// 다운 캐스팅 시 주의 사항
	// - 강제 형 변환이 적용되는 참조변수가 형변환 하려는 타입의 객체를
	//   참조하고 있는지 확인 필요 -> instanceof 사용
	public void test5() {
		
		// 업 캐스팅 
		Parent p = new Child("김",200,"소나타");
		
		test6(p);
	}
	
	// 전달 받은 객체를 String 타입으로 다운 캐스팅(강제 형 변환)
	public void test6(Object obj) {
		
		/* ClassCastException 발생
		 * - 참조 변수의 강제 형 변환(다운 캐스팅) 시
		 * 참조하는 객체가 변환하려는 타입이 아니거나 상속 관계도 아니면
		 * 형 변환 불가(ClassCastException)발생
		 */
		// 참조하는 객체가 String 타입인 경우에만 형 변환
		if(obj instanceof String) {
			String p = (String)obj;
			System.out.println(p);
		}else {
			System.out.println(obj.getClass().getName());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
