package com.winston.service.impl;

import com.winston.entity.Dictionary;
import com.winston.entity.DictionaryExample;
import com.winston.exception.ErrorException;
import com.winston.mapper.DictionaryMapper;
import com.winston.service.IDictionaryService;
import com.winston.utils.result.CodeMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DictionaryServiceImpl
 * @Author: Winston
 * @Description: TODO
 * @Date:Create：in 2019/9/19 10:01
 * @Version：
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    private DictionaryMapper mapper;

    @Override
    public List<Dictionary> query(Dictionary dictionary) {
        DictionaryExample example = new DictionaryExample();
        DictionaryExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(dictionary.getDictType())){
            criteria.andDictTypeEqualTo(dictionary.getDictType());
        }
        if(StringUtils.isNotBlank(dictionary.getDictValue())){
            criteria.andDictValueEqualTo(dictionary.getDictValue());
        }
        return mapper.selectByExample(example);
    }

    @Override
    public void addDictionary(Dictionary dictionary) {
        DictionaryExample example = new DictionaryExample();
        example.createCriteria()
                .andDictTypeEqualTo(dictionary.getDictType())
                .andDictValueEqualTo(dictionary.getDictValue());
        List<Dictionary> dictionaries = mapper.selectByExample(example);
        if(dictionaries.size() > 0){
            throw new ErrorException(CodeMsg.DICTIONARY_ALERADY_EXIST);
        }
        mapper.insert(dictionary);
    }

}
