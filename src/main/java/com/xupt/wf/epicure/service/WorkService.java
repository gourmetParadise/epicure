package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.controller.WorkController;
import com.xupt.wf.epicure.entity.Work;
import com.xupt.wf.epicure.mapper.WorkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 16:01
 */
@Service
public class WorkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkController.class);

    @Autowired
    private WorkMapper workMapper;

    @Transactional
    public Map<String, Object> addWorkImage(String workPath){
        Map<String, Object> resultMap = new HashMap<>();
        Integer workId = 0;
        int count = workMapper.addWork(workId, workPath);
        if(count == 1){
            resultMap.put("workId", workId);
            resultMap.put("workImage", workPath);
        }
        return resultMap;
    }

//    public boolean updateWork(Work work) {
//
//    }

}
