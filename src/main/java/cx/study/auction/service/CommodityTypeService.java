package cx.study.auction.service;

import cx.study.auction.pojo.CommodityType;

import java.util.List;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
public interface CommodityTypeService {
    List<CommodityType> findAll() throws Exception;

    int addType(CommodityType type);

    int update(CommodityType type);

    int delete(int id);
}
