package section02.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ByteService {

	/* Stream(스트림)
	 * - 데이터가 이동하는 통로 역할의 객체
	 * - 데이터의 흐름은 기본적으로 단방향
	 */
	
	/* [Byte 기반 스트림]
	 * 
	 * - 1byte 단위로 데이터를 입/출력하는 스트림
	 * 
	 * - 최상위 클래스 : INputStream, OutputStream
	 * 
	 * - 입/출력 가능 데이터
	 * 문자열, 파일(이미지, 영상, 오디오, pdf 등 모든 파일)
	 * 
	 * * 단, 2byte 범위 문자열이 꺠질 수 있고
	 * 	좁은 통로(1byte)를 이용하다보니 속도가 상대적으로 느림
	 * 
	 * ******************************************************
	 * Stream 객체는 Java 프로그램이 종료 되어도 
	 * 외부 장치/파일/프로그램과의 연결이 유지되기 때문에
	 * 자동으로 사라지지 않는다!!!
	 * -> 프로그램 종료 전 
	 * 		Stream 객체의 메모리를 반환하는 close() 구문 필수!
	 * ******************************************************
	 */
	
	
	/**
	 * 지정된 경로에
	 * 파일 생성 + 내용 출력(파일 기반 byte stream 이용) 
	 */
	public void fileByteOutput() {
		
		// fox 참조 변수를 try, finally에서 모두 사용할 수 있게 바깥쪽에 선언
		FileOutputStream fos = null;
		
		try {
			// 폴더 경로, 파일명 지정
			String folderPath = "io_test/byte"; // 프로젝트폴더/io_test/byte
			String fileName   = "바이트기반테스트.txt";
			
			// 폴더가 없으면 생성
			File folder = new File(folderPath);
			
			if(folder.exists()==false) {
				folder.mkdirs(); // 폴더 생성
			}
			
			// 출력할 파일과 연결된 출력용 스트림 객체를 생성
			// -> 이 때, 해당 파일이 없으면 자동으로 생성된다!!
			fos = new FileOutputStream(folderPath + "/" + fileName,true);			
					// io_test/byte/바이트기반테스트.txt // 이어쓰기 옵션(true)	
			
			// txt 파일에 출력할 내용 작성
			StringBuilder builder = new StringBuilder();
			
			/* StringBuilder : String의 불변성 문제를 해결하기 위한 객체 */
			builder.append("Hello Wordl!!\n");
			builder.append("1234567890\n");
			builder.append("!@#$%^&*()\n");
			builder.append("와 완전 쉽죠??\n");
			builder.append("자바 너무 재밌어요!!\n");
			builder.append("대답해봐요! 재밌죠?\n");
			
			// StringBuilder에 저장된 내용을 String으로 변경
			String content = builder.toString();
			
			/* 연결된 외부 txt파일로 content 내용을 출력 */
			
			// 방법1) 1byte씩 끊어서 출력
//			for(int i=0 ; i<content.length() ; i++) {
//				char ch = content.charAt(i); // i번째 문자 1개 반환
//				
//				// 바이트 기반 스트림 -> 1바이트 범위만 출력 가능
//				// -> 0~255(바이트 범위) 사이 글자는 정상 출력
//				//    이후 글자(한글 등)은 모두 꺠져서 출력
//				
//				fos.write(ch); // IOException 발생 가능성
//			}
			
			// 방법2) String을 byte로 먼저 변환한 후
			// 				손실되는 데이터 없이 출력하기
			byte[] bytes = content.getBytes(); // String -> byte[] 형태로 얻어오기
			
			fos.write(bytes); // 손실되는 데이터 없이 모든 문자열(byte) 출력
			
			
			System.out.println("출력 완료");
						
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			try {
				
				if(fos != null) { // NullPointerException 방지
					fos.close();    // IOException 발생 가능성 있음
					}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	/** 보조 스트림 중 BufferedOutputStream을 이용해서
	 * 출력 속도 향상시키기
	 */
	public void bufferedFileByteOutput() {
		
		/* 버퍼(buffer)란?
		 * - 데이터가 모아져있는 메모리상의 임시 공간
		 * == 장바구니
		 * -> 한 번에 많은 양의 데이터를 이동시켜서
		 *    시간 효율을 좋아지게 만듦
		 */
		
		/* 스트림 객체 참조 변수 선언 */
		FileOutputStream fos		 = null; // 기반 스트림(단독 사용 O)
		BufferedOutputStream bos = null; // 보조 스트림(단독 사용 X)
		// -> 보조 스트림은 꼭 기반 스트림에 붙여서 사용!
		
		try {
			/* 기반 스트림 생성 */
			fos = new FileOutputStream("io_test/byte/버퍼_바이트.txt");
			// -> 파일이 없으면 생성됨
			
			/* 보조 스트림 생성 */
			// 보조 스트림 생성 시 기반 스트림과 연결
			bos = new BufferedOutputStream(fos);
			
			// """ """ : 내부에 작성된 모양 그대로의 문자열 생성 <pre>
			String content = """
You say it's changed
Show must go on, behave
오랜만에 옛 노래해
I'm feelin' like I never left
(That's right) I never left
But you ain't know, O.K then lights, camera
Act like you know
Don't play on me, no, we're
Airbnb, you're homeless
혼비백산-해진-미-장센 (Mise-en-scène)
도레미파시도 (Now, you know it)
두껍아 두껍아 came with the troops
뜯고 맛보고 즐기고 big bang when I shoot
King in the zoo, he gotta do what I do
One of one, not of them (Mirror)
Man in the views aimin' at you
Yeah, I'm aiming at a man, and amen, achoo
Bless you, all cleaned house, fu
Golden days are still alive
외롭다는 말하지 마
내가 있는 곳, 네가 있을 곳
The place that I belong
Home sweet home
Home sick home
Well I said, I would be back
And I'd never let you go
Pick a petal off a flower
Daze you love me nope?
Well I said, I would be back
And I'd never let you go
Pick a petal off a flower
Do you love me or (stop!)
Winner, winner chicken killer, 삼계탕 dinner
하나 둘 set down (one, two, step) 'fantastic'한 팀워크
Not mini, 많이 'More'
Rock, scissors, paper, toast
This is how we do it, just do it, let's do it y'all
Work, work 월화수목금토- 일
They gon' wait til' I'm gone
So I came, I saw, I won
G just D-word is my bond
나 무대로 올라, coup d'e shit
단숨에 호흡곤란, hook catch this
아 '무제' 도 몰라? bull as shit
Whatever, now or never
Golden days are still alive
외롭다는 말하지 마
네가 있을 곳에 내가 있는 걸
The place that I belong
Home sweet home
Home sick home
Well I said, I would be back
And i'd never let you go
Pick a petal off a flower
Daze you love me nope?
Well I said, I would be back
And i'd never let you go
Pick a petal off a flower
Do you love me or (stop!)
We alike dead or alive, your life? Still life
It's so nice, I missed you a lot
You're welcome back home, wherever you are
We alike dead or alive, your life is still with me
Livin' good life, day or nights
The highlight, it's about time to 'rock-on'
Home sweet home
Home sick home
Well, I said, I would be back
And I'd never let you go
Pick a petal off a flower
Daze, you love me, nope?
Well, I said, I would be back
And I'd never let you go
Pick a petal off a flower
Do you love me or (Stop)					
						
""";
			
			// 보조 스트림을 이용해서 출력
			// -> 내용 출력 시 버퍼를 이용해서 한 번에 출력!
			 //=> 
			bos.write(content.getBytes());
			System.out.println("출력 완료");
			
			
			
		} catch (Exception e) { // 다형성 이용해서 모든 예외 처리
			e.printStackTrace();
		
		} finally {
			// 사용 완료된 스트림 객체 메모리 반환
			try {
				if(bos != null) bos.close();
				// 보조 스트림만 close 하면
				// 연결된 기반 스트림도 같이 close 된다!
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	/**
	 * 바이트 기반 파일 입력(파일 읽어오기) 1
	 */
	public void fileByteInput() {
		
		FileInputStream fis = null;
		
		try {
			// 파일 입력용 바이트 기반 스트림 생성
			fis = new FileInputStream("io_test/byte/노래가사.txt");
			
			int value = 0; // 읽어온 1byte fnf 저장할 임시 변수
			
			// 읽어온 내용 누적용 객체
			StringBuilder sb = new StringBuilder(); 
			
			while(true) { // 무한반복
				// 파일로부터 1 바이트 읽어서
				// - 호출 시 마다 다음 내용을 읽어옴
				// - 읽어올 내용이 없다면 01 반환
				value = fis.read();
				if(value ==1) break; // 반복 종료
				sb.append((char)value); // 문자로 변환해서 누적
			}
			
			System.out.println(sb.toString());
			
		} catch (Exception e) {
				e.printStackTrace();
				
		} finally {
			// 사용 완료된 스트림 객체 메모리 반환(close())
			try {
				fis.close();
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	/**
	 *  바이트 기반 파일 입력 2(한글 안깨지게 읽어오기)
	 */
	public void fileByteInput2() {
		
		FileInputStream fis = null;
		
		try {
			// 파일 입력용 바이트 기반 스트림 생성
			fis = new FileInputStream("io_test/byte/노래가사.txt");
			
			// byte[] readAllBytes()
			// - 연결된 파일의 내용을 모두 byte로 변환하여 읽어오기
			// - byte[] 형태로 반환됨
			byte[] bytes = fis.readAllBytes();
			
			String content = new String(bytes); // byte[]을 String에 그대로 저장
			
			System.out.println(content);
			
			
		} catch (Exception e) {
				e.printStackTrace();
				
		} finally {
			// 사용 완료된 스트림 객체 메모리 반환(close())
			try {
				fis.close();
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 *  보조 스트림(BufferedInputStream)을 이용해서
	 *  효율적으로 파일 읽어오기
	 */
	public void bufferedFileByteInput() {
		
		FileInputStream fis = null; // 기반 스트림
		BufferedInputStream bis = null;
		
		try {
			// 파일 입력용 바이트 기반 스트림 생성
			fis = new FileInputStream("io_test/byte/노래가사.txt");
			
			// 보조 스트림 생성
			bis = new BufferedInputStream(fis); // 보조 - 기반 연결됨
			
			// 보조 스트림을 이용해서
			// 파일 내용 -> 바이트로 변환 -> 버퍼를 이용해 한 번에 읽어옴
			// -> byte[]로 반환
			byte[] bytes = bis.readAllBytes();
			String content = new String(bytes); // byte[]을 String에 그대로 저장
			System.out.println(content);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 사용 완료된 스트림 객체 메모리 반환(close())
			try {
				
				if(bis != null) bis.close();
				// 보조 스트림만 close 해도 연결된 기반 스트림도 close된다
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	// -------------------------------------------------------------
	
	/** [파일 복사]
	 * <pre>
	 * 	- 복사할 파일의 경로를 입력 받아
	 * 	  같은 경로에 복사된 파일을 생성
	 * 
	 *  - 입력 받은 경로에 파일이 존재하지 않으면
	 *   -> "파일이 존재하지 않습니다" 출력
	 *  - 존재하면
	 *   -> 같은 경로에 "파일명_copy.확장자" 이름으로 출력
	 *   
	 * [실행화면]
	 * 파일 경로 입력 : io_test/byte/노래가사.txt
	 * 복사 완료 : io_test/byte/노래가사_copy.txt
	 * </pre>
	 */
	public void fileCopy() {
		
		// 스캐너, 파일 입출력 스트림, 보조스트림 참조 변수 선언
		
		Scanner sc = null; 								// 경로 입력용
		
		FileInputStream 		 fis = null;	// 입력 기반
		FileOutputStream     fos = null;	// 출력 기반
		
		BufferedInputStream  bis = null;  // 입력 보조
		BufferedOutputStream bos = null;  // 출력 보조
		
		try {
			
			sc = new Scanner(System.in);
			
			// 1) 파일 경로 입력 받기
			System.out.print("파일 경로 입력 : ");
			String path = sc.nextLine();
			
			// 2) 입력 받은 경로의 파일과 FileInputStream 연결하기
			fis = new FileInputStream(path);
			// path에 파일이 없다면 FileNotFoundException 오류 발생 -> catch에서 처리
			
			// 3) 보조 입력용 스트림 생성
			bis = new BufferedInputStream(fis);
			
			// 4) 연결된 파일을 byte[] 읽어오기
			byte[] bytes = bis.readAllBytes(); // == 읽어온 파일!
			
			// 5) 입력 받은 경로 파일명에 _copy 붙이기
			StringBuilder sb = new StringBuilder();
			sb.append(path); // 기존 경로 추가
			
			// 뒤에서부터 검색하여 처음 찾은 "." 위치(index) 반환
			int insertPoint = sb.lastIndexOf(".");
			
			// 찾은 "." 앞에 "_copy" 삽입하기
			sb.insert(insertPoint,  "_copy");
			
			// 복사본의 경로
			String copyPath = sb.toString();
			
			// 6) 출력용 기반 스트림 객체 생성
			fos = new FileOutputStream(copyPath);
			
			// 7) 출력용 보조 스트림 객체 생성
			bos = new BufferedOutputStream(fos);
			
			// 8) 보조 스트림을 이용해서 출력하기
			bos.write(bytes); // 읽어온 파일(bytes) 출력하기
			
			System.out.println("복사 완료 : "+copyPath);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다");
			
			
		} catch (Exception e) {
			
			
		}
		
		finally {
			
			// 스트림 객체 메모리 반환
			try {
				if(bis != null) bis.close();
				if(bos != null) bos.close();
				if(sc  != null) sc.close();	
			} catch (IOException e) {
				e.printStackTrace(); // 어떤 줄에서 에러가 발생했는지 추적
			}
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
