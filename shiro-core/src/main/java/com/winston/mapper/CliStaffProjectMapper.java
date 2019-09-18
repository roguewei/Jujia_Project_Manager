package com.winston.mapper;

import com.winston.entity.CliStaffProject;
import com.winston.entity.CliStaffProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CliStaffProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cli_staff_project
     *
     * @mbg.generated
     */
    long countByExample(CliStaffProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cli_staff_project
     *
     * @mbg.generated
     */
    int deleteByExample(CliStaffProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cli_staff_project
     *
     * @mbg.generated
     */
    int insert(CliStaffProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cli_staff_project
     *
     * @mbg.generated
     */
    int insertSelective(CliStaffProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cli_staff_project
     *
     * @mbg.generated
     */
    List<CliStaffProject> selectByExample(CliStaffProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cli_staff_project
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CliStaffProject record, @Param("example") CliStaffProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cli_staff_project
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CliStaffProject record, @Param("example") CliStaffProjectExample example);
}