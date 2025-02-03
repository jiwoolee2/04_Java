package section01;

/* 상속 : 부모의 코드(필드/메서드)를 자식이 자신의 것 처럼 사용하는 기술
 * 
 * [특징]
 * 1.재사용성 증가
 * - 한 번 작성한 부모 코드를 자식들이 재사용
 * 
 * 2.유지 보수성 증가
 * - 부모 코드만 수정하면 자식은 그대로 있어도 됨
 * 
 * 3. 공통적인 규약 정의
 * - 물려 받은 자식들의 형태가 부모와 비슷함
 * -> 부모 코드 수정, 추가, 제거 시 자식도 일괄 변경
 * 
 * [상속 키워드] : extends(확장하다)
 * -> 부모의 필드/메서드를 상속 받은 
 * 	 	자식의 크기가 커지기 때문에 "확장하다" 키워드 사용
 * 
 * [상속 주의 사항]
 * 1. 부모의 생성자는 상속 X
 * 
 * 2. 부모의 private 필드/메서드는 상속 받은 자식도 직접 접근 불가능
 * 
 * 3. 부모의 접근제어자가 protected일 경우 직접 접근 가능
 * 
 * [상속 메모리 구조]
 * - 자식 객체 생성 시
 * 	 부모 객체가 같이 생성되어
 *   자식 객체 내부에 배치됨
 */
public class Section01Run {

	public static void main(String[] args) {
		
		// 부모 객체 생성
		Parent p1 = new Parent();
		System.out.println("Parent의 toString() : "+p1.toString());
		
		System.out.println("-----------------------");
		
		
		// Child1 객체 생성
		Child1 c1 = new Child1();
		//*** Parent 객체 생성됨 ***
		//*** Child1 객체 생성됨 ***
		// -> 자식 객체 생성시 부모도 같이 생성됨!! 사실 부모가 먼저 성성됨
		System.out.println("Child1의 toString() : "+c1.toString());
		System.out.println("Child1 만의 필드 laptop : "+c1.getLaptop());
		
		System.out.println("-----------------------");

		// Child2 객체 생성
		Child2 c2 = new Child2();
		System.out.println("Child2의 toString() : "+c2.toString());
		System.out.println("Child2 만의 필드 car : "+c2.getCar());
		
		System.out.println("-----------------------");
		
		// Child3 객체 생성
		Child3 c3 = new Child3();
		System.out.println("Child3의 toString() : "+c3.toString());
		System.out.println("Child3 만의 필드 bitCoin : "+c3.getBitCoin());
		
		System.out.println("-----------------------");
	}
}
