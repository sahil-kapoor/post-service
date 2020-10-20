package nz.westpac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"nz.westpac.*"})
public class PostApplication {

  public static void main(String[] args) {
    SpringApplication.run(PostApplication.class, args);
  }

}
