package cx.study.auction.vo;

import cx.study.auction.pojo.Order;

/**
 *
 * Created by cheng.xiao on 2017/4/18.
 */
public class OrderVo extends Order{
    private String commodityName;
    private String username;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
