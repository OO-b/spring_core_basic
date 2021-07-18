package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {

		SpringApplication.run(HelloSpringApplication.class, args);
		//main 메서드 실행시 @SpringBootApplication 이 알아서 띄우면서 내장중인 tomcat 서버를 자체적으로 띄우면서 spring boot가 같이 올라옴
	}

}
