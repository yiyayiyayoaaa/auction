package cx.study.auction.mapper;

import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityType;

import java.util.List;

public interface CommodityTypeMapper {
    List<CommodityType> findAll() throws Exception;

    int deleteByPrimaryKey(int id);

    int insertSelective(CommodityType type);

    int updateByPrimaryKeySelective(CommodityType type);
}