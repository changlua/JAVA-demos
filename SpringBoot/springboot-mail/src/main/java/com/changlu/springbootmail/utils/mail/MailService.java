package com.changlu.springbootmail.utils.mail;

import com.changlu.springbootmail.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.Paths;

/**
 * @author sungang
 * 邮箱工具类
 */
@Service
public class MailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromUser;

    public void sendMail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送者
        simpleMailMessage.setFrom(fromUser);
//        收件人
        simpleMailMessage.setTo("939974883@qq.com");
//        抄送人
        simpleMailMessage.setCc(fromUser);
//        邮件主题
        simpleMailMessage.setSubject("系统测试");
//        邮件内容
        simpleMailMessage.setText("hi,QQ mail");
        javaMailSender.send(simpleMailMessage);
    }

    public void sendMailIncludeFile() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //邮件发送者
        helper.setFrom(fromUser);
//        收件人
        helper.setTo("939974883@qq.com");
//        抄送人
        helper.setCc(fromUser);
//        邮件主题
        helper.setSubject("系统测试");
//        邮件内容
        helper.setText("hi,QQ mail");
//        helper.addAttachment("zhifeng.sql", new File((new SaveDataTask()).backupSqlAndData()));
        helper.addAttachment("static.zip", new File(ZipUtils.packet(Paths.get("C:\\Users\\93997\\Desktop\\upload"), Paths.get("C:\\Users\\93997\\Desktop\\static.zip"))));
        //文件内容
        javaMailSender.send(mimeMessage);
    }

    //将制定目录打包

}
