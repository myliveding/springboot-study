package com.example.springbootstudy.common;

/**
 * @author dingzr 2023-06-14 13:36
 */
public class BizException extends RuntimeException{

        private static final long serialVersionUID = -4104806330438981374L;
        private String code;

        public BizException(String errMsg) {
            super(errMsg);
            this.code = "C6";
        }

        public BizException(ErrorCodeAncestor errorCode) {
            super(errorCode.getErrMsg());
            this.code = errorCode.getResultCode();
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }
}
