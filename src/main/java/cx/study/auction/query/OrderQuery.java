package cx.study.auction.query;

/**
 *
 * Created by cheng.xiao on 2017/4/18.
 */
public class OrderQuery extends BaseQuery{
    private String keyWord;
    private int status;
    private int userId;
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
