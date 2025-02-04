package section02;

/**
 * Person 클래스를 상속 받은 Student 클래스
 */
public class Student extends Person{
	
	private String schoolName; // 학교명
	
	/* super() 생성자
	 * - super : 극상의, 상위의, 위쪽의
	 * 
	 * - 현재 클래스의 상위 클래스(부모) 생성자를 호출하는 구문
	 * 
	 * - super() 생성자는 반드시
	 * 	 자식 생성자의 첫 번째 줄에 작성 되어야 한다!!
	 *   (미작성 시 컴파일러 자동 추가)
	 *   
	 *  - 코드 길이 감소, 재사용성 증가 
	 */
	
	
	// 기본 생성자
	public Student() {
		super(); // 부모의 기본 생성자
		System.out.println("[Student 기본 생성자로 생성됨]");
	}
	
	
	
	// 매개 변수가 있는 생성자
	// -> Student가 가지고 있는 
	// 모든 필드(schoolName, name, age) 초기화
	public Student(String name, int age, String schoolName) {
		//setName(name);
		//setAge(age);
		// 위의 두줄 대신부모의 매개 변수 있는 생성자를 호출 -> 코드 길이 감소
		super(name,age);
		
		System.out.println("[Student 매개 변수가 있는 생성자로 생성됨]");
		
		// this.name=name; // 부모 필드 직접 접근 불가(private)
		this.schoolName = schoolName;
	}

	
	
	// getter/setter
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	/* super 참조 변수(부모 참조 변수)
	 * - 자식 내 부모 객체를 참조하는 변수
	 * - 부모의 필드/메서드에 접근하기 위해 사용
	 */
//Object.toString()->Person.toString()->Student.toString()
	@ Override 
	public String toString() {
		
		// 부모의 toString() 메서드 호출
		// -> name : 홍길동 / age : 20 이런 식으로 반환
		return super.toString()+" / schoolName : "+schoolName;
		//		return String.format("name : %s / age : %d / schoolName : %s" 
		//				, getName(),getAge(),schoolName);
		
	}
	
	/* Person.introduce() 메서드 오버라이딩(재정의) */
	/* 오버라이딩 성립 조건
	 * [변경 불가]
	 * - 반환형
	 * - 메서드명
	 * - 매개변수(개수,순서,타입)
	 * 
	 * [변경 가능]
	 * - 접근 제어자(기존->넓은 범위)
	 * - 예외 처리(기존->좁은 범위
	 * 
	 * [오버라이딩(Overriding, 덮어쓰기, 재정의)]
	 * - 상속 관계에서 부모의 메서드를
	 *   자식이 용도에 맞게 재정의 하는 것
	 * [오버로딩(Overloading, 과적)]
	 * - 하나의 클래스에 같은 이름의 메서드를 여러개 작성하는 기술
	 * 
	 * * 오버로딩 vs 오버라이딩
	 * 
	 * 같은 클래스에 작성 / 하위(자식) 클래스에 작성
	 * 매개 변수(개수,순서,타입) 하나라도 다름 / 매개변수(개수,순서,타입) 동일
	 * 반환형 관계 없음 / 반환형 동일해야 함
	 * 접근 제어자 관계 없음 / 접근 제어자 같거나 넓은 범위
	 * 예외처리 관계 없음 / 예외처리 같거나 좁은 범위
	 * 
	 * 공통점 : 메서드 이름이 동일해야 한다
	 */
	@ Override // 오버라이딩 명시 + 성립조건 맞는지 검사 지시
	public String introduce(String alias) {
		
		return String.format(
				"[Student가 재정의] 내이름은 %s, %s 이죠!",
				getName(),alias);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
