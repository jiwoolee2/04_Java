package section02;

/* Object 클래스
 * - 모든 클래스(객체)의 최상위 부모
 * - 모든 클래스(객체)가 공통적으로 가져야 하는 기능이 모여 있음
 * 
 * - 클래스 선언부에 아무런 상속 구문이 작성되지 않으면
 *   컴파일러가 자동으로 extends Object 추가
 */

/* [final 예약어]
 *
 * fianl  : 마지막, 최종
 * 
 * final class : 상속 불가 클래스
 * -> 다른 클래스가 필드/메서드를 상속 받아 사용하지 못하게 하려고 사용
 * (+ 모든 메서드 오버라이딩 불가)
 * 
 * final method : 오버라이딩 불가 메서드
 * -> 이미 완벼한 기능 / 바꾸면 문제가 생기는 기능 재정의 방지
 * 
 * final variable : 더 이상 값을 대입 못하게 함(상수)
 * 
 * * 자바에서 클래스간의 상속은 단일 상속만 지원
 * 
 */
public class Person { /* extends object */
	private String name;
	private int age;
	
	// 기본 생성자
	public Person() {
		System.out.println("[Person 기본 생성자로 생성됨]");
	}

	
	// 매개 변수 생성자 자동 생성 : alt+shift+s->o
	
	public Person(String name, int age) {
		System.out.println("[Person 매개 변수있는 생성자로 생성됨]");
		
		this.name = name;
		this.age = age;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	/* Object toString 메서드
	 * - 객체를 사람이 읽기 쉬운 간단한 문자열 형태로 반환하는 메서드
	 * - 모든 클래스는 Object 클래스의 후손임. 
	 *   따라서 Object.toString() 이 존재 
	 * - 모든 자식 클래스에서 재정의(오버라이딩)해서 사용
	 */

	/* 오버라이딩(Overriding)
	 * - 덮어쓰다, 재정의하다라는 의미
	 * - 아래와 같이 Object.toString(), 즉 부모의 메서드가 존재하는데
	 *   똑같은 메서드를 다시 정의하는 것을 오버라이딩이라고 한다 
	 * - 물려받을 자식이 용도에 맞게 바꿔서 사용하는 것이라고 생각하면 됨
	 * 
	 * [오버라이딩 성립 조건]
	 * 
	 * 1) 변경 불가
	 * - 메서드명, 반환형(반환 type), 매개변수의 타입,개수,순서
	 * 
	 * 2) 변경 가능
	 * - 접근제어자는 같거나 넓은 범위로 바꿀 수 있음
	 *   ex) protected->public
	 * - 예외처리는 같거나 좁은 범위로 변경 가능
	 * 
	 * *** 주의 사항 ***
	 * 부모의 private 메서드는 오버라이딩 불가!
	 * (직접 접근이 차단되어있기 때문에 재정의 자체가 불가)
	 * 
	 * 
	 * ------------------------------
	 * 
	 * 어노테이션(Annotation,주석) : 컴파일러를 위한 주석
	 * - 코드의 추가적인 정보 제공 목적
	 * - 컴파일러에게 지시하는 목적
	 * 
	 * @Override 어노테이션
	 * 1) 해당 메서드가 오버라이딩 되었음을 명시(정보 제공)
	 * 2) 컴파일러에게 해당 메서드 오버라이딩이 문제없이 작성되었는지
	 * 		확인(검증) 하라 지시(성립 조건을 위배하지 않았는지 확인)
	 */
	
	@Override
	public String toString() {
		return String.format("name : %s / age : %d", name,age);
	}

	// 부모 메서드
	public String introduce(String alias) {
		return String.format(
				"[person] 이름은 %s이고 별명은 %s 입니다",
				name,alias);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
