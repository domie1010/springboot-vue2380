package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *在线举报：(OnlineReporting)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OnlineReporting")
public class OnlineReporting implements Serializable {

    //OnlineReporting编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_reporting_id")
    private Integer online_reporting_id;
    // 用户
    @Basic
    private Integer user;
    // 日期
    @Basic
    private Timestamp date;
    // 举报内容
    @Basic
    private String report_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
