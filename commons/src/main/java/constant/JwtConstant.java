package constant;

/**
 * @author wwdy
 * @date 2022/3/20 10:57
 */
public class JwtConstant {

    /**
     * redis key prefix
     */
    public static final String JWT_TOKEN_PREFIX = "AUTH:LOGIN:";

    /**
     * request header name
     */
    public static final String HEADER_NAME = "Authorization";

    /**
     * value start
     */
    public static final String HEADER_START = "Bearer ";
}
