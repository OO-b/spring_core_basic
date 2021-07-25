package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 서비스는 비지니스에 의존해서 설계하고
// 리포지토리는 단순히 기계적으로.. 개발스러운 용어로 씀

/*
* 서비스는 비지니스를 처지하는게 Role
* */

//@Service
public class MemberService {

        private final MemberRepository memberRepository;

        //@Autowired
        public MemberService(MemberRepository memberRepository) {

            this.memberRepository = memberRepository;
        }

        /*
        * 회원가입
        */
        public Long join(Member member) {
            //같은 이름이 있는 중복 회원은 X

            validateDuplicateMember(member); //중복회원 걸러내는 method

            memberRepository.save(member);
            return member.getId();
        }

        private void validateDuplicateMember(Member member) {

            // ifPresent : null이 아니라 값이 있으면 (optional 안에 여러 method 중 하나)
            // 기존에는 if null이 아니면 으로 로직을 구성했을것이지만 Optional 이기 때문에 가능
            // findbyname을 해. 그 결과는 Optional<Member>니까 바로 ifPresent

            memberRepository.findByName(member.getName())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
        }


        /*
        * 전체 회원 조회
       */
        public List<Member> findMembers(){
            return memberRepository.findAll();
        }

        public Optional<Member> findOne(Long memberId) {
            return memberRepository.findById(memberId);
        }


}
