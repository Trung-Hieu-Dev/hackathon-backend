package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// create Beans obj
@Configuration
public class SpringConfig {
    // MemberService Bean obj
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    
    
    // MemberRepository Bean obj
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
