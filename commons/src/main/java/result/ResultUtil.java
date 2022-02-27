package result;

import result.enums.ResultEnum;
import result.vo.DataError;
import result.vo.ResultVO;


/**
 * @author wwdy
 * @date 2022/2/21 16:23
 */
public class ResultUtil {

    public static <T> ResultVO<T> success() {
        return success(null,ResultEnum.SUCCESS.getMessage());
    }

    public static <T> ResultVO<T> success(String message) {
        return success(null, message);
    }

    public static <T> ResultVO<T> success(T data) {
        return success(data, ResultEnum.SUCCESS.getMessage());
    }

    public static <T> ResultVO<T> success(T data, String message) {
        ResultVO<T> result = new ResultVO<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setSuccess(true);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static <NullType> ResultVO<NullType> error(String message, DataError error) {
        return error(ResultEnum.ERROR.getCode(), message, error);
    }

    public static <NullType> ResultVO<NullType> error() {
        return error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage(), null);
    }

    public static <NullType> ResultVO<NullType> error(String message) {
        return error(ResultEnum.ERROR.getCode(), message, null);
    }

    public static <NullType> ResultVO<NullType> error(Integer code,String message) {
        return error(code, message, null);
    }

    public static <NullType> ResultVO<NullType> error(DataError error) {
        return error(ResultEnum.ERROR, error);
    }

    public static <NullType> ResultVO<NullType> error(ResultEnum result, DataError error) {
        return error(result.getCode(), result.getMessage(), error);
    }

    public static <NullType> ResultVO<NullType> error(Integer code, String message, DataError error) {
        return ResultVO.<NullType>builder()
                .code(code)
                .success(false)
                .data(null)
                .message(message)
                .error(error)
                .build();
    }
}
