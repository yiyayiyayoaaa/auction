package cx.study.auction.query;

/**
 * Created by AMOBBS on 2017/2/4.
 */
public class BaseQuery {
    private int pageNo;
    private int rows;
    private int startRow;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStartRow() {
        return (pageNo - 1) * rows;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
}
