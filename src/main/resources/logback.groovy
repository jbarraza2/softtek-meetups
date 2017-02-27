import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO

appender("ROLLING", RollingFileAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{5} Groovy - %msg%n"
  }
  rollingPolicy(TimeBasedRollingPolicy){
    FileNamePattern = "softtek-meetups-%d{yyyy-MM}.log"
  }
}

logger("com.softtek.meetup", INFO)
logger('org.springframework', WARN)
root(WARN, ["ROLLING"])