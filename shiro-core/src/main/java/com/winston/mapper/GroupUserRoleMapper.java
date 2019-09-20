package com.winston.mapper;

import com.winston.entity.GroupUserRole;
import com.winston.entity.GroupUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    long countByExample(GroupUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    int deleteByExample(GroupUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    int insert(GroupUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    int insertSelective(GroupUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    List<GroupUserRole> selectByExample(GroupUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    GroupUserRole selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") GroupUserRole record, @Param("example") GroupUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") GroupUserRole record, @Param("example") GroupUserRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GroupUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_user_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GroupUserRole record);
}