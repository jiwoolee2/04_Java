package section04.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class CharService {


	/* [문자 기반 스트림]
	 * 
	 * - 2byte 문자(char) 단위로 입/출력하는 스트림
	 * 
	 * - 문자만 작성된 파일(txt, bin 등),
	 * 	 채팅, JSON, 웹 요청(주소)/응답(HTML)
	 * 
	 * - 문자 기반 스트림 최상위 인터페이스 : Reader(입력), Writer(출력)
	 */
	
	
	/** 문자 기반 스트림을 이용한 파일 출력 */
	public void fileOutput1() {
		
		FileWriter fw = null;
		
		try {
			String content = """
		
존재하는 이유
그런 건 아무래도 좋으니
그리 즐겁지도 괴롭지도 않은
바람아 불어라

달을 찾는 이유
예쁜 건 언제 봐도 좋으니
나는 세계의 시계를 부수고
너에게 닿는다

너와 함께 바라본
붕괴하는 세상의 반짝임을
그 찰나를 별의 시작이라고 부를 거야

시간의 중력으로는 우리의 모든 이야기를
따라잡을 수 없을 테니까

시작의 푸름에
모든 이름에
네가 새겨져있을 뿐
낮과 밤을 지나 새벽 속에도
잠들지 않는 아이 아이야

열 번의 기적처럼
널 가득 안은 채
그대로 멈춰라
내 하루에 번져가는 시작의 너

달이 예쁘다고
네게 말해줬던 그 밤은
너무 뜨겁지도 아쉽지도 않은
고요함이었지


너를 찾은 이유
어쩌면 찾지 않았을지도
사실 언제 만났어도 지금처럼 너를 좋아했을 거야

너와 함께 바라본
마주하는 눈빛의 반짝임을
그 찰나를 시작의 별이라고 부를 거야

처음 느낌 그대로
우리의 모든 이야기를
완성해낼 수 있을 테니까

시작의 푸름에
모든 이름에
네가 새겨져있을 뿐
낮과 밤을 지나 새벽 속에도
잠들지 않는 아이 아이야
백야의 하늘 아래
널 가득 안은 채
그대로 멈춰라 내 하루에 번져가는 시작의 너

숨 쉴 수 있는 순간에 한 번 더 너를 안고
그 품이 그리워 미래에 더 아파할 거야
언젠가 시간보다 늦었던 마음이 밉지 않도록
천 개의 바람으로 날아가 흩어지기 전에

내 모든 이름에
꿈의 흐름에
네가 새겨져 있는 걸
숨과 바람 사이 영원속에도
잠들지 않는 아이 아이야
만개의 계절 속에
태어나는 시작의 푸름으로 부르는 노래
널 사랑하는 나의 마음이야

숨 쉴 수 있는 순간에 한 번 더 너를 안고
그 품이 그리워 더 아파할 거야
""";
			
			// 폴더가 없을 경우 생성
			String path = "io_test/char";
			File folder = new File(path);
			if(!folder.exists()) folder.mkdirs();
			
			// 문자 기반 파일 출력 스트림 객체 생성
			fw = new FileWriter(folder + "/" + "시작의 아이 가사.txt");
			
			// 문자 기반 스트림을 이용해서 출력하기
			fw.write(content); // String,char[] 출력을 위한 기능 제공
			
			// 스트림에 남은 데이터를 밀어냄
			fw.flush(); // close() 시 자동 수행됨/ 굳이 안써도..
			
			System.out.println("출력완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(fw!=null) fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	/** 문자 기반 스트림 + 보조스트림을 이용한 파일 출력 */
	public void fileOutput2() {
		
		FileWriter fw = null;
		// buffer : 데이터를 모아둔 메모리상의 공간
		// 데이터를 모아서 한번에 전달하여 효율성 높임
		BufferedWriter bw = null; // 버퍼를 이용한 보조 스트림
		
		try {
			String content = """
		
					존재하는 이유
					그런 건 아무래도 좋으니
					그리 즐겁지도 괴롭지도 않은
					바람아 불어라
					
					달을 찾는 이유
					예쁜 건 언제 봐도 좋으니
					나는 세계의 시계를 부수고
					너에게 닿는다
					
					너와 함께 바라본
					붕괴하는 세상의 반짝임을
					그 찰나를 별의 시작이라고 부를 거야
					
					시간의 중력으로는 우리의 모든 이야기를
					따라잡을 수 없을 테니까
					
					시작의 푸름에
					모든 이름에
					네가 새겨져있을 뿐
					낮과 밤을 지나 새벽 속에도
					잠들지 않는 아이 아이야
					
					열 번의 기적처럼
					널 가득 안은 채
					그대로 멈춰라
					내 하루에 번져가는 시작의 너
					
					달이 예쁘다고
					네게 말해줬던 그 밤은
					너무 뜨겁지도 아쉽지도 않은
					고요함이었지
					
					
					너를 찾은 이유
					어쩌면 찾지 않았을지도
					사실 언제 만났어도 지금처럼 너를 좋아했을 거야
					
					너와 함께 바라본
					마주하는 눈빛의 반짝임을
					그 찰나를 시작의 별이라고 부를 거야
					
					처음 느낌 그대로
					우리의 모든 이야기를
					완성해낼 수 있을 테니까
					
					시작의 푸름에
					모든 이름에
					네가 새겨져있을 뿐
					낮과 밤을 지나 새벽 속에도
					잠들지 않는 아이 아이야
					백야의 하늘 아래
					널 가득 안은 채
					그대로 멈춰라 내 하루에 번져가는 시작의 너
					
					숨 쉴 수 있는 순간에 한 번 더 너를 안고
					그 품이 그리워 미래에 더 아파할 거야
					언젠가 시간보다 늦었던 마음이 밉지 않도록
					천 개의 바람으로 날아가 흩어지기 전에
					
					내 모든 이름에
					꿈의 흐름에
					네가 새겨져 있는 걸
					숨과 바람 사이 영원속에도
					잠들지 않는 아이 아이야
					만개의 계절 속에
					태어나는 시작의 푸름으로 부르는 노래
					널 사랑하는 나의 마음이야
					
					숨 쉴 수 있는 순간에 한 번 더 너를 안고
					그 품이 그리워 더 아파할 거야
					""";
			
			// 폴더가 없을 경우 생성
			String path = "io_test/char";
			File folder = new File(path);
			if(!folder.exists()) folder.mkdirs();
			
			// 문자 기반 파일 출력 스트림 객체 생성
			fw = new FileWriter(folder + "/" + "시작의 아이 가사_buffer.txt");
			
			// 보조 스트림 객체 생성
			bw = new BufferedWriter(fw);
			
			// 문자 기반 보조 스트림을 이용해서 출력하기
			bw.write(content); // String,char[] 출력을 위한 기능 제공
			
			// 스트림에 남은 데이터를 밀어냄
			bw.flush(); // close() 시 자동 수행됨/ 굳이 안써도..
			
			System.out.println("출력완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(fw!=null) bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	/**
	 *  문자 기반 입력 스트림 + 보조 스트림 이용해서 파일 읽어오기
	 */
	public void fileInput() {
		
		/* [try - width - resource]
		 * - 자바 7버전(2011년)에서 추가된 기능
		 * - finally에서 작성하던 close 처럼 구문을
		 * 	 자동으로 수행하도록 만드는 구문
		 */
		String path = "io_test/char/시작의 아이 가사.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			// 보조 스트림을 이용해서 파일 내용 읽어오기
			
			StringBuilder sb = new StringBuilder(); // 읽어온 내용 누적
			String temp = null; // 임시 변수
			
			while(true) {
				temp = br.readLine(); // 한 줄씩 읽어오기(\n 전까지 읽어옴)
				
				if(temp == null) break; // 읽어온 내용이 없으면 반복 종료
				
				sb.append(temp);
				sb.append("\n"); // 줄바꿈 추가
			}
			
			System.out.println("읽어오기 성공");
			System.out.println("---------------------");
			System.out.println(sb.toString());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} // finally에서 close 하던 구문 생략 가능	
	}
	
	
	
	// Scanner 대신 스트림을 이용한 문자열 입력 받기
	// -> Scanner가 편리하긴 하지만 매우 느림!!
	
	
	// 키보드 입력 -> 바이트(2진수) 코드 입력 -> 문자 변환
	
	public void keyboardInput() {
		
		// System.in : 기본 장치(키보드)와 연결된 InputStream 반환
		
		// InputStreamReader : 바이트 스트림 -> 문자 스트림으로 변환
		try(BufferedReader br = 
				new BufferedReader(new InputStreamReader(System.in))) {
			// BufferedRead:문자기반, system.in:바이트기반
			
			System.out.print("문자열 입력 1 : ");
			String input1 = br.readLine(); // 입력된 한 줄 읽어오기
					
			System.out.print("문자열 입력 2 : ");
			String input2 = br.readLine(); // 입력된 한 줄 읽어오기
					
			System.out.println("input1 : " + input1);		
			System.out.println("input2 : " + input2);		
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
