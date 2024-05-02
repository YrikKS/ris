package org.crackhash.worker.util.logger

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {

    private val log = LoggerFactory.getLogger(LoggingAspect::class.java)

    @Pointcut("@annotation(org.crackhash.worker.util.logger.LogBefore)")
    fun logBeforePointcut() {}

    @Pointcut("@annotation(org.crackhash.worker.util.logger.LogAfter)")
    fun logAfterPointcut() {}

    @Before("logBeforePointcut()")
    fun loggingBefore(jp: JoinPoint): Unit =
        run {
            log.info(
                """
                    Before: 
                    class=${jp.`this`.javaClass}, 
                    method=${jp.signature.name}, 
                    args=${jp.args.map { it.toString() }}
                """
            )
        }

    @After("logAfterPointcut()")
    fun loggingAfter(jp: JoinPoint): Unit =
        run {
            log.info(
                """
                    After: 
                    class=${jp.`this`.javaClass}, 
                    method=${jp.signature.name}, 
                    args=${jp.args.map { it.toString() }}
                """
            )
        }
}