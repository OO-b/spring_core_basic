package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean //스프링 빈에 등록하라는 뜻
    public MemberService MemberService() {
        return new MemberService(MemberRepository());
    };

    @Bean
    public MemberRepository MemberRepository() {
        //MemberRepository 는 Interface이기 때문에 구현체인 MemoryMemberRepository를 return
        return new MemoryMemberRepository();

    }

}
