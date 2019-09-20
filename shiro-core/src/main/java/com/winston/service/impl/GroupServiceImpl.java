package com.winston.service.impl;

import com.winston.entity.Picture;
import com.winston.entity.ProGroup;
import com.winston.entity.ProGroupExample;
import com.winston.exception.ErrorException;
import com.winston.mapper.ProGroupMapper;
import com.winston.service.IFileService;
import com.winston.service.IGroupService;
import com.winston.service.IPictureService;
import com.winston.utils.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GroupServiceImpl
 * @Author: Winston
 * @Description: TODO
 * @Date:Create：in 2019/9/19 14:31
 * @Version：
 */
@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private ProGroupMapper mapper;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IPictureService pictureService;

    @Override
    public List<Map<String, Object>> queryAll(ProGroup group) {
        ProGroupExample example = new ProGroupExample();
        ProGroupExample.Criteria criteria = example.createCriteria();
        if(group.getGroupName() != null){
            criteria.andGroupNameLike("%"+group.getGroupName()+"%");
        }
        if(group.getGroupKeywork() != null){
            criteria.andGroupKeyworkLike("%" + group.getGroupKeywork() +"%");
        }
        if(group.getGroupDesc() != null){
            criteria.andGroupDescLike("%" + group.getGroupDesc() +"%");
        }
        List<ProGroup> groups = mapper.selectByExample(example);

        List<Map<String, Object>> resultList = new ArrayList<>();
        for(ProGroup proGroup : groups){
            Map<String, Object> map = setGroup(proGroup);
            resultList.add(map);
        }
        return resultList;
    }

    private Map<String, Object> setGroup(ProGroup proGroup) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", proGroup.getId());
        map.put("groupName", proGroup.getGroupName());
        map.put("groupKeywork", proGroup.getGroupKeywork());
        map.put("groupDesc", proGroup.getGroupDesc());
        if(proGroup.getGroupIcon() != null){
            Picture picture = pictureService.queryById(proGroup.getGroupIcon());
            map.put("icon", picture.getPicUrl());
        }
        return map;
    }

    @Override
    public Map<String, Object> queryById(Integer id) {
        ProGroup proGroup = mapper.selectByPrimaryKey(id);
        return setGroup(proGroup);
    }

    @Override
    public void addGroup(MultipartHttpServletRequest request) {
        String groupName = request.getParameterMap().get("groupName")[0];
        String groupKeywork = request.getParameterMap().get("groupKeywork")[0];
        String groupDesc = request.getParameterMap().get("groupDesc")[0];
        List<MultipartFile> groupIcon = request.getFiles("groupIcon");

        ProGroupExample example = new ProGroupExample();
        example.createCriteria().andGroupNameEqualTo(groupName).andGroupDescEqualTo(groupDesc);
        List<ProGroup> groups = mapper.selectByExample(example);
        if(groups != null && groups.size() > 0){
            throw new ErrorException(CodeMsg.Group_ALREADY_EXIST);
        }

        ProGroup group = new ProGroup();
        group.setGroupName(groupName);
        group.setGroupDesc(groupDesc);
        group.setGroupKeywork(groupKeywork);

        if(groupIcon != null && groupIcon.size() > 0){
            List<String> picIds = fileService.uploadFile(groupIcon, groupDesc, groupName, "1");
            if(picIds != null && picIds.size() > 0){
                group.setGroupIcon(Integer.valueOf(picIds.get(0)));
            }
        }
        mapper.insert(group);
    }

    @Override
    public void updateGroup(MultipartHttpServletRequest request) {
        String[] ids = request.getParameterMap().get("id");
        String groupName = request.getParameterMap().get("groupName")[0];
        String groupKeywork = request.getParameterMap().get("groupKeywork")[0];
        String groupDesc = request.getParameterMap().get("groupDesc")[0];
        List<MultipartFile> groupIcon = request.getFiles("groupIcon");

        ProGroup group = null;
        if(ids != null){
            group = mapper.selectByPrimaryKey(Integer.valueOf(ids[0]));
            if(groupIcon != null && groupIcon.size() > 0){
                if(group.getGroupIcon() != null)
                pictureService.delById(group.getGroupIcon());

                List<String> picId = fileService.uploadFile(groupIcon, groupDesc, groupName, "1");
                if(picId != null && picId.size() > 0){
                    group.setGroupIcon(Integer.valueOf(picId.get(0)));
                }
            }
        }

        group.setGroupName(groupName);
        group.setGroupDesc(groupDesc);
        group.setGroupKeywork(groupKeywork);


        mapper.updateByPrimaryKeySelective(group);
    }

    @Override
    public void delGroup(Integer id) {
        ProGroup proGroup = mapper.selectByPrimaryKey(id);
        pictureService.delById(proGroup.getGroupIcon());
        mapper.deleteByPrimaryKey(id);
    }
}
