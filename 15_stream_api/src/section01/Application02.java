package section01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Application02 {

	public static void main(String[] args) {
		
		/* 스트림 생성 방법 */
		
		// 1) 배열 -> Stream
		String[] names = {"루피","조로","상디"};
		Stream<String> nameStream = Arrays.stream(names);
		
		// 스트림 + 메서드 참조
		nameStream.forEach(System.out::println);
		
		// java.lang.IllegalStateException -> 잘못된 상태
		// stream has already been operated upon or closed -> 스트림은 일회용!
		// nameStream.forEach(System.out::println); 오류발생
		
		
		System.out.println();
		
		
		// 2) 컬렉션 -> Stream
		List<String> animals
			= Arrays.asList("dog","cat","cow","rat","pig");
			// 고정된 길이의 list 생성 및 요소 추가 방법
		
		Stream<String> animalStream = animals.stream();
		
		animalStream.filter(animal -> animal.startsWith("c"))
								.forEach(System.out::println);
		
		
		// 3) Stream 객체 생성
		IntStream intStream1 = IntStream.range(3, 7); // 3이상 7미만
		IntStream intStream2 = IntStream.rangeClosed(3, 7); // 3이상 7미만
		
		System.out.println();
		intStream1.forEach(System.out::print);
		
		System.out.println();
		intStream2.forEach(System.out::print);
		
		LongStream longStream = LongStream.range(1, 9);
		System.out.println();
		longStream.forEach(num->System.out.print(num+" "));
		
		
		DoubleStream doubleStream = DoubleStream.of(3.14, 9.99);
		double sum = doubleStream.reduce(0,(a,b) -> a+b);
		System.out.println("합계 : "+sum);
		
		
		Stream<String> fruitStream = Stream.of("사과","딸기","바나나");
		fruitStream.forEach(System.out::println);
		
		
		
		
		
		
		
		
		
		
	}
}
