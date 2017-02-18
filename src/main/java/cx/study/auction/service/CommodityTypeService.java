package cx.study.auction.service;

import cx.study.auction.pojo.CommodityType;

import java.util.List;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
public interface CommodityTypeService {
    List<CommodityType> findAll() throws Exception;
}
