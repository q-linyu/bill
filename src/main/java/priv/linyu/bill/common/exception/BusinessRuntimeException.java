package priv.linyu.bill.common.exception;

import priv.linyu.bill.common.enums.HttpState;

/**
 * @className: BusinessRuntimeException
 * @author: q-linyu
 * @description: 自定义业务异常
 * @date: 2019/12/17 20:40
 * @version: 1.0
 **/
public class BusinessRuntimeException extends RuntimeException{

    private HttpState httpState;



    public BusinessRuntimeException() {
        super();
    }

    public BusinessRuntimeException(HttpState httpState) {
        this.httpState = httpState;
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuntimeException(Throwable cause) {
        super(cause);
    }

    protected BusinessRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpState getHttpState() {
        return httpState;
    }

    public void setHttpState(HttpState httpState) {
        this.httpState = httpState;
    }
}

