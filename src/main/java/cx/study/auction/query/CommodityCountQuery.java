package cx.study.auction.query;

import java.util.Date;

/**
 *
 * Created by cheng.xiao on 2017/4/18.
 */
public class CommodityCountQuery {
    private int status;
    private Date start;
    private Date end;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
