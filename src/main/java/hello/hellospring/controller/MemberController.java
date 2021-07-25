package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링이 뜰때 스프링 컨테이너라는 통이 생김. 거기에 @Controller 이 있으면 MemberController 객체를 생성해서 spring에 넣어 둠. 이후 spring이 관리
// = 스프링 컨테이너에서 스프링 bean이 관리된다는 이야기
@Controller
public class MemberController {

    private final MemberService memberService;

    // 스프링 컨테이너에 등록하는 행위
    // 생성자에 @Autowired 가 있으면 spring container에 memberService를 가져다가 연결시킴
    // 이후 service에 @Service를 추가해줘야함(이를 추가해야 spring이 container에 등록해줄 수 있음) @Repository도 마찬가지


    @Autowired //의존성주입 1. 생성자주입 (가장 많이 사용) - 요즘 권장하는 방식
    public MemberController(MemberService memberService) { //Dependency Injection
        this.memberService = memberService;
    }


    //@Autowired MemberService memberService; //의존성주입 2. 필드주입


    // 의존성주입 3. setter 주입
    // public 으로 열어둬야하는데 거의 바꿀일이 없음
    // setter를 사용하다보니 누군가가 접근할 수 있도록 열려있음

    //    public MemberService setMemberService(MemberService memberService) {
    //        this.memberService = memberService;
    //    }
}





// 컴포넌트 스캔 : 스프링이 올라올때 Component annotation이 있으면 다 객체로 생성해서 스프링 컨테이너에 등록
//( 컴포넌트 스캔 범위 : main함수가 있는 패키지 내부만 스캔)
// Autowired는 서로 연결해줌
