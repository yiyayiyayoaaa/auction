package cx.study.auction.service.impl;

import cx.study.auction.mapper.CommodityMapper;
import cx.study.auction.mapper.CommodityTypeMapper;
import cx.study.auction.pojo.Commodity;
import cx.study.auction.pojo.CommodityType;
import cx.study.auction.query.CommodityQuery;
import cx.study.auction.service.CommodityService;
import cx.study.auction.service.CommodityTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
@Service
public class CommodityTypeServiceImpl implements CommodityTypeService{

    @Resource
    private CommodityTypeMapper commodityTypeMapper;

    public List<CommodityType> findAll() throws Exception {
        return commodityTypeMapper.findAll();
    }

    @Override
    public int addType(CommodityType type) {
        return commodityTypeMapper.insertSelective(type);
    }

    @Override
    public int update(CommodityType type) {
        return commodityTypeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public int delete(int id) {
        return commodityTypeMapper.deleteByPrimaryKey(id);
    }
}
