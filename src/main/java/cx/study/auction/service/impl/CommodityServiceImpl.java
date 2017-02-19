package cx.study.auction.service.impl;

import cx.study.auction.mapper.CommodityMapper;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityImage;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.service.CommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
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


}
