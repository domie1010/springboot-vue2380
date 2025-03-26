package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *在线留言：(OnlineMessage)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OnlineMessage")
public class OnlineMessage implements Serializable {

    //OnlineMessage编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "online_message_id")
    private Integer online_message_id;
    // 用户
    @Basic
    private Integer user;
    // 留言类型
    @Basic
    private String message_type;
    // 留言日期
    @Basic
    private Timestamp message_date;
    // 留言内容
    @Basic
    private String message_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
