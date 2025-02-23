package section01.run;

/* 에외처리방법 
 * 1. try exception  구문을 통해 예외가 발생한 곳에서 처리한다.
 * 
 * 2. 예외가 발생한 메서드의 호출부로 예외를 넘겨서 처리한다.

 * */
import section01.service.TryCatchService;

public class TryCatchRun {

	public static void main(String[] args) {
		TryCatchService service = new TryCatchService();
		
//		service.test1();
//		service.test2();
//		service.test3();
//		service.test4();
//		service.test5();
		service.test6();
	}
}
