package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class MemoryMemberRepositoryTest {
    //Test를 클래스 단위로 돌릴때는 method별 순서가 보장되지 않음

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //각 method가 끝날때 마다 동작을 하는 함수 = callback method
    // test는 서로 의존관계 없이 테스트가 이루어져야함
    @AfterEach
    public void afterEach(){
        repository.clearStore(); //repository 저장소를 다 지움
    }


    @Test
    public void save() {

        Member member = new Member(); //멤버객체 생성
        member.setName("spring"); // 이름 스프링으로 지정

        repository.save(member); // repository에 저장
        Member result = repository.findById(member.getId()).get();  //repository에서 id

        /* 검증방법 1   sysout 으로 확인 비교연산자로 비교  */
//        System.out.println("result =" + (result == member));


        /* 검증방법 2   Assertions.assertEquals 로 확인  */
         //assertEquals(member, result); //아무 error가 나지 않는다면 ok.
         //Assertions.assertEquals(member, null);

         // 오류의 경우 아래와같이 기대한 값은 Expected 인데, Actual의 값이야. 라는 의미
         //Expected :hello.hellospring.domain.Member@1a677343
         //Actual   :null



        /* 검증방법 3   assertThat 으로 확인    */

        //member가 result랑 똑같애?
         assertThat(member).isEqualTo(result); //아무 error가 나지 않고 true라면 아무것도 뜨지않음

         //assertThat(member).isEqualTo(null);
         //error인 경우
//        org.opentest4j.AssertionFailedError:
//        expected: null
//        but was : hello.hellospring.domain.Member@1649b0e6
//        Expected :null
//        Actual   :hello.hellospring.domain.Member@1649b0e6

    }




    @Test
    public void findByName() {    //이름으로 찾기 테스트
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();    //shift + f6 동일하게 이름 변경 가능 key
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }




}
