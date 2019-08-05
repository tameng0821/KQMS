package com.yyj.entity;
/**
 * @author Yanjiang
 * @create 2019-07-24 9:53
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@ClassName Department
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 9:53
 *@Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private Integer departmentId;
    private String departmentName;
}
