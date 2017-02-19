package cx.study.auction.pojo;

/**
 *
 * Created by AMOBBS on 2017/2/19.
 */
public class CommodityImage {
    private int id;
    private int commodityId;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
