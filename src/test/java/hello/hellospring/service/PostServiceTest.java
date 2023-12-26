package hello.hellospring.service;

import hello.hellospring.domain.Post;
import hello.hellospring.repository.MemoryPostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceTest {
    PostService postService;
    MemoryPostRepository memoryPostRepository;
    
    @BeforeEach
    void
    beforeEach() {
        this.memoryPostRepository = new MemoryPostRepository();
        this.postService = new PostService(this.memoryPostRepository);
    }
    
    @AfterEach
    void
    afterEach() {
        memoryPostRepository.clearStore();
    }
    
    @Test
    void join() {
        // given
        Post post = new Post();
        post.setTitle("post A");
        post.setContent("content A");
        
        // when
        Long savedId = postService.join(post);
        
        // then
        Post findPost = memoryPostRepository.findById(savedId).get();
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
    
    @Test
    void findOne() {
    }
}