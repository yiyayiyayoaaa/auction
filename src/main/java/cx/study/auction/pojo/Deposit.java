package cx.study.auction.pojo;

import java.util.Date;

/**
 *
 * Created by cheng.xiao on 2017/4/20.
 */
public class Deposit {
    private int id;
    private int userId;
    private int commodityId;
    private Date time;

    public Deposit() {
    }

    public Deposit(int userId, int commodityId, Date time) {
        this.userId = userId;
        this.commodityId = commodityId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
