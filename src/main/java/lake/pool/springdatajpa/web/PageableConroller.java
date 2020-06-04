package lake.pool.springdatajpa.web;

import lake.pool.springdatajpa.common.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageableConroller {

    @Autowired
    private WebRepository webRepository;

    @GetMapping("/pageable")
    public Page<Post> getPageableData(Pageable pageable){
        return webRepository.findAll(pageable);
    }
}
