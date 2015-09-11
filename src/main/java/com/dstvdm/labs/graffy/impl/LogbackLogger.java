package com.dstvdm.labs.graffy.impl;

import com.dstvdm.labs.graffy.interfaces.logging.Logger;

/**
 * Logger that interfaces with a Logback logger instance.
 */
public class LogbackLogger implements Logger {
  
  private final ch.qos.logback.classic.Logger logger;

  public LogbackLogger(ch.qos.logback.classic.Logger logger) {
    this.logger = logger;
  }

  @Override
  public void debug(String msg) {
    logger.debug(msg);
  }

  @Override
  public void info(String msg) {
    logger.info(msg);
  }

  @Override
  public void warn(String msg) {
    logger.warn(msg);
  }

  @Override
  public void error(String msg) {
    logger.error(msg);
  }

  @Override
  public void error(String msg, Throwable e) {
    logger.error(msg, e);
  }

}
