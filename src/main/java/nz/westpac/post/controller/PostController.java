package nz.westpac.post.controller;

import io.swagger.annotations.Api;
import nz.westpac.post.model.Comment;
import nz.westpac.post.model.Post;
import nz.westpac.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/v1", headers = "Accept=application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "Post operations")
public class PostController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private PostService postService;

  @GetMapping("/posts")
  ResponseEntity<List<Post>> getPosts() {
    ResponseEntity<List<Post>> response=postService.getPosts();
    if(response!=null)
      return ResponseEntity.status(response.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(response.getBody());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @GetMapping("/posts/{id}/comments")
  ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Integer id) {
    ResponseEntity<List<Comment>> response=postService.getCommentsByPostId(id);
    if(response!=null)
      return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @GetMapping("/posts/{id}")
  public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
    ResponseEntity<Post> response=postService.getPost(id);
    if(response!=null)
      return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
