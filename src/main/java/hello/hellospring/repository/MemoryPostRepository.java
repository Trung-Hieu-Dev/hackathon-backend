package hello.hellospring.repository;

import hello.hellospring.domain.Post;

import java.util.*;

public class MemoryPostRepository implements PostRepository{
    private static final Map<Long, Post> store = new HashMap<>();
    private static Long sequence = 0L;
    
    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }
    
    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }
    
    @Override
    public Optional<Post> findById(Long id) {
        Post post = store.get(id);
        return Optional.ofNullable(post);
    }
    
    public void clearStore() {
        store.clear();
    }
}
