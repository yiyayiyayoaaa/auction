package cx.study.auction.mapper;

import cx.study.auction.pojo.BidRecord;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityImage;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.vo.CommodityVo;

import java.util.List;

public interface CommodityMapper {
    Commodity findCommodityById(Integer id) throws Exception;

    CommodityVo findCommodityVoById(Integer id) throws Exception;

    List<CommodityVo> findCommodity(CommodityQuery commodityQuery) throws Exception;

    int saveImage(CommodityImage commodityImage) throws Exception;

    int addCommodity(Commodity commodity) throws Exception;

    int updateCommodity(Commodity commodity) throws Exception;

    List<String> findImageByCId(Integer id) throws Exception;

    int addBidRecord(BidRecord bidRecord) throws Exception;
}