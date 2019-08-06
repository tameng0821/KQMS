package com.yyj.service;
/**
 * @author Yanjiang
 * @create 2019-07-23 17:23
 */

import com.yyj.entity.Admin;

/**
 *@ClassName IAdminService
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/23 17:23
 *@Version 1.0
 **/
public interface IAdminService {
    public Admin login(String username,String password);
}
