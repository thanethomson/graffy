package com.dstvdm.labs.graffy.modules;

import com.dstvdm.labs.graffy.impl.TestCacheSystemFactory;
import com.dstvdm.labs.graffy.impl.TestDatabaseFactory;
import com.dstvdm.labs.graffy.impl.TestLoggerFactory;
import com.dstvdm.labs.graffy.interfaces.DatabaseFactory;
import com.dstvdm.labs.graffy.interfaces.cache.CacheSystemFactory;
import com.dstvdm.labs.graffy.interfaces.logging.LoggerFactory;
import com.google.inject.AbstractModule;

/**
 * Our dependency injection configuration for unit testing. 
 */
public class TestModule extends AbstractModule {
  
  @Override
  protected void configure() {
    bind(DatabaseFactory.class).to(TestDatabaseFactory.class);
    bind(CacheSystemFactory.class).to(TestCacheSystemFactory.class);
    bind(LoggerFactory.class).to(TestLoggerFactory.class);
  }

}
