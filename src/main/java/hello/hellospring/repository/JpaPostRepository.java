package hello.hellospring.repository;

import hello.hellospring.domain.Post;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaPostRepository implements PostRepository{
    
    private final EntityManager entityManager;
    
    public JpaPostRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    @Override
    public Post save(Post post) {
        entityManager.persist(post);
        return post;
    }
    
    @Override
    public Optional<Post> findById(Long id) {
        Post post = entityManager.find(Post.class, id);
        return Optional.ofNullable(post);
    }
    
    @Override
    public Optional<Post> findByName(String title) {
        // SQL => JPQL
        List<Post> result = entityManager
                .createQuery("select p from Post p where p.title = :title", Post.class)
                .setParameter("title", title)
                .getResultList();
        
        return result.stream().findAny();
    }
    
    @Override
    public List<Post> findAll() {
        return entityManager
                .createQuery("select p from Post p", Post.class)
                .getResultList();
    }
}
