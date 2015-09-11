package com.dstvdm.labs.graffy.interfaces.cache;

/**
 * Interface for cache system providers.
 */
public interface CacheSystemFactory {
  
  /**
   * Should provide an interface to the cache system that can be used within
   * a single thread.
   */
  public CacheSystem provide();

}
