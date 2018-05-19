package com.xupt.wf.epicure.mapper;

import com.xupt.wf.epicure.entity.Work;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 15:53
 */
public interface WorkMapper {

    int addWork(Work work);

    int updateWork(Work work);

    int incrCookCount(int cookbookId);

    Work getworkById(int workId);

    int updateScore(@Param("workScore") float workScore, @Param("workId") int workId);

    List<Float> getScoreList(int cookbookId);

    int updateAvgScore(@Param("avgScore") float avgScore, @Param("cookbookId") int cookbookId);

}
