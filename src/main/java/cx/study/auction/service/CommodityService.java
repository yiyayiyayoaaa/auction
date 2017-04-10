package cx.study.auction.service;

import cx.study.auction.pojo.BidRecord;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityImage;
import cx.study.auction.pojo.Result;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.vo.CommodityVo;

import java.util.List;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
public interface CommodityService {

    List<CommodityVo> findCommodity(CommodityQuery commodityQuery) throws Exception;

    List<CommodityVo> findCommodityWithImg(CommodityQuery commodityQuery) throws Exception;

    CommodityVo findCommodityById(Integer id) throws Exception;
    int addCommodity(Commodity commodity,List<CommodityImage> imageList) throws Exception;

    int saveImages(List<CommodityImage> imageList, Commodity commodity) throws Exception;

    int commodityStatusChange(Integer id,int status) throws Exception;

    int auction(Commodity commodity) throws Exception;

    Result saveBidRecord(BidRecord bidRecord) throws Exception;
}
