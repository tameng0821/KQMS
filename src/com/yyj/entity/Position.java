package com.yyj.entity;
/**
 * @author Yanjiang
 * @create 2019-07-24 9:54
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@ClassName Position
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 9:54
 *@Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private Integer positionID;
    private String positionName;
}
