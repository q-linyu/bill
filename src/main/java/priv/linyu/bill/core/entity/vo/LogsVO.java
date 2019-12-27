package priv.linyu.bill.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @className: LogsVO
 * @author: q-linyu
 * @description:
 * @date: 2019/12/27 14:08
 * @version: 1.0
 **/
@Data
public class LogsVO {

    /**
     * 主键id
     */
    private Long id;


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
     * 管理员
     */
    private String username;

    /**
     * ip地址
     */
    @Transient
    private String ip;

}
