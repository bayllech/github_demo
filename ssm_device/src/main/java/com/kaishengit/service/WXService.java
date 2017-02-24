package com.kaishengit.service;

import com.kaishengit.exception.ServiceException.ServiceException;
import com.qq.wx.mp.aes.AesException;
import com.qq.wx.mp.aes.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WXService {

    @Value("${wx.token}")
    private String token;
    @Value("${wx.EncodingAESKey}")
    private String aesKey;
    @Value("${wx.corpid}")
    private String corpid;

    /**
     * 微信企业号初始化
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public String init(String msg_signature, String timestamp, String nonce, String echostr) {

        try {
            WXBizMsgCrypt crypt = new WXBizMsgCrypt(token, aesKey, corpid);
            return crypt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        } catch (AesException e) {
            throw new ServiceException("微信初始化异常", e);
        }
    }

}
