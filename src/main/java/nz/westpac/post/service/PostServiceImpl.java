package nz.westpac.post.service;

import nz.westpac.post.model.Comment;
import nz.westpac.post.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
  private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

  @Value("${post.create.endpoint}")
  private String postCreateEndpoint;

  @Value("${post.get.endpoint}")
  private String postGetEndpoint;

  @Value("${posts.get.endpoint}")
  private String postsGetEndpoint;

  @Value("${post.get.comments.endpoint}")
  private String getComentByPostIdEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public Post getPost(Integer id){
        return restTemplate.getForObject(postGetEndpoint,Post.class,id);
   }

  @Override
  public List<Comment> getCommentsByPostId(Integer postId){
    Map<String, Integer> uriParams = new HashMap<String, Integer>();
    uriParams.put("id",postId);
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getComentByPostIdEndpoint);
    logger.debug("Request URI {}", builder.buildAndExpand(uriParams).toUri());
    URI uri= builder.buildAndExpand(uriParams).toUri();
    ResponseEntity<List<Comment>> response=restTemplate.exchange(uri, HttpMethod.GET,null, new ParameterizedTypeReference<List<Comment>>() {});
    return response.getBody();
  }

  @Override
  public List<Post> getPosts(){
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(postsGetEndpoint);
    ResponseEntity<List<Post>> response=restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Post>>() {});
    List<Post> posts=response.getBody();
    return posts;
  }




}
