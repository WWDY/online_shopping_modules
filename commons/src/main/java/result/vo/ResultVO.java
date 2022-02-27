package result.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  wwdy
 * @date  2022/2/21 16:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 成功状态
     */
    private Boolean success;

    /**
     * 错误信息
     */
    private DataError error;

    /**
     * 提示消息
     */
    private String message;
}
