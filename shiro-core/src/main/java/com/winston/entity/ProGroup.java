package com.winston.entity;

import java.io.Serializable;

public class ProGroup implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_group.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_group.group_name
     *
     * @mbg.generated
     */
    private String groupName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_group.group_keywork
     *
     * @mbg.generated
     */
    private String groupKeywork;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_group.group_desc
     *
     * @mbg.generated
     */
    private String groupDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pro_group.group_icon
     *
     * @mbg.generated
     */
    private Integer groupIcon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pro_group
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_group.id
     *
     * @return the value of pro_group.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_group.id
     *
     * @param id the value for pro_group.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_group.group_name
     *
     * @return the value of pro_group.group_name
     *
     * @mbg.generated
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_group.group_name
     *
     * @param groupName the value for pro_group.group_name
     *
     * @mbg.generated
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_group.group_keywork
     *
     * @return the value of pro_group.group_keywork
     *
     * @mbg.generated
     */
    public String getGroupKeywork() {
        return groupKeywork;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_group.group_keywork
     *
     * @param groupKeywork the value for pro_group.group_keywork
     *
     * @mbg.generated
     */
    public void setGroupKeywork(String groupKeywork) {
        this.groupKeywork = groupKeywork == null ? null : groupKeywork.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_group.group_desc
     *
     * @return the value of pro_group.group_desc
     *
     * @mbg.generated
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_group.group_desc
     *
     * @param groupDesc the value for pro_group.group_desc
     *
     * @mbg.generated
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pro_group.group_icon
     *
     * @return the value of pro_group.group_icon
     *
     * @mbg.generated
     */
    public Integer getGroupIcon() {
        return groupIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pro_group.group_icon
     *
     * @param groupIcon the value for pro_group.group_icon
     *
     * @mbg.generated
     */
    public void setGroupIcon(Integer groupIcon) {
        this.groupIcon = groupIcon;
    }
}