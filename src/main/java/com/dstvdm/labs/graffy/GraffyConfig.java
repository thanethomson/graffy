package com.dstvdm.labs.graffy;

import com.dstvdm.labs.graffy.interfaces.DatabaseFactory;
import com.dstvdm.labs.graffy.interfaces.cache.CacheSystemFactory;
import com.dstvdm.labs.graffy.interfaces.logging.LoggerFactory;
import com.dstvdm.labs.graffy.schema.DatabaseSchema;
import com.google.inject.Inject;

/**
 * The primary mechanism by which Graffy config is passed into the relevant
 * Graffy classes.
 */
public class GraffyConfig {
  
  private final DatabaseFactory databaseFactory;
  private final LoggerFactory loggerFactory;
  private final CacheSystemFactory cacheFactory;
  private final DatabaseSchema databaseSchema;

  @Inject
  public GraffyConfig(DatabaseFactory databaseFactory,
      CacheSystemFactory cacheFactory,
      LoggerFactory loggerFactory) {
    this.databaseFactory = databaseFactory;
    this.cacheFactory = cacheFactory;
    this.loggerFactory = loggerFactory;
    
    // build up our database schema
    this.databaseSchema = databaseFactory.buildSchema();
  }
  
  /**
   * Getter for the database factory.
   */
  public DatabaseFactory getDatabaseFactory() {
    return databaseFactory;
  }
  
  
  /**
   * Getter for the cache system factory.
   */
  public CacheSystemFactory getCacheFactory() {
    return cacheFactory;
  }
  
  /**
   * Getter for the logger factory.
   */
  public LoggerFactory getLoggerFactory() {
    return loggerFactory;
  }
  
  
  /**
   * Getter for the database schema.
   */
  public DatabaseSchema getDatabaseSchema() {
    return databaseSchema;
  }

}
