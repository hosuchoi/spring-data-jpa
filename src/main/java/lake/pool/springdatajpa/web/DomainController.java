package lake.pool.springdatajpa.web;

import lake.pool.springdatajpa.common.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DomainController {

    @Autowired
    private WebRepository webRepository;

//    @GetMapping("/posts/{id}")
//    public String getPost(@PathVariable Long id){
//        Optional<Post> byId = webRepository.findById(id);
//        Post post = byId.get();
//
//        return post.getTitle();
//    }

    @GetMapping("/posts/{id}")  // DomainClassConverter 사용됨 ( ToIdConverter(entity->id) ToEntityConvert(id->entity))
    public String getPost(@PathVariable("id") Post post){ // ToEntityConvert를 통해서 findById 호출되서 entity 리턴됨
        return post.getTitle();
    }
}
