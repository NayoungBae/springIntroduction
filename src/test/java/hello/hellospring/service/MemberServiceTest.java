package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /* 직관적인 것이 주 목적이기 때문에 함수명을 한글로 해도 무방함 */
    /* 빌드 시 테스트코드는 실제 코드에 포함되지 않음 */

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hihi");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member, findMember);
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //이 로직 실핼 시 이런 에러를 기대한다
        IllegalStateException e = assertThrows(IllegalStateException.class,
                                                () -> memberService.join(member2));

        //then
        //메시지 검증
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
    }

    @Test
    void 모든회원찾기() {
    }

    @Test
    void 회원한명찾기() {
    }
}