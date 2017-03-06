package cx.study.auction.service.impl;

import cx.study.auction.mapper.CommodityMapper;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.Commodity.CommodityStatus;
import cx.study.auction.pojo.CommodityImage;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.service.CommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */

@Service
public class CommodityServiceImpl implements CommodityService{

    @Resource
    private CommodityMapper commodityMapper;
    public List<Commodity> findCommodity(CommodityQuery commodityQuery) throws Exception {
        return commodityMapper.findCommodity(commodityQuery);
    }

    @Override
    public int addCommodity(Commodity commodity,List<CommodityImage> imageList) throws Exception {
        int i = commodityMapper.addCommodity(commodity);
        if(i > 0){
            for (CommodityImage commodityImage:imageList) {
                commodityImage.setCommodityId(commodity.getId());
                commodityMapper.saveImage(commodityImage);
            }
        }
        return i;
    }

    @Override
    public int saveImages(List<CommodityImage> imageList, Commodity commodity) throws Exception {
        int i = 0;
        for(CommodityImage commodityImage : imageList){
            commodityImage.setCommodityId(commodity.getId());
            i = commodityMapper.saveImage(commodityImage);
        }
        return i;
    }

    @Override
    public int commodityStatusChange(Integer id,int status) throws Exception {
        Commodity commodity = new Commodity();
        commodity.setId(id);
        commodity.setStatus(status);
        commodity.setUpdateTime(new Date());
        return commodityMapper.updateCommodity(commodity);
    }

    @Override
    public int auction(final Commodity commodity) throws Exception {
        commodity.setStatus(CommodityStatus.WAIT_AUCTION);
        commodity.setUpdateTime(new Date());
        int i = commodityMapper.updateCommodity(commodity);
        if (i == 1){
            Date start = commodity.getStartTime();
            Date end = commodity.getEndTime();
            long delay1 = start.getTime() - System.currentTimeMillis();
            long delay2 = end.getTime() - System.currentTimeMillis() + 60 * 1000;
            startScheduledExecutorService(delay1, new Runnable() {
                @Override
                public void run() {
                    commodity.setStatus(CommodityStatus.AUCTION);
                    commodity.setUpdateTime(new Date());
                    try {
                        commodityMapper.updateCommodity(commodity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            startScheduledExecutorService(delay2, new Runnable() {
                @Override
                public void run() {
                    try {
                        Commodity commodity1 = commodityMapper.findCommodityById(commodity.getId());
                        Double hammerPrice = commodity1.getHammerPrice();
                        if (hammerPrice != null && hammerPrice > 0d){
                            commodity1.setStatus(CommodityStatus.SUCCESS);
                        } else {
                            commodity1.setStatus(CommodityStatus.UNSOLD);
                        }
                        commodity1.setUpdateTime(new Date());
                        commodityMapper.updateCommodity(commodity1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return i;
    }

    private void startScheduledExecutorService(long delay,Runnable runnable){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(runnable,delay, TimeUnit.MILLISECONDS);
        System.out.println("开始定时任务：" + new Date(System.currentTimeMillis() + delay));
    }

}
