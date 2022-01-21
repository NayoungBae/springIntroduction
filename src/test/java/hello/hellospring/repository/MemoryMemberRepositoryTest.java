package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /*
    - 테스트코드 실행 시 순서가 보장되지 않음
      findByName() 함수 실행 시 에러가 났던 이유는
      이전에 실행한 코드에서 쓰였던 데이터가 저장소에 남아있기 때문
      그래서 함수 실행이 끝날 때마다 저장소에 있는 데이터를 비워줘야 함
    */

    /* 테스트끼리는 의존관계가 없어야 한다 */

    @AfterEach //하나의 테스트가 끝난 뒤 실행
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertEquals(result, member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertEquals(member1, result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertEquals(result.size(), 2);


    }

}