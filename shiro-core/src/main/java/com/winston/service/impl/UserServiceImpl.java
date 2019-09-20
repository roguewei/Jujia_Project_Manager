package com.winston.service.impl;

import com.github.pagehelper.PageHelper;
import com.winston.entity.User;
import com.winston.entity.UserExample;
import com.winston.exception.ErrorException;
import com.winston.mapper.UserMapper;
import com.winston.service.IUserService;
import com.winston.utils.jwt.RawAccessJwtToken;
import com.winston.utils.result.CodeMsg;
import com.winston.utils.result.Result;
import com.winston.utils.shiro.PasswordHelper;
import com.winston.utils.wechat.WeChatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Winston
 * @title: UserServiceImpl
 * @projectName shiroDemo
 * @description:
 * @date 2019/7/24 14:25
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RawAccessJwtToken rawAccessJwtToken;

    @Override
    public Result queryByUser(User user, int page, int length) {
        PageHelper.startPage(page, length);

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if(user.getId() != null){
            criteria.andIdEqualTo(user.getId());
        }
        if(user.getUsername() != null){
            criteria.andUsernameEqualTo(user.getUsername());
        }
        List<User> users = mapper.selectByExample(example);
        List<Map<String, Object>> result = new ArrayList<>();
        if(users != null && users.size() > 0){
            for(User us : users){
                Map<String, Object> userMap = result(us);
                result.add(userMap);
            }
        }
        return Result.success(result);
    }

    @Override
    public User selectByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = mapper.selectByExample(example);
        if(users != null && users.size() > 0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public User selectById(Integer id) {
        User user = mapper.selectByPrimaryKey(id);
        user.setPassword("");
        return user;
    }

    @Override
    public User selectByOpenId(String openId) {
        return null;
    }

    @Override
    public void save(WeChatUser weChatUser) {
//        String nicname = weChatUser.getNickname();
//        long nowTime = new Date().getTime();
//
//        User user = new User();
//        user.setOpenId(weChatUser.getOpenId());
//        user.setOpenidHex(weChatUser.getOpenId());
//        user.setEnable(1);
//        user.setSex(String.valueOf(weChatUser.getSex()));
//        user.setNickName(weChatUser.getNickname());
//        user.setState("1");
//        user.setCreateOpr(nicname);
//        user.setCreateTime(nowTime);
//        user.setUpdateOpr(nicname);
//        user.setUpdateTime(nowTime);
//        user.setOperatorType("1");
//        PasswordHelper passwordHelper = new PasswordHelper();
//        passwordHelper.encryptPasswordWx(user);
//        save(user);
    }

    @Override
    public int save(User user) {

        if(user.getUsername() == null){
            throw new ErrorException(CodeMsg.USER_ADD_FAILE);
        }
        User dbUser = selectByUsername(user.getUsername());
        if(dbUser != null){
            throw new ErrorException(CodeMsg.USER_ALREADY_EXIST);
        }

        String manager = rawAccessJwtToken.getUserName();
        long nowTime = new Date().getTime();

        user.setStatus(1);
        user.setCreateOpr(manager);
        user.setCreateTime(nowTime);
        user.setUpdateOpr(manager);
        user.setUpdateTime(nowTime);
//        user.setOperatorType("0");
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);
        mapper.insert(user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        user.setPassword(null);
        int i = mapper.updateByPrimaryKeySelective(user);
        if(i <= 0){
            throw new ErrorException(CodeMsg.USER_UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        User user = mapper.selectByPrimaryKey(id);
        if(user != null){
            user.setStatus(0);
            mapper.updateByPrimaryKeySelective(user);
        }else{
            throw new ErrorException(CodeMsg.USER_DEL_FAILE);
        }
    }

    //    @Override
//    public User queryByOpenId(String openId) {
//        UserExample example = new UserExample();
//        example.createCriteria().andOpenIdEqualTo(openId);
//        List<User> users = mapper.selectByExample(example);
//        return users.get(0);
//    }

    private Map<String, Object> result(User user){
        Map<String, Object> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("relName", user.getRelName());
        result.put("jobNumber", user.getJobNumber());
        result.put("sex", user.getSex());
        result.put("mobile", user.getMobile());
        result.put("email", user.getEmail());
        result.put("address", user.getAddress());
        return result;
    }
}
