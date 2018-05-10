package com.xupt.wf.epicure.mapper;

import com.xupt.wf.epicure.entity.Work;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 15:53
 */
public interface WorkMapper {

    int addWork(@Param("workId") Integer workId, @Param("workImage") String workImage);

    int updateWork(Work work);

}
