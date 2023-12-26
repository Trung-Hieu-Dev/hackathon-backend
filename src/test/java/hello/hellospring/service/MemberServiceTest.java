package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;
    
    @BeforeEach // run before every @Test
    void beforeEach() {
        this.memoryMemberRepository = new MemoryMemberRepository();
        this.memberService = new MemberService(this.memoryMemberRepository);
    }
    
    @AfterEach
    void afterEach() {
        memoryMemberRepository.clearStore();
    }
    
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("memberA");
        
        // when
        Long savedId = memberService.join(member);
        
        // then
        Member findMember = memoryMemberRepository.findById(savedId).get();
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