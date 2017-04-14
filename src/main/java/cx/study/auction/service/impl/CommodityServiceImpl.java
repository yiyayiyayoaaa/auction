package cx.study.auction.service.impl;

import cx.study.auction.mapper.CommodityMapper;
import cx.study.auction.pojo.BidRecord;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.Commodity.CommodityStatus;
import cx.study.auction.pojo.CommodityImage;
import cx.study.auction.pojo.Result;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.service.CommodityService;
import cx.study.auction.vo.CommodityVo;
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
    public List<CommodityVo> findCommodity(CommodityQuery commodityQuery) throws Exception {
        return commodityMapper.findCommodity(commodityQuery);
    }

    @Override
    public List<CommodityVo> findCommodityWithImg(CommodityQuery commodityQuery) throws Exception {
        List<CommodityVo> commodities = this.findCommodity(commodityQuery);
        for (CommodityVo commodityVo : commodities){
            List<String> images = commodityMapper.findImageByCId(commodityVo.getId());
            commodityVo.setImageUrls(images);
        }
        return commodities;
    }

    @Override
    public CommodityVo findCommodityById(Integer id) throws Exception {
        CommodityVo commodityVo = commodityMapper.findCommodityVoById(id);
        List<String> images = commodityMapper.findImageByCId(commodityVo.getId());
        commodityVo.setImageUrls(images);
        return commodityVo;
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
            long delay2 = end.getTime() - System.currentTimeMillis() + 5 * 1000;
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

    @Override
    public Result saveBidRecord(BidRecord bidRecord) throws Exception {
        //根据id;查询商品状态是否正在拍卖。
        String msg;
        int code;
        Commodity commodity = commodityMapper.findCommodityById(bidRecord.getCommodityId());
        if (commodity.getStatus() == CommodityStatus.AUCTION){
            if(Double.isNaN(commodity.getHammerPrice())){
                commodity.setHammerPrice(0d);
            }
            if (bidRecord.getPrice() > commodity.getHammerPrice()) {
                commodity.setHammerPrice(bidRecord.getPrice());
                commodityMapper.updateCommodity(commodity);
                int i = commodityMapper.addBidRecord(bidRecord);
                if (i> 0){
                    code = 0;
                    msg = "出价成功";
                } else {
                    code = -1;
                    msg = "出价失败";
                }
            } else {
                code = -1;
                msg = "出价低于当前价格";
            }

        } else {
            msg = "拍卖已结束";
            code = -1;
        }
        return new Result<>(code,msg,bidRecord);
    }

    @Override
    public Result findBidRecordById(int commodityId) throws Exception {
        List<BidRecord> records = commodityMapper.findBidRecords(commodityId);
        return new Result<>(0,"",records);
    }

    private void startScheduledExecutorService(long delay,Runnable runnable){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(runnable,delay, TimeUnit.MILLISECONDS);
        System.out.println("开始定时任务：" + new Date(System.currentTimeMillis() + delay));
    }

}
