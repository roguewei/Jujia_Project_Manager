package com.winston.entity;

import java.io.Serializable;

public class Dictionary implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.dict_name
     *
     * @mbg.generated
     */
    private String dictName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.dict_type
     *
     * @mbg.generated
     */
    private String dictType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.dict_value
     *
     * @mbg.generated
     */
    private String dictValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dictionary.dict_desc
     *
     * @mbg.generated
     */
    private String dictDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dictionary
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.id
     *
     * @return the value of dictionary.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.id
     *
     * @param id the value for dictionary.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.dict_name
     *
     * @return the value of dictionary.dict_name
     *
     * @mbg.generated
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.dict_name
     *
     * @param dictName the value for dictionary.dict_name
     *
     * @mbg.generated
     */
    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.dict_type
     *
     * @return the value of dictionary.dict_type
     *
     * @mbg.generated
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.dict_type
     *
     * @param dictType the value for dictionary.dict_type
     *
     * @mbg.generated
     */
    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.dict_value
     *
     * @return the value of dictionary.dict_value
     *
     * @mbg.generated
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.dict_value
     *
     * @param dictValue the value for dictionary.dict_value
     *
     * @mbg.generated
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dictionary.dict_desc
     *
     * @return the value of dictionary.dict_desc
     *
     * @mbg.generated
     */
    public String getDictDesc() {
        return dictDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dictionary.dict_desc
     *
     * @param dictDesc the value for dictionary.dict_desc
     *
     * @mbg.generated
     */
    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc == null ? null : dictDesc.trim();
    }
}