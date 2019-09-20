package com.winston.service;


import com.winston.entity.User;
import com.winston.utils.result.Result;
import com.winston.utils.wechat.WeChatUser;

import java.util.List;
import java.util.Map;

public interface IUserService {

    Result queryByUser(User user, int page, int length);

    User selectByUsername(String username);

    User selectById(Integer id);

    User selectByOpenId(String openId);

    void save(WeChatUser weChatUser);

    int save(User user);

    void update(User user);

    void delete(Integer id);

}
