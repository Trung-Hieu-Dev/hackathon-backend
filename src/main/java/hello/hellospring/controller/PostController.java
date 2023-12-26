package hello.hellospring.controller;

import hello.hellospring.domain.Post;
import hello.hellospring.repository.MemoryPostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class PostController {
    private MemoryPostRepository memoryPostRepository = new MemoryPostRepository();
    
    @PostMapping("")
    public Post createPost(@RequestBody Post post){
//        post.setTitle("Post 1");
//        post.setContent("Content 1");
        return memoryPostRepository.save(post);
    }
    
    @GetMapping("")
    public List<Post> getAllPosts() {
        return memoryPostRepository.findAll();
    }
    
    @GetMapping("/{postId}")
    public Post getPost(@PathVariable(name = "postId") Long postId) {
        return memoryPostRepository.findById(postId).get();
    }
}
