package cx.study.auction.action;

import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityStatistics;
import cx.study.auction.service.CommodityService;
import cx.study.auction.util.ChartUtil;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 *
 * Created by cheng.xiao on 2017/4/18.
 */
@Controller
@RequestMapping("/chart")
public class ChartAction {
    @Resource
    CommodityService commodityService;
    @RequestMapping("/pie")
    public void get(HttpServletResponse response) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.getWeekYear();
        String title = year + "年商品拍卖情况走势图";
        List<CommodityStatistics> statistics = commodityService.getCommodityStatistics();
        JFreeChart chart = ChartUtil.createTimeSeriesByYear(title, year,statistics);
        response.setContentType("image/png");
        ChartUtilities.writeChartAsPNG(response.getOutputStream(),chart,600,400,null);
    }
}
