package com.yyj.service.Impl;
/**
 * @author Yanjiang
 * @create 2019-08-01 8:54
 */

import com.yyj.dao.AdminDao;
import com.yyj.entity.Admin;
import com.yyj.service.IAdminService;
import com.yyj.utils.MD5Utils;

/**
 *@ClassName AdminServiceImpl
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/8/1 8:54
 *@Version 1.0
 **/
public class AdminServiceImpl implements IAdminService {
    AdminDao adminDao = new AdminDao();

    @Override
    public Admin login(String username, String password) {
        String encrypt = MD5Utils.encrypt(password);
        Admin admin = (Admin)adminDao.get("select * from admin where adminAccount=? and adminPwd=?", Admin.class, new Object[]{username, encrypt});
        return admin;
    }
}
