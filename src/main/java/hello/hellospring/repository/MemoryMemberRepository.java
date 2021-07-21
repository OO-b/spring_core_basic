package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
       member.setId(++sequence); //아이디셋팅
       store.put(member.getId(), member); //스토어 저장
       return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return store.get(id); //과거에는 이렇게 했으나, 혹 null이 반환될 경우가 있을 수 있으니
        return Optional.ofNullable(store.get(id));  //Optional.ofNullable으로 감싸면 null이여도 클라이언트쪽에서 사용 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
                //넘어온 name이 store에 있는 name이랑 같은지 확인
    }

    @Override
    public List<Member> findAll() {
        //store의 type은 map이지만, return type은 list 자바 실무에서는  list 반환을 많이함. loop로 돌리기 편해서
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear(); //비우는 함수
    }

}
