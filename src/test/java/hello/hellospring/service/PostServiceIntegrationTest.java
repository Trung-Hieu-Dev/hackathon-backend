package hello.hellospring.service;

import hello.hellospring.domain.Post;
import hello.hellospring.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class PostServiceIntegrationTest {
    @Autowired PostService postService;
    @Autowired PostRepository postRepository;
    
    @Test
    void join() {
        // given
        Post post = new Post();
        post.setTitle("post A");
        post.setContent("content A");
        
        // when
        Long savedId = postService.join(post);
        
        // then
        Post findPost = postRepository.findById(savedId).get();
        assertThat(post.getTitle()).isEqualTo(findPost.getTitle());
    }
    
    @Test
    void findPosts() {
        // given
        Post post1 = new Post();
        post1.setTitle("post A");
        post1.setContent("content A");
        
        Post post2 = new Post();
        post2.setTitle("post A");
        post2.setContent("content A");
        
        // when
        postService.join(post1);
        
        // then
        assertThrows(IllegalStateException.class, () -> postService.join(post2));
    }
    
}