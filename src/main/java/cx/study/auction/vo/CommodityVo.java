package cx.study.auction.vo;

import cx.study.auction.pojo.Commodity;

import java.util.List;

/**
 *
 * Created by AMOBBS on 2017/2/8.
 */
public class CommodityVo extends Commodity{
    private String typeName;
    private String customerName;
    private List<String> imageUrls;
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
