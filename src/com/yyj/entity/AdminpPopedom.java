package com.yyj.entity;
/**
 * @author Yanjiang
 * @create 2019-07-24 9:52
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@ClassName AdminpPopedom
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 9:52
 *@Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminpPopedom {

    Integer popedomID;
    Integer departmentID;
    Integer adminID;
    Admin admin;
    Department department;
}
