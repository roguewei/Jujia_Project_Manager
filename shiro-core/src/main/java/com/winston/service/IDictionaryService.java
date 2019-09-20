package com.winston.service;


import com.winston.entity.Dictionary;

import java.util.List;

/**
 * @ClassName IParamService
 * @Author: Winston
 * @Description: TODO
 * @Date:Create：in  2019/09/09 9:48
 * @Version：
 */
public interface IDictionaryService {

    List<Dictionary> query(Dictionary dictionary);

    void addDictionary(Dictionary dictionary);

}
