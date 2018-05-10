package com.xupt.wf.epicure.mapper;

import com.xupt.wf.epicure.entity.Ingredients;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-06
 * Time: 22:00
 */
public interface IngredientsMapper {

    int addList(List<Ingredients> ingreList);

    int deleteIngre(int cookbookId);

    int updateIngre(int cookbookId);
}
