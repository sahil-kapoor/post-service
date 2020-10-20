package nz.westpac.post.service;

import nz.westpac.post.model.Comment;
import nz.westpac.post.model.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

 public ResponseEntity<Post>  getPost(Integer postId);
 public ResponseEntity<List<Comment>> getCommentsByPostId(Integer postId);
 public ResponseEntity<List<Post>> getPosts();

}
