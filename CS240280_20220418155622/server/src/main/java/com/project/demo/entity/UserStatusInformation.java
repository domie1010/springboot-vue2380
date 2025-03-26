package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *用户状态信息：(UserStatusInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "UserStatusInformation")
public class UserStatusInformation implements Serializable {

    //UserStatusInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_status_information_id")
    private Integer user_status_information_id;
    // 日期
    @Basic
    private Timestamp date;
    // 健康人数
    @Basic
    private String number_of_healthy_people;
    // 不适人数
    @Basic
    private String number_of_discomfort;
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
