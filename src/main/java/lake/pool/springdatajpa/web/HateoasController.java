package lake.pool.springdatajpa.web;

import lake.pool.springdatajpa.common.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HateoasController {

    @Autowired
    private WebRepository webRepository;

    @GetMapping("/hateoas")
    public PagedResources<Resource<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler){
        Page<Post> page = webRepository.findAll(pageable);
        return assembler.toResource(page);
    }
}
