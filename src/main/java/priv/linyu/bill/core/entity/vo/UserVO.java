package priv.linyu.bill.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @className: UserVO
 * @author: q-linyu
 * @description:
 * @date: 2019/12/17 20:45
 * @version: 1.0
 **/
@Data
public class UserVO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 登录的时间
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date loginTime;

    /**
     * 会话sessionId
     */
    private String sessionId;
}
