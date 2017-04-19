package cx.study.auction.pojo;

import org.jfree.data.time.RegularTimePeriod;

import java.util.Calendar;

/**
 *
 * Created by cheng.xiao on 2017/4/18.
 */
public class CommodityCount implements Comparable<CommodityCount>{
    private int month;
    private long count;

    public CommodityCount() {
    }

    public CommodityCount(int month, long count) {
        this.month = month;
        this.count = count;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public int compareTo(CommodityCount o) {
        return o.getMonth() - this.month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommodityCount)) return false;

        CommodityCount that = (CommodityCount) o;

        return getMonth() == that.getMonth();
    }

    @Override
    public int hashCode() {
        return getMonth();
    }
}
