package Quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;
@DisallowConcurrentExecution
public class Hellojob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JobDetail jobDetail=jobExecutionContext.getJobDetail();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String time=simpleDateFormat.format(date);
        String  job= jobDetail.getJobDataMap().getString("data04");
        System.out.println("时间是:"+new Date()+job);
    }
}
