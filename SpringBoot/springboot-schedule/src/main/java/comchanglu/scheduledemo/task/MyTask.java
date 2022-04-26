package comchanglu.scheduledemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyTask
 * @Author ChangLu
 * @Date 4/9/2022 2:06 PM
 * @Description 自定义任务
 */
@Component
@Slf4j
public class MyTask {

    //这里定时设置1秒执行一次
//    @Scheduled(fixedDelay = 1 * 1000)  //毫秒为单位
    @Scheduled(cron = "0/1 * * * * ?")  //从0秒开始每秒执行一次
    public void test(){
        log.info("hello,changlu!");
    }

}
