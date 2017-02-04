package cx.study.auction.query;

/**
 * Created by AMOBBS on 2017/2/4.
 */
public class CustomerQuery extends BaseQuery{
    private String name;
    private String idcard;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
