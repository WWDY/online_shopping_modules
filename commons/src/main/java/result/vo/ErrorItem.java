package result.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author  wwdy
 * @date  2022/2/21 16:36
 */
@Data
@AllArgsConstructor
public class ErrorItem {
    /**
     * 错误名称
     */
    private String name;

    /**
     * 错误提示消息
     */
    private String message;
}
