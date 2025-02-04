package section01.dto;

public class Child extends Parent {
	
	private String car;
	
	
	// 기본 생성자 생성 -> 접근 제어자 + 클래스명
	public Child() {
		super();
		
	}

  // 매개 변수 있는 생성자
	public Child(String lastName, int money, String car) {
		super(lastName,money); //부모의 매개변수있는 생성자 호출
		this.car = car;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}
	
	@Override
	public String toString() {
		// Parent [lastName="제갈", money=1000] / Child[car=car변수]
		return super.toString()+" / Child [car="+car+"]";
	}
}
