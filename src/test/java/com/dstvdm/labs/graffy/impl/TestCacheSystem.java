package com.dstvdm.labs.graffy.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.dstvdm.labs.graffy.interfaces.cache.CacheSystem;
import com.dstvdm.labs.graffy.interfaces.logging.Logger;
import com.dstvdm.labs.graffy.interfaces.logging.LoggerFactory;
import com.google.inject.Inject;

/**
 * A simple, in-memory cache system for our unit testing.
 */
public class TestCacheSystem implements CacheSystem {
  
  private final Map<String, Serializable> map = new HashMap<>();
  @Inject LoggerFactory loggerFactory;
  private Logger logger;
  
  public TestCacheSystem() {
    logger = loggerFactory.provide(TestCacheSystem.class.getName());
  }
  
  @Override
  public void put(String key, Serializable value) {
    map.put(key, value);
    logger.debug(String.format("Put value in cache at key: %s", key));
  }

  @Override
  public void put(String key, Serializable value, int expires) {
    map.put(key, value);
    logger.debug(String.format("Put value in cache at key: %s (expiry %d)", key, expires));
  }

  @Override
  public Serializable get(String key) {
    logger.debug(String.format("Getting value from cache with key: %s", key));
    return map.getOrDefault(key, null);
  }

  @Override
  public Serializable delete(String key) {
    Serializable val = get(key);
    
    if (map.containsKey(key))
      map.remove(key);
    
    logger.debug(String.format("Removed value from cache with key: %s", key));
    
    return val;
  }

}
