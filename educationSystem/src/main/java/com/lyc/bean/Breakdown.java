package com.lyc.bean;

import lombok.Data;

/**
 * 故障实体类
 */
@Data
public class Breakdown {
    private String breakdownId;         //主键id
    private String breakdownTitle;      //故障标题
    private String classRoom;           //故障教室
    private String breakdownContent;    //故障内容
    private String breakdownState;      //故障状态
    private String createBy;            //创建人
    private String createTime;          //创建时间
    private String solveBy;             //解决人
    private String solveTime;           //解决时间
    private String createName;          //创建人姓名
    private String solveName;           //解决人姓名

}
