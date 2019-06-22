package practice.quartztest;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

/**
 * Created by donglongcheng01 on 2017/11/13.
 */
public class CronTriggerTest {

    // 每秒钟
    public static final String CRON_EVERY_SECOND = "0/1 * * * * ?";

    // 每天十点
    public static final String CRON_EVERY_DAY = "0 0 10 * * ?";

    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = new JobDetail("job1", MyJob.class);
            CronTrigger cronTrigger = new CronTrigger("cronTrigger");
            cronTrigger.setCronExpression(CRON_EVERY_SECOND);
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
