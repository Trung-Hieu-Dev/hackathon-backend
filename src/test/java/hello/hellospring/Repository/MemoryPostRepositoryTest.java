package hello.hellospring.Repository;

import hello.hellospring.domain.Post;
import hello.hellospring.repository.MemoryPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryPostRepositoryTest {
    private final MemoryPostRepository memoryPostRepository = new MemoryPostRepository();
    
    @AfterEach
    public void afterEach() {
        memoryPostRepository.clearStore();
    }
    
    @Test
    public void save() {
        Post post = new Post("Title 1", "Content 1");
        memoryPostRepository.save(post);
        Post result = memoryPostRepository.findById(post.getId()).get();
        Assertions.assertThat(result).isEqualTo(post);
    }
    
    @Test
    public void findAll() {
        Post post1 = new Post("Post 1", "Content 1");
        memoryPostRepository.save(post1);
        Post post2 = new Post("Post 2", "Content 2");
        memoryPostRepository.save(post2);
        
        List<Post> result = memoryPostRepository.findAll();
        
        Assertions.assertThat(result.size()).isEqualTo(2);
        
    }
    
    @Test
    public void findById() {
        Post post1 = new Post("Post 1", "Content 1");
        memoryPostRepository.save(post1);
        Post post2 = new Post("Post 2", "Content 2");
        memoryPostRepository.save(post2);
        
        Post post = memoryPostRepository.findById(post1.getId()).get();
        
        Assertions.assertThat(post).isEqualTo(post1);
    }
}
