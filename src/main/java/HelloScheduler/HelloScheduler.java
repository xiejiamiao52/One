package HelloScheduler;

import Quartz.Hellojob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;



public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Crontigger();

    }
    //间隔时间触发
    public static void Simpletrigger() throws SchedulerException {
        //调度器
        Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
        //定义一个Trigger，触发条件
        TriggerBuilder triggerBuilder=TriggerBuilder.newTrigger();
        //创建触发器，trigger
        Trigger trigger=triggerBuilder.withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever())
                .build();
        //创建JObDetail,jobBuilder
        JobDetail jobDetail=JobBuilder.newJob(Hellojob.class).withIdentity("job04","group04")
                .usingJobData("data04","hello  word").build();

        //注册 Jobdetail 和 Trigger
        scheduler.scheduleJob(jobDetail,trigger);
        //启动调度器
        scheduler.start();
    }

    //每隔多长时间触发
    public static  void Calendartrigger() throws SchedulerException {
        //调度器
        Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
        //定义一个Trigger，触发条件
        TriggerBuilder triggerBuilder=TriggerBuilder.newTrigger();
        //创建触发器，trigger
        Trigger trigger=triggerBuilder.withIdentity("trigger1","group1")
                .startNow()
                //.withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInDays(3))每3天
               //.withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInYears(1))每1年
               // .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInMonths(3))每3个月
                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(1))
                .build();
        //创建JObDetail,jobBuilder
        JobDetail jobDetail=JobBuilder.newJob(Hellojob.class).withIdentity("job04","group04")
                .usingJobData("data04","hello  word").build();

        //注册 Jobdetail 和 Trigger
        scheduler.scheduleJob(jobDetail,trigger);
        //启动调度器
        scheduler.start();
    }

    //每天按某个时间段内执行
    public static  void dailytime() throws SchedulerException, InterruptedException {
        //调度器
        Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
        //定义一个Trigger，触发条件
        TriggerBuilder triggerBuilder=TriggerBuilder.newTrigger();
        //创建触发器，trigger
        Trigger trigger=triggerBuilder.withIdentity("trigger1","group1")
                .startNow()

                .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                        .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9,0))//每天九点开始
                        .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(20,0))//每天8点结束
                        .onDaysOfTheWeek(2,4,6)
                        .withIntervalInMinutes(1)
                      //  .withRepeatCount(10)
                )
                .build();
        //创建JObDetail,jobBuilder
        JobDetail jobDetail=JobBuilder.newJob(Hellojob.class).withIdentity("job04","group04")
                .usingJobData("data04","hello  word").build();

        //注册 Jobdetail 和 Trigger
        scheduler.scheduleJob(jobDetail,trigger);
        //启动调度器
        scheduler.start();

        //关闭调度器
        Thread.sleep(1000);
        scheduler.shutdown();
    }

    //按照设定时间执行
    public static void Crontigger() throws SchedulerException {
        //调度器
        Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
        //定义一个Trigger，触发条件
        TriggerBuilder triggerBuilder=TriggerBuilder.newTrigger();
        //创建触发器，trigger
        Trigger trigger=triggerBuilder.withIdentity("trigger1","group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();
        //创建JObDetail,jobBuilder
        JobDetail jobDetail=JobBuilder.newJob(Hellojob.class).withIdentity("job04","group04")
                .usingJobData("data04","hello  word").build();

        //注册 Jobdetail 和 Trigger
        scheduler.scheduleJob(jobDetail,trigger);
        //启动调度器
        scheduler.start();
    }

}
