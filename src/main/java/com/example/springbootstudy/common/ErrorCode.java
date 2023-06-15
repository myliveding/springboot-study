package com.example.springbootstudy.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码
 *
 * @author wangl 2023-04-18
 */
@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorCodeAncestor {

    // 公共
    COMMON_01("B-COMMON0001", "查询对象不存在"),

    // 成员管理
    MEMBER_01("B-MEMBER0001", "导入人数超过企业剩余可加入成员数"),

    MEMBER_02("B-MEMBER0002", "请控制单次导入行数在500行以内"),

    SMS_V_CODE_LIMIT("B-COMMON0002", "操作过于频繁，请15分钟后再次发送短息"),

    ;

    @JsonValue
    private final String resultCode;

    private final String errMsg;

}