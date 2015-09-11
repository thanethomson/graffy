package com.dstvdm.labs.graffy.interfaces.cache;

import java.io.Serializable;

/**
 * Interface for cache systems.
 */
public interface CacheSystem {
  
  /**
   * Should attempt to put the given serializable value into the cache.
   * @param key The cache system key to use to identify the object.
   * @param value The value to put into the cache.
   */
  public void put(String key, Serializable value);
  
  /**
   * Should attempt to put the given serializable value into the cache, but the
   * value should expire after a certain predetermined time.
   * @param key The cache system key to use.
   * @param value The value to put into the cache.
   * @param expires The number of seconds after which the value should expire.
   */
  public void put(String key, Serializable value, int expires);
  
  /**
   * Should attempt to retrieve the value associated with the given key.
   * @param key The key for which to search.
   * @return On success, should return the deserialized object; on failure, null.
   */
  public Serializable get(String key);
  
  /**
   * Removes the cache system entry with the given key, but if the key existed,
   * it will return its value prior to deleting it.
   * @param key The key of the entry to delete.
   * @return The stored object, if found, or null if not found.
   */
  public Serializable delete(String key);

}
