package com.xupt.wf.epicure.mapper;

import com.xupt.wf.epicure.entity.CookBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-05
 * Time: 12:30
 */
public interface CookBookMapper {

    int addCookBook(CookBook cookBook);

    String getImagePath(int cookbookId);

    int updateCookBook(CookBook cookBook);

    int updateImage(@Param("cookbookId") Integer cookbookId, @Param("imagePath") String imagePath);

    CookBook queryById(@Param("cookbookId") Integer cookbookId);

    List<CookBook> queryListByName(String userName);

    List<CookBook> queryList();

    int getCookCount(String userName);

    int increCookCount(String userName);

    int decreCookCount(String userName);

    int deleteCookBook(@Param("cookbookId") Integer cookbookId, @Param("userName") String userName);

}
