package cx.study.auction.mapper;

import cx.study.auction.pojo.CommodityType;

import java.util.List;

public interface CommodityTypeMapper {
    List<CommodityType> findAll() throws Exception;
}