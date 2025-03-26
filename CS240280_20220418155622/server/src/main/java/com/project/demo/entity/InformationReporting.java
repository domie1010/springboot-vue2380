package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *上报信息情况：(InformationReporting)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "InformationReporting")
public class InformationReporting implements Serializable {

    //InformationReporting编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "information_reporting_id")
    private Integer information_reporting_id;
    // 信息类型
    @Basic
    private String information_type;
    // 数量
    @Basic
    private String number;
    // 日期
    @Basic
    private Timestamp date;
    // 统计人
    @Basic
    private String statistician;
    // 备注
    @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
