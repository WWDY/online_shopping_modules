package utils;

import javax.servlet.http.HttpServletRequest;

import static constant.JwtConstant.HEADER_NAME;
import static constant.JwtConstant.HEADER_START;

/**
 * @author wwdy
 * @date 2022/4/5 10:50
 */
public class RequestUtil {

    public static String getRequestToken(HttpServletRequest request) {
        String value = request.getHeader(HEADER_NAME);
        if(value != null) {
            return value.substring(HEADER_START.length());
        }
        return null;
    }
}
