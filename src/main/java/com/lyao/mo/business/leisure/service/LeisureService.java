package com.lyao.mo.business.leisure.service;

import com.lyao.mo.business.leisure.bean.HappyItem;
import com.lyao.mo.business.leisure.bean.HappyPagingQuery;
import com.lyao.mo.business.leisure.bean.po.T_happy_collect;

import java.util.List;

/**
 * @author lyao
 * @date 2019/5/17 9:32
 * @description
 */
public interface LeisureService {

    /**
     * 查询开心果
     * @param happyPagingQuery
     * @return
     */
    List<HappyItem> queryHappyItems(HappyPagingQuery happyPagingQuery);

    /**
     * 增加开心果的点击数
     * @param happyId 开心果编号
     * @return
     */
    boolean addHappyHits(Integer happyId);

    /**
     * 减少开心果的点击数
     * @param happyId 开心果编号
     * @return
     */
    boolean substractHappyHits(Integer happyId);

    /**
     * 查询某用户收藏的开心果
     * @param happyItem
     * @return
     */
    List<HappyItem> queryHappyItemsByCustomerId(HappyItem happyItem);

    /**
     * 查询某用户是否收藏了某开心果
     * @param collect
     * @return
     */
    List<T_happy_collect> queryHappyCollectsByHappyId(T_happy_collect collect);

    /**
     * 查询某用户是否收藏了某开心果
     * @param happyId
     * @param customerId
     * @return
     */
    List<T_happy_collect> queryHappyCollectsByHappyId(int happyId, String customerId);

    /**
     * 新增加一条收藏记录
     * @param collect
     * @return
     */
    boolean addHappyCollect(T_happy_collect collect);

    /**
     * 新增加一条收藏记录
     * @param happyId
     * @param customerId
     * @return
     */
    boolean addHappyCollect(int happyId, String customerId);

    /**
     * 取消收藏
     * @param happyId
     * @param customerId
     * @return
     */
    boolean cancelHappyCollect(String happyId, String customerId);
}
