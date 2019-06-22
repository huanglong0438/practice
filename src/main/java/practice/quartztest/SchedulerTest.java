package practice.quartztest;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by donglongcheng01 on 2017/11/13.
 */
public class SchedulerTest {
    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = new JobDetail("job1", MyJob.class);
            SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger");
            simpleTrigger.setStartTime(new Date());
            simpleTrigger.setRepeatInterval(1000);
            simpleTrigger.setRepeatCount(8);
            scheduler.scheduleJob(jobDetail, simpleTrigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
