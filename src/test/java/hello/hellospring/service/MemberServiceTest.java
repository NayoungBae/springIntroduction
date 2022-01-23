package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();

    /* 직관적인 것이 주 목적이기 때문에 함수명을 한글로 해도 무방함 */
    /* 빌드 시 테스트코드는 실제 코드에 포함되지 않음 */

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
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch(IllegalStateException e) {
            assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
        }

        //then
    }

    @Test
    void 모든회원찾기() {
    }

    @Test
    void 회원한명찾기() {
    }
}