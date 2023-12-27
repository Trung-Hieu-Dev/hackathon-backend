package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// create Beans obj
@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    // MemberService Bean obj
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    
    
    // MemberRepository Bean obj
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository(); // manually
        return new JdbcTemplateRepository(dataSource); // using JdbcTemplate to work with DB
    }
    
    // PostService Bean obj
    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }
    
    // PostRepository Bean obj
    @Bean
    public PostRepository postRepository() {
        return new MemoryPostRepository();
    }
}
