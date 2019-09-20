package com.winston.service;

import com.winston.entity.ProGroup;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;

public interface IGroupService {

    List<Map<String, Object>> queryAll(ProGroup group);

    Map<String, Object> queryById(Integer id);

    void addGroup(MultipartHttpServletRequest request);

    void updateGroup(MultipartHttpServletRequest request);

    void delGroup(Integer id);

}
