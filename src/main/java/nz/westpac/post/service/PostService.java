package nz.westpac.post.service;

import nz.westpac.post.model.Comment;
import nz.westpac.post.model.Post;

import java.util.List;

public interface PostService {

 public Post getPost(Integer postId);
 public List<Comment> getCommentsByPostId(Integer postId);
 public List<Post> getPosts();

}
