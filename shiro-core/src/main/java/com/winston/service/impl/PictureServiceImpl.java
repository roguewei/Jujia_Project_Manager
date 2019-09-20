package com.winston.service.impl;

import com.winston.entity.Picture;
import com.winston.mapper.PictureMapper;
import com.winston.service.IFileService;
import com.winston.service.IPictureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Winston
 * @title: PictureServiceImpl
 * @projectName shiro-parent
 * @description:
 * @date 2019/7/18 18:53
 */
@Service
public class PictureServiceImpl implements IPictureService {

    @Resource
    private PictureMapper mapper;

    @Autowired
    private IFileService fileService;

    @Override
    public Picture queryById(Integer id) {
        Picture picture = mapper.selectByPrimaryKey(id);
        return picture;
    }

    @Override
    public int savePic(Picture picture) {
//        String userName = rawAccessJwtToken.getUserName();
        long nowTime = new Date().getTime();
        picture.setCreateTime(nowTime);
        picture.setUpdateTime(nowTime);
        picture.setCreateOpr("system");
        picture.setUpdateOpr("system");
        picture.setState("1");
        mapper.insertSelective(picture);
        return picture.getId();
    }

    @Override
    public int delById(Integer id) {
        Picture picture = mapper.selectByPrimaryKey(id);
        if(picture != null && picture.getPicPath() != null){
            fileService.delFile(picture.getPicPath());
        }
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delByList(List<String> picIds) {
        int result = 0;
        for(String picId : picIds){
            if(StringUtils.isNotBlank(picId)){
                result = delById(Integer.valueOf(picId));
            }
        }
        return result;
    }
}
