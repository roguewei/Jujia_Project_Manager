package com.winston.service;


import com.winston.entity.Picture;

import java.util.List;

/**
 * @ClassName IPictureService
 * @Author: Winston
 * @Description: TODO
 * @Date:Create：in  2019/09/09 9:48
 * @Version：
 */
public interface IPictureService {

    Picture queryById(Integer id);

    int savePic(Picture picture);

    int delById(Integer id);

    int delByList(List<String> picIds);

}
