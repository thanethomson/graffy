package com.dstvdm.labs.graffy.impl;

import com.dstvdm.labs.graffy.interfaces.cache.CacheSystem;
import com.dstvdm.labs.graffy.interfaces.cache.CacheSystemFactory;

/**
 * A simple, in-memory cache system for unit testing purposes only.
 */
public class TestCacheSystemFactory implements CacheSystemFactory {

  @Override
  public CacheSystem provide() {
    return new TestCacheSystem();
  }

}
