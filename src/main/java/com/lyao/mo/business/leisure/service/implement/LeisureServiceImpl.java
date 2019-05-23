package com.lyao.mo.business.leisure.service.implement;

import com.lyao.mo.bottom.dao.CommonBaseDao;
import com.lyao.mo.business.leisure.bean.HappyItem;
import com.lyao.mo.business.leisure.bean.HappyPagingQuery;
import com.lyao.mo.business.leisure.bean.po.T_happy_collect;
import com.lyao.mo.business.leisure.service.LeisureService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyao
 * @date 2019/5/17 9:32
 * @description
 */
@Service("leisureService")
public class LeisureServiceImpl implements LeisureService {

    private final Logger log = Logger.getLogger(LeisureServiceImpl.class);
    @Autowired
    CommonBaseDao commonBaseDao;

    @Override
    public List<HappyItem> queryHappyItems(HappyPagingQuery happyPagingQuery) {
        return commonBaseDao.selectList("leisure.getHappiness", happyPagingQuery);
    }

    @Override
    public boolean addHappyHits(Integer happyId) {
        int i = commonBaseDao.update("leisure.addCollectHits", happyId);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean substractHappyHits(Integer happyId) {
        int i = commonBaseDao.update("leisure.substractCollectHits", happyId);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<HappyItem> queryHappyItemsByCustomerId(HappyItem happyItem) {
        return commonBaseDao.selectList("leisure.getHappyCollectsByCustomerId", happyItem);
    }

    @Override
    public List<T_happy_collect> queryHappyCollectsByHappyId(T_happy_collect collect) {
        return commonBaseDao.selectList("leisure.getHappyCollectByCustomerIdAndHappyId", collect);
    }

    @Override
    public List<T_happy_collect> queryHappyCollectsByHappyId(int happyId, String customerId) {
        T_happy_collect happyCollect = new T_happy_collect();
        happyCollect.setCustomerId(customerId);
        happyCollect.setHappyId(happyId);
        happyCollect.setType(1);
        return queryHappyCollectsByHappyId(happyCollect);
    }

    @Override
    public boolean addHappyCollect(T_happy_collect collect) {
        int i = commonBaseDao.insert("leisure.insertHappyCollect", collect);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addHappyCollect(int happyId, String customerId) {
        T_happy_collect happyCollect = new T_happy_collect();
        happyCollect.setCustomerId(customerId);
        happyCollect.setHappyId(happyId);
        happyCollect.setType(1);
        happyCollect.setTransmitPlatform(0);
        happyCollect.setStatus(0);
        happyCollect.setRemark("");
        addHappyHits(happyId);
        return addHappyCollect(happyCollect);
    }

    @Override
    public boolean cancelHappyCollect(String happyId, String customerId) {
        substractHappyHits(Integer.valueOf(happyId));
        T_happy_collect happyCollect = new T_happy_collect();
        happyCollect.setCustomerId(customerId);
        happyCollect.setHappyId(Integer.valueOf(happyId));
        happyCollect.setType(1);
        int i = commonBaseDao.delete("leisure.deleteHappyByIdAndCustomerId", happyCollect);
        if (i>0){
            return true;
        }
        return false;
    }
}
