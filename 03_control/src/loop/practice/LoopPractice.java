package loop.practice;

import java.util.Scanner;

public class LoopPractice {

	Scanner sc = new Scanner(System.in);
	
	/** 문제1
	 * 메소드 명 : public void practice1(){}
		사용자로부터 한 개의 값을 입력 받아 1부터 
		그 숫자까지의 숫자들을 모두 출력하세요.
		단, 입력한 수는 1보다 크거나 같아야 합니다.
		만일 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요“
	  를 출력하세요.
	 */
	public void practice1() {
		
		System.out.print("1이상의 숫자를 입력하세요 : ");
		int num = sc.nextInt();
		
		if(num<1) {
			System.out.println("1이상의 숫자를 입력해주세요");
		} else {
			for(int i=1 ; i<=num ; i++) {
				System.out.print(i+" ");
			}
		}
	}

	/** 문제2
	 * 메소드 명 : public void practice2(){}
		 사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 
		 모든 숫자를 거꾸로 출력하세요. 단, 입력한 수는 1보다 크거나 같아야 합니다.
	 */
	public void practice2() {
		
		System.out.println("1이상의 숫자를 입력하세요");
		int num = sc.nextInt();
		
		if(num<1) {
			System.out.println("1이상의 숫자를 입력해주세요");
		} else {
			for(int i=num ; i>0 ; i--) {
				System.out.print(i+" ");
			}
		}
	}
	
	/** 문제3
	 *  메소드 명 : public void practice3(){}
			1부터 사용자에게 입력 받은 수까지의 정수들의 합을 
			for문을 이용하여 출력하세요.
	 */
	public void practice3() {
		
		System.out.print("정수를 하나 입력하세요 : ");
		int num = sc.nextInt();
		
		String result = "";
		int sum = 0;
		for(int i=1 ; i<=num ; i++) {
			sum += i;
			if(i==num) {
				result += num+" = "+sum;
			} else {
			result += i+" + ";
			}
		}
		System.out.println(result);
	}
	
	/** 문제4
	 * 메소드 명 : public void practice4(){}
		 사용자로부터 두 개의 값을 입력 받아 
	 	 그 사이의 숫자를 모두 출력하세요.
		 만일 1 미만의 숫자가 입력됐다면 
		 “1 이상의 숫자를 입력해주세요“를 출력하세요.
	 */
	public void practice4() {
		
		System.out.print("첫 번째 숫자 : ");
		int num1 = sc.nextInt();
		System.out.print("두 번째 숫자 : ");
		int num2 = sc.nextInt();
	
		if(num1<1 | num2<1) {
			System.out.println("1 이상의 숫자를 입력해주세요");
			return;
		}
		
		if(num1>num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
		for(int i=num1 ; i<=num2 ; i++) {
			System.out.print(i+" ");
		}
	}
	
	/** 문제5
	 * 메소드 명 : public void practice5(){}
		 사용자로부터 입력 받은 숫자의 단을 출력하세요.
	 */
	public void practice5() {
		
		System.out.print("숫자 : ");
		int num = sc.nextInt();
		
		System.out.printf("===== %d단 =====\n",num);
		for(int i=1 ; i<=9 ; i++) {
			System.out.printf("%d * %d = %d\n",num,i,num*i);
		}
	}
	
	/** 문제6
	 * 메소드 명 : public void practice6(){}
		 사용자로부터 입력 받은 숫자의 단부터 9단까지 출력하세요.
		 단, 2~9를 사이가 아닌 수를 입력하면 
		 “2~9 사이 숫자만 입력해주세요”를 출력하세요.
	 */
	public void practice6() {
		
		System.out.print("숫자 : ");
		int num = sc.nextInt();
		
		if(num<2 | num>9) {
			System.out.println("2~9 사이 숫자만 입력해주세요");
		}
		for(int j=num ; j<10 ; j++) {
			System.out.printf("===== %d단 =====\n",j);
			for(int i=1 ; i<=9 ; i++) {
				System.out.printf("%d * %d = %d\n",j,i,j*i);
			}
		}
	}
	
	
	/** 문제7
	 * 메소드 명 : public void practice7(){}
	   다음과 같은 실행 예제를 구현하세요.
	 */
	public void practice7() {
		
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		
		for(int i=1 ; i<=num ; i++) {
			for(int j=1 ; j<=i ; j++) {
				System.out.print('*');
			}
			System.out.println();	
		}

	}
	
	
	/** 문제8
	 * 메소드 명 : public void practice8(){}
	   다음과 같은 실행 예제를 구현하세요.
	 */
	public void practice8() {
		
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		
		for(int i=num ; i>0 ; i--) {
			for(int j=1 ; j<=i ; j++) {
				System.out.print('*');
			}
			System.out.println();	
		}
	}
	
	
	/** 문제9
	 * 메소드 명 : public void practice9(){}
	   다음과 같은 실행 예제를 구현하세요.
	 */
	public void practice9() {
		
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		
		for(int i=1 ; i<=num ; i++) {
			for(int k=num-i ; k>=0 ; k--) {
				System.out.print(" ");
			}
			for(int j=1 ; j<=i ; j++) {

				System.out.print('*');
			}
			System.out.println();	
		}
	}
	
	/** 문제10
	 * 메소드 명 : public void practice10(){}
	   다음과 같은 실행 예제를 구현하세요.
	 */
	public void practice10() {
		
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		
		for(int i=1 ; i<=num ; i++) {
			for(int j=1 ; j<=i ; j++) {
				System.out.print('*');
			}
			System.out.println();	
			
		}
		for(int i=num-1 ; i>0 ; i--) {
			for(int j=1 ; j<=i ; j++) {
				System.out.print('*');
			}
			System.out.println();			
		}
	}
	
	/** 문제11
	 * 메소드 명 : public void practice11(){}
	   다음과 같은 실행 예제를 구현하세요.
	 */
	public void practice11() {
		
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		
		for(int i=1 ; i<=num ; i++) {
			
			// 앞쪽 공백 추가
			for(int blank=1 ; blank<=num-i ; blank++) {
				System.out.print(" ");
			}
			// 뒤쪽 별 추가
			for(int j=1 ; j<=2*i-1 ; j++) {
				System.out.print('*');
			}
			System.out.println();	
		}
	}
	
	/** 문제12
	 * 메소드 명 : public void practice12(){}
	   다음과 같은 실행 예제를 구현하세요.
	 */
	public void practice12() {
		
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		
		for(int i=1 ; i<=num ; i++) {
			
			if(i==1 | i==num) { //i가 1이나 num이면 *을 num만큼 출력
				for(int j=0 ; j<num ; j++) {
				System.out.print('*');
				}
				
			} else {
				for(int k=1 ; k<=num ; k++) {
					if(k==1 | k==num) {
						System.out.print('*');
					}else {
						System.out.print(" ");
					}
				}
			}
			System.out.println();	
		}
	}
	
	/** 문제13
	 * 메소드 명 : public void practice13(){}
		 1부터 사용자에게 입력 받은 수까지 중에서
		 1) 2와 3의 배수를 모두 출력하고
		 2) 2와 3의 공배수의 개수를 출력하세요.
	 */
	public void practice13() {
		
		System.out.print("자연수 하나를 입력하세요 : ");
		int num = sc.nextInt();
		String result = "";
		int count = 0;
		
		for(int i=1; i<=num ; i++) {
			if(i%2==0 | i%3==0) {
				result += i+" ";
			}
			if(i%2==0 && i%3==0) {
				count += 1;
			}
		}
		System.out.println(result+"\n"+"count : "+count);
	}
	
	/** 문제14
	 * 메소드 명 : public void practice14(){}
		 사용자로부터 입력 받은 하나의 값이 소수인지 판별하는 프로그램을 구현하세요.
     단, 입력한 수가 2보다 작은 경우 “잘못 입력하셨습니다.”를 출력하세요.
	 */
	public void practice14() {
		
		System.out.print("숫자 : ");
		int num = sc.nextInt();
		int count = 0;
		
		if(num<2) {
			System.out.println("잘못 입력하셨습니다.");
			return;
		}
		
		for(int i=1 ; i<=num ; i++) {
			if(num%i==0) {
				count++;
			}
		}
		if(count==2) {
			System.out.print("소수입니다.");
		}else {
			System.out.println("소수가 아닙니다.");
		}
	}
	
	/** 문제15
	 * 메소드 명 : public void practice15 (){}
		 위 문제와 모든 것이 동일하나, 입력한 수가 2보다 작은 경우
		 “잘못 입력하셨습니다.”를 출력하면서 다시 사용자가 값을 입력하도록 하세요.
	 */
	public void practice15() {
		
		System.out.print("숫자 : ");
		int num = sc.nextInt();
		int count = 0;
		
		if(num<2) {
			System.out.println("잘못 입력하셨습니다.");
			practice15();
		} 
		
		else {
		
		for(int i=1 ; i<=num ; i++) {
			if(num%i==0) {
				count++;
			}
		}
		if(count==2) {
			System.out.print("소수입니다.");
		}else {
			System.out.println("소수가 아닙니다.");
		 	}
		}
	}

	/** 문제16
	 * 메소드 명 : public void practice16(){}
	   2부터 사용자가 입력한 수까지의 소수를 모두 출력하고 
	   소수의 개수를 출력하세요.
		 단, 입력한 수가 2보다 작은 경우 “잘못 입력하셨습니다.”를 출력하세요.
	 */
	public void practice16() {
		
		System.out.print("숫자 : ");
		int num = sc.nextInt();
	
		int result = 0;
		
		if(num<2) {
			System.out.println("잘못 입력하셨습니다.");
			return;
		} 
		
		for(int j=2 ; j<=num ; j++) {
			int count = 0;
			for(int i=1 ; i<=j ; i++) {
				
				if(j%i==0) {
					count++;
				}	
			}
			if(count==2) {
				result++;
				System.out.print(j+" ");
			}	
		}
		System.out.printf("\n2부터 %d까지 소수의 개수는 %d개입니다.",num,result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
