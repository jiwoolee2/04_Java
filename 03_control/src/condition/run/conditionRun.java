package condition.run;

// 다른 패키지에 있는 클래스 가져오기
import condition.service.conditionService;

public class conditionRun {


	public static void main(String[] args) {
		
		//  
		conditionService service = new conditionService();
	
//		service.method1();
//		service.method2();
//	  service.method3();
//    service.displayMenu();
		  service.grade();
	}
}

