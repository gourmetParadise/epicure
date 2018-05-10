package com.xupt.wf.epicure.mapper;

import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-04-06
 * Time: 13:16
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    @Override
    int deleteByPrimaryKey(Object o);

    @Override
    int delete(T t);

    @Override
    int insert(T t);

    @Override
    int insertSelective(T t);

    @Override
    List<T> selectAll();

    @Override
    T selectByPrimaryKey(Object o);

    @Override
    int selectCount(T t);

    @Override
    List<T> select(T t);

    @Override
    T selectOne(T t);

    @Override
    int updateByPrimaryKey(T t);

    @Override
    int updateByPrimaryKeySelective(T t);

    @Override
    int deleteByExample(Object o);

    @Override
    List<T> selectByExample(Object o);

    @Override
    int selectCountByExample(Object o);

    @Override
    int updateByExample(T t, Object o);

    @Override
    int updateByExampleSelective(T t, Object o);

    @Override
    List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);

    @Override
    List<T> selectByRowBounds(T t, RowBounds rowBounds);

    @Override
    int insertList(List<T> list);

    @Override
    int insertUseGeneratedKeys(T t);
}
