package result.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  wwdy
 * @date  2022/2/21 16:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataError {
    /**
     * 异常领域
     */
    private String domain;

    /**
     * 异常类名
     */
    private String exception;

    /**
     * 详细错误信息
     */
    private List<ErrorItem> errors = new ArrayList<>();

    /**
     * 追加错误信息
     *
     * @param name 名称
     * @param message 错误提示
     */
    public void addError(String name, String message) {
        this.errors.add(new ErrorItem(name, message));
    }
}
