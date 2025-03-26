package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *疫情数据：(EpidemicData)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "EpidemicData")
public class EpidemicData implements Serializable {

    //EpidemicData编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "epidemic_data_id")
    private Integer epidemic_data_id;
    // 地区
    @Basic
    private String region;
    // 感染人数
    @Basic
    private String number_of_infected_persons;
    // 密接人数
    @Basic
    private String number_of_close_contacts;
    // 统计人
    @Basic
    private String statistician;
    // 备注
    @Basic
    private String remarks;
    // 日期
    @Basic
    private Timestamp date;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
