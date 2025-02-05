package section02.dto;

// 추상 클래스인 Animal 상속 받기
// -> 부모인 Animal의 필드/메서드(정상/추상)를 물려 받음
// -> 상속 받은 추상 메서드를 사용하고 싶으면 
//재정의 하라고 강제(오버라이딩)
public class Cat extends Animal{

	// 필드
	private String fur; // 털
	
	// 생성자
	public Cat() {
		super();
	}
	
	// 매개변수 생성자
	public Cat(String type, String fur) {
		super(type);
		this.fur = fur;
	}
	
	// getter/setter
	public String getFur() {
		return fur;
	}

	public void setFur(String fur) {
		this.fur = fur;
	}
	
	
	
	
	// The type Cat must implement the inherited 
	// abstract method Animal.move()
	
	@Override
	public void move() {
		System.out.println("고양이 : 4족 보행, 사뿐사뿐 움직임");
	}
	
	@Override
	public void sleep() {
		System.out.println("고양이 : 엎드려서 잠");
	}
	
	@Override
	public void eat() {
		System.out.println("고양이 : 깨작 꺠작");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
