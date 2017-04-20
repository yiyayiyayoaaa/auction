package cx.study.auction.query;

/**
 *
 * Created by cheng.xiao on 2017/4/20.
 */
public class DepositQuery {

    private int userId;
    private int commodityId;

    public DepositQuery() {
    }

    public DepositQuery(int userId, int commodityId) {
        this.userId = userId;
        this.commodityId = commodityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }
}
