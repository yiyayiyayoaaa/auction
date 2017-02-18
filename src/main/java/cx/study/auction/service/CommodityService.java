package cx.study.auction.service;

import cx.study.auction.pojo.Commodity;
import cx.study.auction.query.CommodityQuery;

import java.util.List;

/**
 * Created by AMOBBS on 2017/2/18.
 */
public interface CommodityService {

    List<Commodity> findCommodity(CommodityQuery commodityQuery) throws Exception;
}
