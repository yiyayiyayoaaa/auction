package cx.study.auction.mapper;

import cx.study.auction.pojo.CommodityType;

public interface CommodityTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommodityType record);

    int insertSelective(CommodityType record);

    CommodityType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityType record);

    int updateByPrimaryKey(CommodityType record);
}