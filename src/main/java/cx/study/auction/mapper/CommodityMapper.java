package cx.study.auction.mapper;

import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.Customer;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.query.CustomerQuery;

import java.util.List;

public interface CommodityMapper {
    List<Commodity> findCommodity(CommodityQuery commodityQuery) throws Exception;
}