package com.winston.service.impl;

import com.winston.entity.Picture;
import com.winston.service.IFileService;
import com.winston.service.IPictureService;
import com.winston.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FileServiceImpl
 * @Author: Winston
 * @Description: TODO
 * @Date:Create：in 2019/9/19 15:31
 * @Version：
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private IPictureService pictureService;

    @Override
    public List<String> uploadFile(List<MultipartFile> multipartFiles, String describe, String title, String type) {
        List<String> picList = excuteUpload(multipartFiles, title, describe, type);
        return picList;
    }

    @Override
    public void delFile(String path) {
        File file = new File(path);
        fileUtil.delFile(file);
    }

    private List<String> excuteUpload(List<MultipartFile> multipartFiles, String title, String describe, String type){
        List<String> picList = new ArrayList<>();
        try {
            for (MultipartFile file : multipartFiles) {
                Map<String, String> map = fileUtil.excuteUpload(file);

                String saveUrl = map.get("fileHttpUrl");
                String savePath = map.get("filePath");
                int picId = insertPic(saveUrl, savePath, title, describe, type);
                picList.add(String.valueOf(picId));
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return picList;
    }

    private Integer insertPic(String httpurl, String path, String title, String describe, String type){
        Picture picture = new Picture();
        picture.setTitle(title);
        picture.setPicDesc(title);
        picture.setType(type);
        picture.setPicUrl(httpurl);
        picture.setPicPath(path);
        Integer id = pictureService.savePic(picture);
        return id;
    }
}
