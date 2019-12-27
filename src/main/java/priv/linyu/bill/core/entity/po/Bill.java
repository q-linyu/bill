package priv.linyu.bill.core.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @className: Bill
 * @author: QiuShangLin
 * @description: 账单实体类
 * @date: 2019/12/15 11:32
 * @version: 1.0
 **/
@Data
public class Bill {

    /**
     * 主键id
     */
    private Integer bid;

    /**
     * 账单编码
     */
    private String billCode;

    /**
     * 账单名称
     */
    private String billName;

    /**
     * 续费,0：年，1：月，2：日，3：时
     */
    private Integer billCom;

    /**
     * 账单数量
     */
    private Integer billNum;

    /**
     * 金额
     */
    private Double money;

    /**
     * 是否付款 0 未付款， 1已付款
     */
    private Integer pay;

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
