package com.yyj.entity;
/**
 * @author Yanjiang
 * @create 2019-07-24 9:53
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *@ClassName AttendanceRecord
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 9:53
 *@Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecord {

    private Integer attendanceID;
    private Integer employeeID;
    private String cardNumber;
    private String attendanceDate;
    private String attendanceFlag;
    private Integer attendanceTypeID;
    private Integer adminID;
    private Integer noteID;

    //新增需要的属性
    private String employeeName;
    private String departmentName;
    private Integer departmentId;
}
