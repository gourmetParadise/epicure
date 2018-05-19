package com.xupt.wf.epicure.mapper;

import com.xupt.wf.epicure.entity.UserHistory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-15
 * Time: 22:41
 */
public interface UserHistoryMapper {

    int addUserHistory(UserHistory userHistory);

    List<UserHistory> queryListByName(String userName);

}
