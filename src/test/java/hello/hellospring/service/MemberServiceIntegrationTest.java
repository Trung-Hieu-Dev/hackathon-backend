package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    
    @Autowired MemberService memberService;
    @Autowired MemberRepository MemberRepository;
    
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("memberA");
        
        // when
        Long savedId = memberService.join(member);
        
        // then
        Member findMember = MemberRepository.findById(savedId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        
    }
    
    @Test
    void duplicateMemberException() {
        // given
        Member member1 = new Member();
        member1.setName("memberA");
        
        Member member2 = new Member();
        member2.setName("memberA");
        
        // when
        memberService.join(member1);
        
        // then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
    
    @Test
    void findMembers() {
        //...
    }
    
    @Test
    void findOne() {
        //....
    }
}