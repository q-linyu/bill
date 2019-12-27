package priv.linyu.bill.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.exception.BusinessRuntimeException;
import priv.linyu.bill.core.entity.ResponseData;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Objects;

/**
 * @className: GlobalExceptionHandler
 * @author: q-linyu
 * @description: 全局异常处理器
 * @date: 2019/12/17 20:41
 * @version: 1.0
 **/
@Slf4j
@RestControllerAdvice(basePackages = "priv.linyu.bill.web.controller")
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    public ResponseData<String> businessHandlerException(BusinessRuntimeException e) {
        log.error(e.getMessage(),e);
        return new ResponseData<>(e.getHttpState());
    }

    /**
     * 方法参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<String> methodArgumentNotValidHandlerException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        return new ResponseData<>(HttpState.BAD_REQUEST, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
    }

    /**
     * 参数错误异常
     * @param e
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseData<String> handleValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseData<>(HttpState.PARAM_FAIL_CODE);
    }


    /**
     * 参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseData<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return new ResponseData<>(HttpState.VALIDATION_CODE);
    }

    /**
     * 数据重复,请检查后提交
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseData<String> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return new ResponseData<>(HttpState.DUPLICATE_KEY_CODE);
    }


    /**
     * 404异常
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseData<String> handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseData<>(HttpState.NOT_FOUND);
    }


    /**
     * 自定义所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseData<String> globalHandlerException(Exception e) {
        log.error(e.getMessage(),e);
        return new ResponseData<>(HttpState.ERROR);
    }
}
