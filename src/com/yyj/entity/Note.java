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
 *@ClassName Note
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/7/24 9:53
 *@Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    private int noteId;
    private int employeeId;
    private int noteTypeId;
    private String cause;
    private Date fillInTime;
    private String directorSign;
    private String adminstrationSign;
    private String presidentSign;
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
    private int adminId;
    private int operatorId;
    private String isVerify;

    private String employeeName ;
    private String typeName;
    private String operatorName;
    private String operatorCardNumber;
    private Date attendanceDate;
    private String cardNumber;

    public Note(int noteId, int employeeId, int noteTypeId, String cause, Date fillInTime, String directorSign,
                String administrationSign, String presidentSign, Date startDate, String startTime, Date endDate,
                String endTime, int adminId, int operatorId, String isVerify) {
        super();
        this.noteId = noteId;
        this.employeeId = employeeId;
        this.noteTypeId = noteTypeId;
        this.cause = cause;
        this.fillInTime = fillInTime;
        this.directorSign = directorSign;
        this.adminstrationSign = administrationSign;
        this.presidentSign = presidentSign;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.adminId = adminId;
        this.operatorId = operatorId;
        this.isVerify = isVerify;
    }
}
