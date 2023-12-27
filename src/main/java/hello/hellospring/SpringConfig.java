package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.PostService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// create Beans obj
@Configuration
public class SpringConfig {
//    private final DataSource dataSource;
    private final EntityManager em;
    
    @Autowired // JDBC
    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
        this.em = em;
    }
    
    
    // MemberService Bean obj
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    
    
    // MemberRepository Bean obj
    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository(); // manually
        // return new JdbcTemplateMemberRepository(dataSource); // JDBC
        return new JpaMemberRepository(em); // JPA
    }
    
    // PostService Bean obj
    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }
    
    // PostRepository Bean obj
    @Bean
    public PostRepository postRepository() {
//        return new MemoryPostRepository();
//        return new JdbcTemplatePostRepository(dataSource);
        return new JpaPostRepository(em);
    }
}
