package priv.linyu.bill.core.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @className: Logs
 * @author: q-linyu
 * @description: 日志实体类
 * @date: 2019/12/27 12:58
 * @version: 1.0
 **/
@Data
public class Logs {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 主键id
     */
    private Integer userId;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 管理员对象
     */
    private UserInfo userInfo;

    /**
     * ip地址
     */
    @Transient
    private String ip;

}
