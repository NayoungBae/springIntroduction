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
    void 모든회원찾기() {
    }

    @Test
    void 회원한명찾기() {
    }
}