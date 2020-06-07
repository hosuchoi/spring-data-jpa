package lake.pool.springdatajpa.common.entity;


import org.springframework.beans.factory.annotation.Value;

/*
    projection Interface 방식 ( 추천 class 방식보다 코딩수가 적어짐 )
 */

public interface CommentSummary {

    String getComment();

    int getUp();

    int getDown();

    // 이 방식은 모든 column을 다 조회한다..ㄷㄷㄷ
//    @Value("#{target.up + ' ' + target.down}")
//    String getVotes();

    default String getVotes(){ //java 8부터 interface에 default 메서드 사용 할 수 있음. >> 이렇게 하면 필요한 칼럼 + 커스텀한 칼럼을 뽑는게 가능함.
        return getUp() + " " + getDown();
    }
    default String getAll(){
        return getComment() + " " + getUp() + " " +getDown();
    }
}
