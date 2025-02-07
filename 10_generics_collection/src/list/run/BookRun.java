package list.run;

import list.view.BookView;

public class BookRun {

	public static void main(String[] args) {
		
		// 객체를 1회성으로 사용하는 방법
		// displayMenu가 while문으로 되어있어서 한번만 실행하면 계속 돌아감
		new BookView().displayMenu();
	}
}
