package hello.hellospring.service;


import hello.hellospring.domain.Post;
import hello.hellospring.repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private final PostRepository postRepository;
    
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    
    public Long join(Post post) {
        // check duplicate post name
        validateDuplicatePost(post);
        
        // if not duplication, saving post
        postRepository.save(post);
        
        // return the id of post
        return post.getId();
    }
    
    private void validateDuplicatePost(Post post) {
        postRepository.findByName(post.getTitle()).ifPresent(m -> {
            throw new IllegalStateException("This post already exists.");
        });
    }
    
    public List<Post> findPosts() {
        return postRepository.findAll();
    }
    
    public Optional<Post> findOne(Long postId) {
        return postRepository.findById(postId);
    }
}
