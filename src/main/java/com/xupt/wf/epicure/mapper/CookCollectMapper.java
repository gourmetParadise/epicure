package com.xupt.wf.epicure.mapper;

import com.xupt.wf.epicure.entity.CookCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 20:06
 */
public interface CookCollectMapper {

    int addCollect(CookCollect cookCollect);

    int deleteCollect(@Param("userName") String userName, @Param("collectId") Integer collectId);

    List<CookCollect> queryListByName(String userName);

    int isCollect(@Param("userName") String userName, @Param("cookbookId") Integer cookbookId);

}
