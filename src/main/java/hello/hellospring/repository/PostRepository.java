package hello.hellospring.repository;

import hello.hellospring.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    
    List<Post> findAll();
    
    Optional<Post> findById(Long id);
    
    Optional<Post> findByName(String name);
}
