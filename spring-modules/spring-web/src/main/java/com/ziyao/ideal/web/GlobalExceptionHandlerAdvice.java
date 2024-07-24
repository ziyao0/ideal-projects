package com.ziyao.ideal.web;


import com.ziyao.ideal.core.CommUtils;
import com.ziyao.ideal.web.exception.ServiceException;
import com.ziyao.ideal.web.exception.UnauthorizedException;
import com.ziyao.ideal.web.response.ResponseMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author zhangziyao
 */
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    /**
     * 方法参数校验失败异常处理.
     *
     * @param mex {@link org.springframework.web.bind.MethodArgumentNotValidException}
     * @return 参数校验失败统一响应处理 {@link ResponseMetadata}
     * @see MethodArgumentNotValidException#getBindingResult()
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMetadata methodArgumentNotValidException(MethodArgumentNotValidException mex) {
        if (mex != null && mex.getBindingResult().hasErrors())
            return buildIllegalArgument(ExceptionUtils.buildExceptionMessage(mex));
        else
            return ResponseBuilder.failed();
    }

    /**
     * 参数绑定错误异常处理.
     *
     * @param be {@link org.springframework.validation.BindException}
     * @return 包含参数绑定错误信息的 {@link ResponseMetadata}
     */
    @ExceptionHandler(value = org.springframework.validation.BindException.class)
    public ResponseMetadata bindExceptionHandler(org.springframework.validation.BindException be) {
        return buildIllegalArgument(ExceptionUtils.buildExceptionMessage(be));
    }


    /**
     * 单参数校验错误异常处理.
     *
     * @param e {@link ConstraintViolationException}
     * @return 包含单参数校验失败信息的 {@link ResponseMetadata}
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseMetadata constraintViolationExceptionHandler(ConstraintViolationException e) {
        return buildIllegalArgument(ExceptionUtils.buildExceptionMessage(e));
    }

    /**
     * 参数类型错误异常处理.
     *
     * @param e {@link MethodArgumentTypeMismatchException}
     * @return 包含参数类型错误信息的 {@link ResponseMetadata}
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseMetadata methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        return buildIllegalArgument(ExceptionUtils.buildExceptionMessage(e));
    }

    /**
     * 自定义业务异常处理.
     *
     * @param e {@link com.ziyao.ideal.web.exception.ServiceException}
     * @return 包含业务异常信息的 {@link ResponseMetadata}
     */
    @ExceptionHandler(value = ServiceException.class)
    public ResponseMetadata serviceExceptionHandler(ServiceException e) {
        Throwable causeThrowable = e.getCause();
        if (causeThrowable != null) {
            LOGGER.error("发生由其他异常导致的业务异常", causeThrowable);
        }
        return ResponseMetadata.getInstance(e.getStatus(), e.getMessage());
    }

    /**
     * 自定义业务异常处理.
     *
     * @param e {@link ServiceException}
     * @return 包含业务异常信息的 {@link ResponseMetadata}
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseMetadata unauthorizedExceptionHandler(UnauthorizedException e) {
        Throwable causeThrowable = e.getCause();
        if (causeThrowable != null) {
            LOGGER.error("发生由其他异常导致的业务异常", causeThrowable);
        }
        return ResponseMetadata.getInstance(e.getStatus(), e.getMessage());
    }

    /**
     * 处理参数错误异常.
     *
     * @param e {@link IllegalArgumentException}
     * @return 包含异常信息的 {@link ResponseMetadata}
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseMetadata illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return buildIllegalArgument(e.getMessage());
    }

    /**
     * 拦截 Exception
     *
     * @param e {@link Exception}
     * @return 包含异常信息的 {@link ResponseMetadata}
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseMetadata exceptionHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return ResponseBuilder.of(500, e.getClass().getName(),
                "服务器内部异常" + e.getMessage());
    }


    private ResponseMetadata buildIllegalArgument(String message) {
        return ResponseBuilder.of(400, message);
    }

    protected abstract static class ExceptionUtils {

        private ExceptionUtils() {
        }

        public static String buildExceptionMessage(MethodArgumentTypeMismatchException e) {

            if (null == e) return CommUtils.EMPTY_CHAR;

            StringBuilder sb = new StringBuilder();
            String name = e.getName();
            Class<?> expectType = e.getRequiredType();
            String actualType = ClassUtils.getDescriptiveType(e.getValue());
            Object value = e.getValue();

            sb.append("参数类型错误, 参数名: '").append(name).append("'");
            if (null != value) {
                sb.append(", 输入值: '").append(value).append("'");
            }
            if (null != expectType) {
                sb.append(", 期望类型: '").append(ClassUtils.getQualifiedName(expectType)).append("'");
            }
            sb.append(", 输入类型: '").append(actualType).append("'");
            return sb.toString();
        }

        public static String buildExceptionMessage(org.springframework.validation.BindException e) {
            return buildBindingResultMessage(e.getBindingResult());
        }

        public static String buildExceptionMessage(MethodArgumentNotValidException e) {
            return buildBindingResultMessage(e.getBindingResult());
        }

        public static String buildExceptionMessage(ConstraintViolationException e) {
            if (e == null || CollectionUtils.isEmpty(e.getConstraintViolations()))
                return CommUtils.EMPTY_CHAR;

            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            StringBuilder sb = new StringBuilder(violations.size() * 12);
            sb.append("参数校验失败: ");
            violations.forEach(error -> {
                sb.append("[");
                if (error instanceof FieldError fieldError) {
                    sb.append(fieldError.getField()).append(":");
                }
                sb.append(error.getMessage());
                sb.append("] ");
            });
            return sb.toString();
        }


        private static String buildBindingResultMessage(BindingResult bindingResult) {
            if (bindingResult == null)
                return CommUtils.EMPTY_CHAR;

            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder sb = new StringBuilder();
            sb.append("参数校验失败: ");
            errors.forEach(error -> {
                sb.append("[");
                if (error instanceof FieldError fieldError)
                    sb.append(fieldError.getField()).append(":");
                sb.append(error.getDefaultMessage());
                sb.append("] ");
            });
            return sb.toString();
        }
    }


}