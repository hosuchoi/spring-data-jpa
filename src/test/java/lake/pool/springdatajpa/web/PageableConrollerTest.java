package lake.pool.springdatajpa.web;

import lake.pool.springdatajpa.common.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PageableConrollerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebRepository webRepository;

    @Test
    public void pageableTest() throws Exception{
        createPosts();

        mockMvc.perform(get("/pageable/")
                    .param("page", "3")
                    .param("size", "10")
                    .param("sort", "created,desc")
                    .param("sort", "title"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                            jsonPath("$.content[0].title", is("lake"));
                            });
    }

    public void createPosts() {
        int page = 100;

        while(page > 0){
            Post post = new Post();
            post.setTitle("lake");
            webRepository.save(post);
            page--;
        }
    }

}