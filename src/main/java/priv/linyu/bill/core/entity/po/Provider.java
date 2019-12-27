package priv.linyu.bill.core.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @className: Provider
 * @author: QiuShangLin
 * @description: 供应商实体类
 * @date: 2019/12/15 11:35
 * @version: 1.0
 **/
@Data
public class Provider {

    /**
     * 主键id
     */
    private Integer pid;

    /**
     * 供应商编码
     */
    private String providerCode;

    /**
     * 供应商名称
     */
    private String providerName;

    /**
     *
     */
    private String people;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 传真
     */
    private String fax;

    /**
     * 描述
     */
    private String describe;

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
