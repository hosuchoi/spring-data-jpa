package lake.pool.springdatajpa.samplerepository;

import lake.pool.springdatajpa.common.entity.Comment;
import lake.pool.springdatajpa.common.entity.CommentSummary;
import lake.pool.springdatajpa.common.entity.CommentSummaryC;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SampleCommentRepository extends JpaRepository<Comment, Long> {

    /*
    EntityGragh
     */
    @EntityGraph(value = "Comment.post")
    Optional<Comment> getCommentById(Long id);

    @EntityGraph(attributePaths = {"post"})
    Optional<Comment> getById(Long id);

    /*
    Projection
     */
//    List<CommentSummary> findByPost_Id(Long id); // 인터페이스 방식
//    List<CommentSummaryC> findByPost_Id(Long id); // 클래스 방식
    // 위 2개 방식 다 커버 가능
    // CommentSummary, CommentSummaryC, CommentSummaryOnly 등 클래스 타입 따라 여러개의 리턴 처리 가능
    <T> List<T>
    ndByPost_Id(Long id, Class<T> classType);

    List<CommentSummary> findByComment(String comment);
}
