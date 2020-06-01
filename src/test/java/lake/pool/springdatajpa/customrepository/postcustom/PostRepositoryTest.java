package lake.pool.springdatajpa.customrepository.postcustom;

import lake.pool.springdatajpa.common.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void crud(){
        postRepository.findMyPost();
        Post post = new Post();
        post.setTitle("hibernate");

        postRepository.save(post);

        postRepository.findAll();

        postRepository.delete(post);
        postRepository.flush(); // 강제로 실제 db 반영
    }
}