package result.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author  wwdy
 * @date  2022/2/21 16:35
 */
@Data
@NoArgsConstructor
public class PageVO<T> {
    private List<T> content;

    private Long page;

    private Integer size;

    private Long total;

    public static <T> PageVO<T> of(List<T> content, Long page, Integer size, Long total) {
        return new PageVO<>(content, page, size, total);
    }

    public PageVO(List<T> content, Long page, Integer size, Long total) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.total = total;
    }
}
