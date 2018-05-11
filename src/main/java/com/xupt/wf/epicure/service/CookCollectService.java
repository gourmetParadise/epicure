package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.entity.CookCollect;
import com.xupt.wf.epicure.mapper.CookBookMapper;
import com.xupt.wf.epicure.mapper.CookCollectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 20:31
 */
@Service
public class CookCollectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookCollectService.class);

    @Autowired
    private CookCollectMapper cookCollectMapper;

    @Autowired
    private CookBookMapper cookBookMapper;

    @Transactional
    public boolean addCollect(CookCollect cookCollect){
        boolean result = false;
        String cookImage = cookBookMapper.getImagePath(cookCollect.getCookbookId());
        cookCollect.setCookImage(cookImage);
        int count = cookCollectMapper.addCollect(cookCollect);
        if(count == 1){
            result = true;
        }
        return result;
    }

    public boolean deleteCollect(String userName, Integer cookbookId){
        boolean result = false;
        int count = cookCollectMapper.deleteCollect(userName, cookbookId);
        if(count == 1){
            result = true;
        }
        return result;
    }

    public List<CookCollect> queryByName(String userName){
        return cookCollectMapper.queryListByName(userName);
    }

    //判断某条菜谱是否被收藏
    public boolean isCollect(String userName, Integer cookbookId){
        boolean result = false;
        int count = cookCollectMapper.isCollect(userName, cookbookId);
        if(count >= 1){
            result = true;
        }
        return result;
    }

}
