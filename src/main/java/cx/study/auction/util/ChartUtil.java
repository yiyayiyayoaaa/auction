package cx.study.auction.util;

import cx.study.auction.pojo.Commodity.CommodityStatus;
import cx.study.auction.pojo.CommodityCount;
import cx.study.auction.pojo.CommodityStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTick;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 *
 * Created by cheng.xiao on 2017/4/18.
 */
public class ChartUtil {
    public static JFreeChart createPie(){
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("1",12);
        pieDataset.setValue("2",12);
        pieDataset.setValue("3",12);
        pieDataset.setValue("4",12);
        return ChartFactory.createPieChart("dada", pieDataset, true, true, false);
    }
    public static JFreeChart createTimeSeriesByYear(String title, int year, List<CommodityStatistics> statistics){
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        TimeSeries timeSeries = null;
        for (CommodityStatistics s: statistics){
            switch (s.getStatus()){
                case -1:
                    timeSeries = new TimeSeries("所有商品");
                    break;
                case CommodityStatus.SUCCESS:
                    timeSeries = new TimeSeries("成交商品");
                    break;
                case CommodityStatus.UNSOLD:
                    timeSeries = new TimeSeries("流拍商品");
                    break;
            }
            if (timeSeries != null){
                for (CommodityCount c : s.getData()){
                    timeSeries.add(new Month(c.getMonth(),year),c.getCount());
                }
                timeseriescollection.addSeries(timeSeries);
            }
        }
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "日期", "数量(个)",timeseriescollection, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM"));
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));     //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12)); //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        return jfreechart;
    }
}
