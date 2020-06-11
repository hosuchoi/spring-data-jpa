package lake.pool.springdatajpa.Specifications;

import lake.pool.springdatajpa.common.entity.Comment;
import lake.pool.springdatajpa.common.entity.Comment_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/*
 Specification을 위한 spec 정의
 */
public class CommentSpecs {

    public static Specification<Comment> isBest(){
        return new Specification<Comment>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //root = Comment
                return criteriaBuilder.isTrue(root.get(Comment_.best)); // 가장 best는 뭐냐
            }
        };
    }

    public static  Specification<Comment> isGood(){
        return new Specification<Comment>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(Comment_.up),10); // up 갯수가 10개 이상이냐
            }
        };
    }
}
