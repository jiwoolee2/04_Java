package section01;

public class Child2 extends Parent {

//	private String lastName = "김";    //성
//	private String firstName = "바다"; //이름
//	private String address = "경기도 용인시";
//	private int money = 7000;
	
	private String car; // Child2만의 필드
	
	public Child2() {
		System.out.println("*** Child2 객체 생성됨 ***");
		
		// 부모의 firstName 필드를 상속 받았으나 접근 제어자가 private이라서
		// 직접 접근이 불가능 -> 간접 접근 방법
		setFirstName("바다");
		setAddress("경기도 용인시");
		setMoney(7000);
		car = "아반떼";
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}
	
	// alt+shift+s->r : getter/setter 자동 생성
	
	
	
	
	
	
	
}
