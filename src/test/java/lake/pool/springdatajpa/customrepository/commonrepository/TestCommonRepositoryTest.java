//package lake.pool.springdatajpa.customrepository.commonrepository;
//
//import lake.pool.springdatajpa.common.entity.Post;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class TestCommonRepositoryTest {
//
//    @Autowired
//    TestCommonRepository testCommonRepository;
//
//    @Test
//    public void setTestCommonRepositoryTest(){
//        Post post = new Post();
//        post.setTitle("hibernate");
//
//        assertThat(testCommonRepository.contains(post)).isFalse();
//        testCommonRepository.save(post);
//
//        assertThat(testCommonRepository.contains(post)).isTrue();
//
//        testCommonRepository.findAll();
//
//        testCommonRepository.delete(post);
//        testCommonRepository.flush(); // 강제로 실제 db 반영
//    }
//}