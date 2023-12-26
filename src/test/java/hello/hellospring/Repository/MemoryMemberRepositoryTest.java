package hello.hellospring.Repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    
    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore(); // clear store for every @Test methods to keep store empty, to make testing correctly
    }

    @Test
    public void save() {
        // given:
        Member member = new Member();
        member.setName("Spring!");
        
        // when:
        memoryMemberRepository.save(member);
        
        
        // then:
        Member result = memoryMemberRepository.findById(member.getId()).get();
        
        Assertions.assertThat(member).isEqualTo(result);
        
    }
    
    @Test
    public void findByName() {
        // given
        // save 2 member
        Member member1 = new Member();
        member1.setName("Member A");
        memoryMemberRepository.save(member1);
        
        Member member2 = new Member();
        member2.setName("Member B");
        memoryMemberRepository.save(member2);
        
        // when
        // findByName
        Member result = memoryMemberRepository.findByName("Member A").get();
        
        
        // then
        // true member == find member
        Assertions.assertThat(result).isEqualTo(member1); // the result must be equal to initial
    }
    
    @Test
    public void findByAll() {
        // given
        // save 2 member
        Member member1 = new Member();
        member1.setName("Member A");
        memoryMemberRepository.save(member1);
        
        Member member2 = new Member();
        member2.setName("Member B");
        memoryMemberRepository.save(member2);
        
        // when
        // findAll
        List<Member> result = memoryMemberRepository.findAll();
        
        // then
        // count of members == 2
        Assertions.assertThat(result.size()).isEqualTo(2);
        
    }
}
