package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ctrl + b: 정의 탐색
// @SpringBootApplication는 spring boot의 시작 정보(설정)가 들어 있다
// 설정 안에 @ComponentScan이 들어 있다
// 컴포넌트 스캔은 다음 5가지를
// @Component: 컴포넌트 스캔에서 사용
// @Controller: 스프링 MVC 컨트롤러에서 사용
// @Service: 스프링 비즈니스 로직에서 사용
// @Repository: 스프링 데이터 접근 계층에서 사용
// @Configuration: 스프링 설정 정보에서 사용
// 스캔 대상에 포함하는데, 이는 각각 설정 안에 @ComponentScan이 들어있어서 가능한 것이다
@SpringBootApplication
public class CoreApplication
{
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
}
