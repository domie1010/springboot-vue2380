package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *健康信息上报：(HealthInformationReporting)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HealthInformationReporting")
public class HealthInformationReporting implements Serializable {

    //HealthInformationReporting编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_information_reporting_id")
    private Integer health_information_reporting_id;
    // 用户
    @Basic
    private Integer user;
    // 姓名
    @Basic
    private String full_name;
    // 身份证
    @Basic
    private String id;
    // 体温
    @Basic
    private String temperature;
    // 是否咳嗽
    @Basic
    private String cough;
    // 是否发热
    @Basic
    private String is_it_hot;
    // 是否乏力
    @Basic
    private String is_it_weak;
    // 是否呼吸困难
    @Basic
    private String is_it_difficult_to_breathe;
    // 日期
    @Basic
    private Timestamp date;
    // 身体症状
    @Basic
    private String physical_symptoms;
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
