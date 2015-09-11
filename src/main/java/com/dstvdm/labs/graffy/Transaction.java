package com.dstvdm.labs.graffy;

import com.dstvdm.labs.graffy.interfaces.cache.CacheSystem;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

/**
 * A single abstracted transaction for when interacting with the graph database.
 */
public class Transaction {
  
  private final OrientGraph graph;
  private final CacheSystem cache;

  /**
   * Constructor.
   * @param graph A transactional connection to the graph database.
   */
  public Transaction(OrientGraph graph, CacheSystem cache) {
    this.graph = graph;
    this.cache = cache;
  }
  
  /**
   * Getter to retrieve the raw graph database connection.
   */
  public OrientGraph getGraph() {
    return graph;
  }
  
  /**
   * Getter to retrieve the cache system connection.
   */
  public CacheSystem getCache() {
    return cache;
  }

}
