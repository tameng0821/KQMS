package com.yyj.entity;
/**
 * @author Yanjiang
 * @create 2019-07-24 9:53
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.net.DatagramPacket;

/**
 *@ClassName Employee
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 9:53
 *@Version 1.0
 **/
@Data
@NoArgsConstructor
public class Employee {

    private Integer employeeID;
    private String employeeName;
    private String employeeGender;
    private Integer positionID;
    private Integer departmentID;
    private String cardNumber;
    private String employeeState;
    private String employeeMemo;

    public Employee(Integer employeeID, String employeeName, String employeeGender, Integer positionID, Integer departmentID, String cardNumber, String employeeState, String employeeMemo) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.positionID = positionID;
        this.departmentID = departmentID;
        this.cardNumber = cardNumber;
        this.employeeState = employeeState;
        this.employeeMemo = employeeMemo;
    }

    String positionName;
    String departmentName;
}
