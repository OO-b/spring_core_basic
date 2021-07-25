package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {
// 테스트의 경우 직관적으로 보기위해 한글로 적는경우 많음

    MemoryMemberRepository memberRepository;
    MemberService memberService;


    //각 테스트를 시행하기 전에
    @BeforeEach
    public void beforeEach() {

        //이렇게 하면 같은 memberRepository가 사용됨
        //이렇게 Memberservice 입장에서는 자기가 직접 new하지 않고, 외부에서 new 해서 넣어주는 이 경우를 Dependency Injection DI 라고 함

        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); //repository 저장소를 다 지움
    }

    @Test
    void 회원가입() {
        //given - when - then : 이런상황이 주어져서 이걸 실행했을때, 결과가 이게 나와야 해.

        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회원_예외() {
        //given
            Member member1 = new Member();
            member1.setName("spring");

            Member member2 = new Member();
            member2.setName("spring");

        //when
            memberService.join(member1);
            //Junit 예외처리하는 방법
            IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

            // assertThrows(NullPointerException.class, () -> memberService.join(member2));


//            try {
//                memberService.join(member2);
//                fail("예외발생");
//            }catch(IllegalStateException e){
//                assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            }



        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}