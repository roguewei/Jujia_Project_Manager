package com.winston.mapper;

import com.winston.entity.StaffClient;
import com.winston.entity.StaffClientExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StaffClientMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_client
     *
     * @mbg.generated
     */
    long countByExample(StaffClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_client
     *
     * @mbg.generated
     */
    int deleteByExample(StaffClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_client
     *
     * @mbg.generated
     */
    int insert(StaffClient record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_client
     *
     * @mbg.generated
     */
    int insertSelective(StaffClient record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_client
     *
     * @mbg.generated
     */
    List<StaffClient> selectByExample(StaffClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_client
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StaffClient record, @Param("example") StaffClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_client
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StaffClient record, @Param("example") StaffClientExample example);
}