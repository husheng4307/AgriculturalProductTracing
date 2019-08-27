package site.beanyon.apt.common.entity.response;

import lombok.Data;

/**
 * 封装统一的HTTP请求响应模版
 *
 * @author BeanYon
 * 2019.07.25
 */
@Data
public class HttpResponse {
    /**
     * 是否请求成功
     */
    private boolean result;
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应描述
     */
    private String message;
    /**
     * 返回到客户端的数据
     */
    private Object data;

    public HttpResponse(ResponseType resultType, Object data) {
        this.result = resultType.getResult();
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
        this.data = data;
    }
}
