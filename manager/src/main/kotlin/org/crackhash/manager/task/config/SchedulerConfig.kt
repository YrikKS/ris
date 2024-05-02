package org.crackhash.manager.task.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableAsync
@EnableScheduling
class SchedulerConfig {

//    @Bean
//    fun jobDetail(): JobDetail =
//        JobBuilder.newJob().ofType(ResendCreatedTaskEventService::class.java)
//            .storeDurably()
//            .withIdentity("resend")
//            .withDescription("Resend events")
//            .build()
//
//    @Bean
//    fun scheduler(trigger: Trigger, job: JobDetail, factory: SchedulerFactoryBean): Scheduler {
//        val scheduler = factory.scheduler
//        scheduler.scheduleJob(job, trigger)
//        scheduler.start()
//        scheduler.
//    }
}