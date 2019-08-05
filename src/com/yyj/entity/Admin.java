package com.yyj.entity;
/**
 * @author Yanjiang
 * @create 2019-07-23 17:22
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@ClassName Admin
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/23 17:22
 *@Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

     private Integer adminID;
     private  String adminAccount;
     private  String adminPwd;
     private  String adminState;
     private  String adminRight;
     private  String adminName;



}
