package lake.pool.springdatajpa.web;

import lake.pool.springdatajpa.common.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HateoasControllerTest {

    @Autowired
    private WebRepository webRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hateoasTest() throws Exception{
        postCreate();

        mockMvc.perform(get("/hateoas")
                    .param("page", "5")
                    .param("size", "10")
                    .param("sort", "created,desc")
                    .param("sort", "id"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    jsonPath("$.content[0].title", is("lake"));
                });
    }

    public void postCreate() {
        int postSize = 100;
        while(postSize > 0){
            Post post = new Post();
            post.setTitle("lake");
            webRepository.save(post);
            postSize--;
        }
    }
}