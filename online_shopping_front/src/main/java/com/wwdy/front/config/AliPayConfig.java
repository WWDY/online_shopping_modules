package com.wwdy.front.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.wwdy.front.constant.AliPayConstant.*;

/**
 * @author wwdy
 * @date 2022/4/8 19:52
 */
@Configuration
public class AliPayConfig {

    @Bean
    public AlipayClient alipayClient(AlipayConfig alipayConfig){
        try {
            return new DefaultAlipayClient(alipayConfig);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public AlipayConfig alipayConfig(){
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setAlipayPublicKey(PUBLIC_KEY);
        alipayConfig.setAppId(APP_ID);
        alipayConfig.setServerUrl(GATE_WAY);
        alipayConfig.setPrivateKey(PRIVATE_KEY);
        alipayConfig.setSignType(SIGN_TYPE);
        alipayConfig.setCharset(CHARSET);
        return alipayConfig;
    }
}
