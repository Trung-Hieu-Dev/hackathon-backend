package hello.hellospring.Repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {
    
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

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
}
