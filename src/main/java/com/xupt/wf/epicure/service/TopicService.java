package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.entity.Topic;
import com.xupt.wf.epicure.mapper.CookBookMapper;
import com.xupt.wf.epicure.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-17
 * Time: 10:55
 */
@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private CookBookMapper cookBookMapper;

    public List<Topic> getList(){
        return topicMapper.getList();
    }

    public List<CookBook> getListByType(Integer typeId){
        return cookBookMapper.queryListByType(typeId);
    }

    public List<CookBook> getListByScore(){
        return  cookBookMapper.queryListByScore();
    }

    public List<CookBook> getListByCount(){
        return cookBookMapper.queryListByCount();
    }

    public List<CookBook> getListByReadNum(){
        return cookBookMapper.queryListByReadNum();
    }

    public List<CookBook> getListByScoreLimit(){
        return  cookBookMapper.queryListByScoreLimit();
    }

    public List<CookBook> getListByCountLimit(){
        return cookBookMapper.queryListByCountLimit();
    }

    public List<CookBook> getListByReadNumLimit(){
        return cookBookMapper.queryListByReadNumLimit();
    }
}
