package com.blue.rabbitmq.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliYunSmsService {
    @Value("${aliyun.sms.AccessKeyId}")
    private String AccessKeyId;
    @Value("${aliyun.sms.Secret}")
    private String Secret;
    @Value("${aliyun.sms.SignName}")
    private String SignName;
    @Value("${aliyun.sms.TemplateCode}")
    private String TemplateCode;
    @Value("${aliyun.sms.regionId}")
    private String regionId;
    public void sendSms(String phone,String phoneCode) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(regionId,AccessKeyId,Secret);
        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setSignName(SignName);
        request.setTemplateCode(TemplateCode);
        request.setPhoneNumbers(phone);
        request.setTemplateParam("{code:"+ phoneCode +"}");
        client.getAcsResponse(request);
    }
}


