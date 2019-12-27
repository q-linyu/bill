package priv.linyu.bill.core.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @className: Customer
 * @author: q-linyu
 * @description: 客户实体类
 * @date: 2019/12/18 23:18
 * @version: 1.0
 **/
@Data
public class Customer {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 客户名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别，1：男  0：女
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态，1：已激活  0：未激活
     */
    private Integer state;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

}