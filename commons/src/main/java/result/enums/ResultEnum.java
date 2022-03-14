package result.enums;

import lombok.Getter;


/**
 * @author  wwdy
 * @date  2022/2/21 16:34
 */
@Getter
public enum ResultEnum {
    /**
     * 全局响应状态码
     */
    SUCCESS(0, "success"),
    ERROR(10000000, "error"),
    UNEXPECTED_ERROR(10001000, "服务发生非预期异常，请联系管理员"),
    PARAM_VALIDATED_UN_PASS(10002000, "参数校验(JSR303)不通过，请检查参数或联系管理员"),
    MISSING_SERVLET_REQUEST_PARAMETER_ERROR(10002001, "Query请求参数校验不通过，请检查参数或联系管理员"),
    METHOD_ARGUMENT_TYPE_MISMATCH_ERROR(10002002, "方法请求参数类型不匹配，请检查参数或联系管理员"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_ERROR(10002003, "不支持的请求方法，请检查 API 或联系管理员"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_ERROR(10002004, "不支持的互联网媒体类型，请检查 API 或联系管理员"),
    AUTHENTICATION(10004001, "很抱歉, 用户认证失效"),
    AUTHORIZATION(10004003, "很抱歉, 暂无权限操作"),
    MESSAGE_CODE_ERROR(10005001,"验证码不正确"),
    PHONE_IS_EXIST(10006001,"手机号已存在");

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
