package com.winston.mapper;

import com.winston.entity.RolePermission;
import com.winston.entity.RolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated
     */
    long countByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated
     */
    int deleteByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated
     */
    int insert(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated
     */
    int insertSelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated
     */
    List<RolePermission> selectByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_permission
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);
}