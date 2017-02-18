package cx.study.auction.service.impl;

import cx.study.auction.mapper.CommodityMapper;
import cx.study.auction.pojo.Commodity;
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
}
