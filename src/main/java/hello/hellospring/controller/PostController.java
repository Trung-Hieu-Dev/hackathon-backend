package hello.hellospring.controller;

import hello.hellospring.domain.Post;
import hello.hellospring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class PostController {
    
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    @PostMapping("")
    public Post createPost(@RequestBody Post post){
        Long postId = postService.join(post);
        if (postId != null) {
            return post;
        }
        return null;
    }
    
    @GetMapping("")
    public List<Post> getAllPosts() {
        return postService.findPosts();
    }
    
    @GetMapping("/{postId}")
    public Post getPost(@PathVariable(name = "postId") Long postId) {
        return postService.findOne(postId).get();
    }
}
