package lake.pool.springdatajpa.common.entity;

public class CommentSummaryC {

    private String comment;

    private int up;

    private int down;

    public CommentSummaryC(String comment, int up, int down) {
        this.comment = comment;
        this.up = up;
        this.down = down;
    }

    public String getComment() {
        return comment;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public String getVotes(){ //java 8부터 interface에 default 메서드 사용 할 수 있음. >> 이렇게 하면 필요한 칼럼 + 커스텀한 칼럼을 뽑는게 가능함.
        return getUp() + " " + getDown();
    }
    public String getAll(){
        return getComment() + " " + getUp() + " " +getDown();
    }
}
