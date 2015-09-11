package com.dstvdm.labs.graffy.impl;

import com.dstvdm.labs.graffy.interfaces.logging.Logger;
import com.dstvdm.labs.graffy.interfaces.logging.LoggerFactory;

import ch.qos.logback.classic.Level;

/**
 * Our basic test logging for unit testing.
 */
public class TestLoggerFactory implements LoggerFactory {
  
  // our own internal little logger
  private ch.qos.logback.classic.Logger logger;
  
  public TestLoggerFactory() {
    logger = (ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger(TestLoggerFactory.class);
    logger.setLevel(Level.DEBUG);
  }

  @Override
  public Logger provide(String namespace) {
    logger.debug(String.format("Providing new logger for namespace: %s", namespace));
    ch.qos.logback.classic.Logger logbackLogger = (ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger(namespace);
    logbackLogger.setLevel(Level.DEBUG);
    return new LogbackLogger(logbackLogger);
  }

}
