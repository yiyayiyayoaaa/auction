package cx.study.auction.pojo;

import cx.study.auction.pojo.Commodity.CommodityStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Created by cheng.xiao on 2017/4/18.
 */
public class CommodityStatistics {
    private int status;
    private Set<CommodityCount> data;


    public CommodityStatistics(int status, Set<CommodityCount> data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<CommodityCount> getData() {
        return data;
    }

    public void setData(Set<CommodityCount> data) {
        this.data = data;
    }

}
