package priv.linyu.bill.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import priv.linyu.bill.common.enums.HttpState;

import java.io.Serializable;

/**
 * @className: ResponseData
 * @author: q-linyu
 * @description:
 * @date: 2019/12/17 20:39
 * @version: 1.0
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> implements Serializable {

    /**
     * 返回的状态码
     */
    private Integer status;

    /**
     * 返回的消息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 返回结果不带数据
     * @param httpState
     */
    public ResponseData(HttpState httpState) {
        this.status = httpState.getStatus();
        this.msg = httpState.getMsg();
    }


    /**
     * 返回结果带数据
     * @param httpState
     * @param data
     */
    public ResponseData(HttpState httpState,T data) {
        this.status = httpState.getStatus();
        this.msg = httpState.getMsg();
        this.data = data;
    }


}