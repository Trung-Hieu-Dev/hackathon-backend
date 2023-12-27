package hello.hellospring.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "blog")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private String content;
    
    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "UPDATEDAT")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    public Post() {
    
    }
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Post post)) {return false;}
        return Objects.equals(getTitle(), post.getTitle());
    }
    
    @Override public int hashCode() {
        return Objects.hash(getTitle());
    }
}
