package com.lam.spring.initializrstart.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lam.spring.initializrstart.SpringUtil;
import com.lam.spring.initializrstart.job.WeatherDataSyncJob;

@Component
public class MyScheduler {


	public void schedulerJob() throws SchedulerException {
		ApplicationContext annotationContext = SpringUtil.getApplicationContext();
		StdScheduler stdScheduler = (StdScheduler) annotationContext.getBean("mySchedulerFactoryBean");//获得上面创建的bean
		Scheduler myScheduler =stdScheduler;
		startScheduler1(myScheduler);
		myScheduler.start();
	}

	public void startScheduler1(Scheduler scheduler) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("job1", "jobGroup1").build();
		SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule();
		SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1").startNow()
				.withSchedule(simpleSchedule.withIntervalInMinutes(29).repeatForever()).build();
		scheduler.scheduleJob(jobDetail, trigger);
	}
}