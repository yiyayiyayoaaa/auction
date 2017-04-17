package cx.study.auction.query;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
public class CommodityQuery extends BaseQuery{
    private Integer status;
    private Integer typeId;
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
