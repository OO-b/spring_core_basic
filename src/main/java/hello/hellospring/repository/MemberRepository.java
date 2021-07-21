package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장이 됨
    Optional<Member> findById(Long id); //저장소에서 find로 찾아올 수 있음
    Optional<Member> findByName(String name); //저장소에서 find로 찾아올 수 있음
    List<Member> findAll(); //저장된 모든 회원 리스트를 반환


    //Optional java 8에 들어간 기능
    //findById, findByName의 값이 null 일 수 있으니 null처리를 위해

}
