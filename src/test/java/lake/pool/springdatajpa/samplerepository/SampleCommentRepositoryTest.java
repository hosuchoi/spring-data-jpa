package lake.pool.springdatajpa.samplerepository;

import lake.pool.springdatajpa.common.entity.Comment;
import lake.pool.springdatajpa.common.entity.CommentOnly;
import lake.pool.springdatajpa.common.entity.CommentSummary;
import lake.pool.springdatajpa.common.entity.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SampleCommentRepositoryTest {

    @Autowired
    SampleCommentRepository sampleCommentRepository;

    @Autowired
    SamplePostRepository samplePostRepository;

    @Test
    public void entityGraghTest(){
        /*
        EntityGragh는 repository의 특정 메서드에 대해서 연관관계에 있는 객체를 함께 조회 하게 해준다.
        Fetch 타입 설정
        - EAGER : 함께 조회
        - LAZY : 조회 안함
         */
        sampleCommentRepository.getCommentById(1l); //EntityGragh 설정 : entity에 전역을 설정 후 repository에서 각각 설정

        System.out.println("========================");

        sampleCommentRepository.getById(1l); //EntityGragh 설정 :  @EntityGraph(attributePaths = {"post"}) 한방에 설정 (entity에 다른 설정 필요 없음) << 추천

        System.out.println("========================");

        sampleCommentRepository.findById(1l);
    }

    @Test
    public void porjectionTest(){
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = samplePostRepository.save(post);

        Comment comment = new Comment();
        comment.setComment("jpa");
        comment.setUp(10);
        comment.setDown(1);
        comment.setPost(savedPost);
        Comment savedComment = sampleCommentRepository.save(comment);

//        sampleCommentRepository.findByPost_Id(savedPost.getId()).forEach(c ->{
//            System.out.println("==============");
//            System.out.println(c.getVotes());
//            System.out.println("==============");
//            System.out.println(c.getAll());
//        });

//        sampleCommentRepository.findByComment("jpa").forEach(c ->{
//            System.out.println("==============");
//            System.out.println(c.getVotes());
//            System.out.println("==============");
//            System.out.println(c.getAll());
//        });

        sampleCommentRepository.findByPost_Id(savedPost.getId(), CommentSummary.class).forEach(c ->{
            System.out.println("==============");
            System.out.println(c.getVotes());
            System.out.println("==============");
            System.out.println(c.getAll());
        });

        sampleCommentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class).forEach(c ->{
            System.out.println("==============");
            System.out.println(c.getComment());
        });
    }
}