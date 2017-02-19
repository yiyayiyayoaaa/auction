package cx.study.auction.mapper;

import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityImage;
import cx.study.auction.query.CommodityQuery;

import java.util.List;

public interface CommodityMapper {
    List<Commodity> findCommodity(CommodityQuery commodityQuery) throws Exception;

    int saveImage(CommodityImage commodityImage) throws Exception;

    int addCommodity(Commodity commodity) throws Exception;
}