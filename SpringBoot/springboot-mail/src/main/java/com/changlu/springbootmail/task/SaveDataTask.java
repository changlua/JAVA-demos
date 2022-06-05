package com.changlu.springbootmail.task;

import com.changlu.springbootmail.utils.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
class SaveDataPostConstruct{

    @Autowired
    private SaveDataTask saveDataTask;

    //项目启动时执行
    @PostConstruct
    public void backupSqlAndData(){
        saveDataTask.backupWorkByPostConstruct();
    }
}

@Component
@Slf4j
public class SaveDataTask {

	//备份sql
    @Value("${backup.sql.dataBackupPath}")
	private String dataBackupPath;//备份路径
    @Value("${backup.sql.host}")
	private String host;//数据库地址
    @Value("${backup.sql.port}")
	private Integer port = 3306;//数据库端口
    @Value("${backup.sql.username}")
	private String username = "root";//数据库用户名
    @Value("${backup.sql.password}")
	private String password = "123456";//数据库密码
    @Value("${backup.sql.dataBaseName}")
	private String dataBaseName = "zhifeng";//数据库名称
    //备份的数据
    @Value("${backup.static.folder}")
    private String folder;
    @Value("${backup.static.zipFile}")
    private String zipFile;

    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromUser;

	//备份数据库sql和网站数据
//	@Scheduled(cron = "0 0 3 ? * 6")//每个周六晚上3点自动备份数据库
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void backupSqlAndData(){
		log.info("开始备份数据库");
        String backUpSqlPath = dataBaseBackupWork();
        log.info("开始备份网站数据");
        String backupZipPath = dataZipBackupWork();
        log.info("备份结束");
        log.info("开始发送邮件");
        sendMail(backUpSqlPath, backupZipPath);
        log.info("邮件发送成功！");
    }

    //项目启动后运行
    @Async("taskExecutor")  //执行异步任务
    public void backupWorkByPostConstruct(){
        log.info("开始备份数据库");
        String backUpSqlPath = dataBaseBackupWork();
        log.info("开始备份网站数据");
        String backupZipPath = dataZipBackupWork();
        log.info("备份结束");
        log.info("开始发送邮件");
        sendMail(backUpSqlPath, backupZipPath);
        log.info("邮件发送成功！");
    }

    //1、备份SQL
    public String dataBaseBackupWork(){
        try {
            return dataBaseBackup(host, port, username, password, dataBaseName, new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String dataBaseBackup(String host, Integer port, String username, String password, String dataBaseName, String sqlname) throws Exception {
    	File file = new File(dataBackupPath);
        if (!file.exists()) {
            file.mkdir();
        }
        File dataFile = new File(file + File.separator + sqlname + ".sql");
        synchronized (SaveDataTask.class) {
            if (dataFile.exists()) {
                dataFile.delete();
            }
        }
        //拼接cmd命令
        //mysqldump -hlocalhost -P3306 -uroot -p123456 db > E:/back.sql
        StringBuffer s = new StringBuffer();
        s.append("cmd /c mysqldump -h").append(host).append(" -P")
        .append(port).append(" -u ").append(username).append(" -p")
        .append(password).append(" ").append(dataBaseName).append(" > ")
        .append(dataFile);
        Process exec = Runtime.getRuntime().exec(s.toString());
        if (exec.waitFor() == 0){
            log.info("数据库备份成功,备份路径为：{}",dataFile);
        }
        return dataFile.getAbsolutePath();//备份路径
    }

    //2、打包数据备份任务
    public String dataZipBackupWork(){
        try {
            return ZipUtils.packet(Paths.get(folder), Paths.get(zipFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //发送邮件任务
    private void sendMail(String backUpSqlPath, String backupZipPath) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //邮件发送者
            helper.setFrom(fromUser);
            //收件人
            helper.setTo(fromUser);
            //抄送人
            helper.setCc(fromUser);
            //邮件主题
            helper.setSubject("智蜂工作室数据备份，时间：" + new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date()));
            //邮件内容
            helper.setText("智蜂工作室数据备份：sql、网站数据，请注意及时备份噢。");
            helper.addAttachment("zhifeng.sql", new File(backUpSqlPath));
            helper.addAttachment("static.zip", new File(backupZipPath));
            //文件内容
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //测试
//    public static void main(String[] args) throws Exception {
//        String backName = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
//        dataBaseBackup(host, port, username, password, dataBaseName, backName);
//    }
}
