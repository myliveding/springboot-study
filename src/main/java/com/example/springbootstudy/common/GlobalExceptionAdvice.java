package com.example.springbootstudy.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author dingzr 2023-06-14 13:38
 */
@RestControllerAdvice
@Order(-2147483648)
public class GlobalExceptionAdvice {

        private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvice.class);
        @Autowired
        private HttpServletResponse httpServletResponse;

        public GlobalExceptionAdvice() {
        }

        @ExceptionHandler({NoHandlerFoundException.class})
        public RequestResult<?> handler(NoHandlerFoundException e) {
            log.warn(e.getMessage());
            this.httpServletResponse.addHeader("X-Trace-Id", MDC.get("traceId"));
            this.httpServletResponse.addHeader("X-Span-Id", MDC.get("spanId"));
            return RequestResult.failure(new ErrorCodeAncestor() {
                @Override
                public String getResultCode() {
                    return "C4";
                }

                @Override
                public String getErrMsg() {
                    return null;
                }
            });
        }

        @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
        public RequestResult<?> handler(HttpRequestMethodNotSupportedException e) {
            String supportedMethods = Arrays.toString(e.getSupportedMethods());
            log.warn(e.getMessage() + " " + supportedMethods);
            this.httpServletResponse.addHeader("X-Trace-Id", MDC.get("traceId"));
            this.httpServletResponse.addHeader("X-Span-Id", MDC.get("spanId"));
            return RequestResult.failure(new ErrorCodeAncestor() {
                @Override
                public String getResultCode() {
                    return "C1";
                }

                @Override
                public String getErrMsg() {
                    return null;
                }
            });
        }

        @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
        public RequestResult<?> handler(HttpMediaTypeNotSupportedException e) {
            log.warn(e.getMessage() + "  [application/json]");
            this.httpServletResponse.addHeader("X-Trace-Id", MDC.get("traceId"));
            this.httpServletResponse.addHeader("X-Span-Id", MDC.get("spanId"));
            return RequestResult.failure(new ErrorCodeAncestor() {
                @Override
                public String getResultCode() {
                    return "C1";
                }

                @Override
                public String getErrMsg() {
                    return null;
                }
            });
        }

        @ExceptionHandler({HttpMessageNotReadableException.class})
        public RequestResult<?> httpMessageNotReadable(HttpMessageNotReadableException e) {
            log.warn("message={}", e.getMessage());
            this.httpServletResponse.addHeader("X-Trace-Id", MDC.get("traceId"));
            this.httpServletResponse.addHeader("X-Span-Id", MDC.get("spanId"));
            return RequestResult.failure(new ErrorCodeAncestor() {
                @Override
                public String getResultCode() {
                    return "C1";
                }

                @Override
                public String getErrMsg() {
                    return null;
                }
            });
        }

    /**
     * C6业务逻辑异常
     */
    @ExceptionHandler(BizException.class)
    public RequestResult<?> handle(BizException e) {
        httpServletResponse.addHeader("X-Trace-Id", MDC.get("traceId"));
        httpServletResponse.addHeader("X-Span-Id", MDC.get("spanId"));
        return RequestResult.failure(new ErrorCodeAncestor() {
            @Override
            public String getResultCode() {
                log.warn("抛出的BizException未指定code", e);
                return e.getCode();
            }

            @Override
            public String getErrMsg() {
                return e.getMessage();
            }
        });
    }

        @ExceptionHandler({Throwable.class})
        public RequestResult<?> handle(Throwable e) {
            log.error("统一异常处理被击穿！", e);
            this.httpServletResponse.addHeader("X-Trace-Id", MDC.get("traceId"));
            this.httpServletResponse.addHeader("X-Span-Id", MDC.get("spanId"));
            return RequestResult.failure(new ErrorCodeAncestor() {
                @Override
                public String getResultCode() {
                    return "C5";
                }

                @Override
                public String getErrMsg() {
                    return String.format("服务异常，请稍后重试（错误代码：%s）", MDC.get("traceId"));
                }
            });
        }
}
