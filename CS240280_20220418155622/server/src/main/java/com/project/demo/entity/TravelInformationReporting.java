package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *行程信息上报：(TravelInformationReporting)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TravelInformationReporting")
public class TravelInformationReporting implements Serializable {

    //TravelInformationReporting编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_information_reporting_id")
    private Integer travel_information_reporting_id;
    // 用户
    @Basic
    private Integer user;
    // 姓名
    @Basic
    private String full_name;
    // 身份证
    @Basic
    private String id;
    // 日期
    @Basic
    private Timestamp date;
    // 行程
    @Basic
    private String trip;
    // 备注
    @Basic
    private String remarks;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
