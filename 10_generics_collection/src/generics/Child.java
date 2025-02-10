package generics;

public class Child extends Parent{

	private int number;
	
	public Child(int number) {
		this.number = number;
	}
	
	public Child() {}

	@Override
	public String toString() {
		return super.toString()+"/ number :"+number;
	}
}
