package com.banmenit.libcore.web.exception;

import com.banmenit.libcore.common.api.ResultCode;
import com.banmenit.libcore.common.exception.ApiException;
import com.banmenit.libcore.common.exception.ArgumentsIllegalException;
import com.banmenit.libcore.common.exception.BizException;
import com.banmenit.libcore.web.api.ActionResult;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局异常处理：如果这里处理过程中再有异常是不会被拦截的只会拦截从controller出来的异常
 *
 * @author tangsq
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public void handle(Exception e) {
        log.error("[异常组件]请求异常:", e);
        //返回500
        ActionResult.fail();

    }

    /**
     * ApiException 对应状态码默认为500, 可修改
     * BizException 默认为400
     *
     * @param e 自定义异常
     */
    @ResponseBody
    @ExceptionHandler(value = {ArgumentsIllegalException.class, BizException.class, ApiException.class})
    public void handleSystemException(ApiException e) {
        log.error("[异常组件]请求异常:", e);
        ActionResult.responseFail(e.getHttpStatus(), e.getCode(), e.getMessage(), e.getStatus());
    }

    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public void handleMethodNotSupportedException(Exception e) throws Exception {

        log.error("An HttpRequestMethodNotSupportedException has occurred: ", e);
        throw e;

    }


    /**
     * MethodArgumentNotValidException父类是BindException
     * MethodArgumentNotValidException是针对json校验结果
     * BindException是对普通表单校验结果
     * ConstraintViolationException是对
     *
     * @param e 异常
     */
    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class})
    public void handleValidException(Exception e) {
        log.error("An method argument valid exception has occurred: ", e);
        StringBuilder message = new StringBuilder();
        if (e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            if (bindingResult.hasErrors()) {
                FieldError fieldError = bindingResult.getFieldError();
                if (fieldError != null) {
                    message = new StringBuilder(fieldError.getField() + fieldError.getDefaultMessage());
                }
            }
            ActionResult.responseFail400(message.toString(), ResultCode.VALIDATE_FAILED.toString());
            return;
        }

        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            if (bindingResult.hasErrors()) {
                FieldError fieldError = bindingResult.getFieldError();
                if (fieldError != null) {
                    message = new StringBuilder(fieldError.getField() + fieldError.getDefaultMessage());
                }
            }
            ActionResult.responseFail400(message.toString(), ResultCode.VALIDATE_FAILED.toString());
            return;
        }

        if (e instanceof ConstraintViolationException) {
            //Controller上加@Validated,对于@RequestParam参数加@NotBlank @NotNull等抛出的异常如:@RequestParam @Min(1) Integer age
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            if (constraintViolations == null) {
                return;
            }
            for (ConstraintViolation constraintViolation : constraintViolations) {
                constraintViolation.getMessageTemplate();
                PathImpl path = (PathImpl) constraintViolation.getPropertyPath();
                String argName = path.getLeafNode().getName();
                message.append(argName).append(constraintViolation.getMessage()).append(";");
            }
            ActionResult.responseFail400(message.toString(), ResultCode.VALIDATE_FAILED.toString());
        }
    }
}

