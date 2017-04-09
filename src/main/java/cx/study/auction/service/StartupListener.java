package cx.study.auction.service;

import cx.study.auction.mapper.CommodityMapper;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.Commodity.CommodityStatus;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.vo.CommodityVo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by chengxiao on 2017/4/9.
 */
@Component
public class StartupListener implements ApplicationContextAware {

    @Resource
    private
    CommodityService commodityService;
    @Resource
    private
    CommodityMapper commodityMapper;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            initCommodityStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCommodityStatus() throws Exception {
        List<CommodityVo> list = new ArrayList<>();
        CommodityQuery commodityQuery = new CommodityQuery();
        commodityQuery.setStatus(CommodityStatus.WAIT_AUCTION);
        List<CommodityVo> list1 = commodityService.findCommodity(commodityQuery);
        list.addAll(list1);
        commodityQuery.setStatus(CommodityStatus.AUCTION);
        List<CommodityVo> list2 = commodityService.findCommodity(commodityQuery);
        list.addAll(list2);
        for (CommodityVo commodityVo : list){
            doAuction(commodityVo);
        }
    }
    private void doAuction(final CommodityVo commodityVo) throws Exception{
        Date startTime = commodityVo.getStartTime();
        Date endTime = commodityVo.getEndTime();

        if (System.currentTimeMillis() < startTime.getTime()){
            //还未开始
            commodityVo.setStatus(CommodityStatus.WAIT_AUCTION);
            commodityVo.setUpdateTime(new Date());
            commodityMapper.updateCommodity(commodityVo);
            start(startTime.getTime() - System.currentTimeMillis(),commodityVo);
            end(endTime.getTime() - System.currentTimeMillis(),commodityVo);
        } else if (startTime.getTime() <= System.currentTimeMillis() && System.currentTimeMillis() < endTime.getTime()){
            //正在进行
            commodityVo.setStatus(CommodityStatus.AUCTION);
            commodityVo.setUpdateTime(new Date());
            commodityMapper.updateCommodity(commodityVo);
            end(endTime.getTime() - System.currentTimeMillis(),commodityVo);
        } else {
            //已经结束
            doEnd(commodityVo);
        }
    }

    private void doEnd(CommodityVo commodityVo) {
        try {
            Commodity commodity = commodityMapper.findCommodityById(commodityVo.getId());
            Double hammerPrice = commodity.getHammerPrice();
            if (hammerPrice != null && hammerPrice > 0d){
                commodity.setStatus(CommodityStatus.SUCCESS);
            } else {
                commodity.setStatus(CommodityStatus.UNSOLD);
            }
            commodity.setUpdateTime(new Date());
            commodityMapper.updateCommodity(commodity);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startScheduledExecutorService(long delay,Runnable runnable){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(runnable,delay, TimeUnit.MILLISECONDS);
        System.out.println("开始定时任务：" + new Date(System.currentTimeMillis() + delay));
    }

    private void start(long delay, final CommodityVo commodityVo){
        startScheduledExecutorService(delay, new Runnable() {
            @Override
            public void run() {
                try {
                    commodityVo.setStatus(CommodityStatus.AUCTION);
                    commodityMapper.updateCommodity(commodityVo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void end(long delay, final CommodityVo commodityVo){
        startScheduledExecutorService(delay, new Runnable() {
            @Override
            public void run() {
                doEnd(commodityVo);
            }
        });
    }
}
