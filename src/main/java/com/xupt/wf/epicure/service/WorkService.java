package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.controller.WorkController;
import com.xupt.wf.epicure.entity.Work;
import com.xupt.wf.epicure.mapper.WorkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        Work work = new Work();
        work.setWorkImage(workPath);
        int count = workMapper.addWork(work);
        int workId = work.getWorkId();
        if(count == 1){
            resultMap.put("workId", workId);
            resultMap.put("filePath", workPath);
        }
        return resultMap;
    }

    @Transactional
    public boolean updateWork(Work work) {
        boolean result = false;
        int count = workMapper.updateWork(work);
        if(1 == count) {
            int cookbookId = work.getCookbookId();
            count = workMapper.incrCookCount(cookbookId);
            if(1 == count){
                result = true;
            }
        }
        return result;
    }

    public Work getWorkById(int workId){
        return workMapper.getworkById(workId);
    }

    public boolean addScore(HttpServletRequest request, int workId, float workScore){
        boolean result = false;
        Integer cookbookId = (Integer) request.getSession().getAttribute("cookbookId");
        request.getSession().removeAttribute("cookbookId");
        int count = workMapper.updateScore(workScore, workId);
        if(1 == count && cookbookId != null) {
            List<Float> scoreList = workMapper.getScoreList(cookbookId);
            if(scoreList != null && scoreList.size() > 0){
                float avgScore = 0;
                int n = scoreList.size();
                for (Float score : scoreList ) {
                    avgScore += score;
                }
                avgScore = avgScore / n;
                avgScore =  (float)(Math.round(avgScore*10))/10;
                count = workMapper.updateAvgScore(avgScore, cookbookId);
                if(count == 1){
                    result = true;
                }
            }
        }
        return result;
    }
}
