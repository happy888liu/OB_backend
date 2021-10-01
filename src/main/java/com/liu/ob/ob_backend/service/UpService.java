package com.liu.ob.ob_backend.service;

import com.liu.ob.ob_backend.core.enumeration.UpSortEnum;
import com.liu.ob.ob_backend.model.Up;
import com.liu.ob.ob_backend.model.UpData;
import com.liu.ob.ob_backend.repository.UpDataRepository;
import com.liu.ob.ob_backend.repository.UpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class UpService {
    @Resource
    private UpRepository upRepository;
    @Resource
    private UpDataRepository upDataRepository;
    @Resource
    private MongoTemplate template;

    /**
     * 添加用户到观测队列
     *
     * @param mid up主唯一标识
     */
    public void addUp2Watch(Long mid) {
        Up up = new Up(mid);
        upRepository.save(up);
    }

    /**
     * 将用户从观测队列软删除
     *
     * @param mid 用户id
     */
    public void removeUp2Watch(Long mid) {
        template.updateFirst(Query.query(Criteria.where("mid").is(mid)),
                Update.update("focus", false),
                "up");
    }

    /**
     * @param sort
     * @param page
     * @param size
     */
    public Page<Up> getTopUps(Integer sort, Integer page, Integer size) {
        String sortKey = UpSortEnum.getKeyByFlag(sort);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortKey));
        return upRepository.findAllWithoutCData(pageRequest);
    }

    /**
     * 根据mid获取up主的具体信息
     *
     * @param mid up主唯一标识
     */
    public Up getUpDetail(Long mid) {
        return upRepository.findUpByMid(mid);
    }

    /**
     * 根据mid获取up主的历史信息，获取 up_data的数据
     *
     * @param mid
     * @return
     */
    public List<UpData> getHistory(Long mid, int days) {
        Query query = new Query();
        Criteria criteria = getAggregateMatch(mid, days);
        query.addCriteria(criteria).with(Sort.by(Sort.Direction.DESC, "datetime"));
        List<UpData> upDatas = template.find(query, UpData.class);
        return upDatas;
    }

    /**
     * 用于检索满足事件条件的记录
     *
     * @param mid  id
     * @param days 往后多少天
     * @return
     */
    private Criteria getAggregateMatch(Long mid, int days) {
        Criteria criteria = Criteria.where("mid").is(mid);
        if (days == -1) {
            return criteria;
        } else {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -days);
            return criteria.and("datetime").gt(c.getTime());
        }
    }
}
