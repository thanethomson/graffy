package com.dstvdm.labs.graffy.interfaces;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

/**
 * The graph database factory, which you must declare in order to expose
 */
public interface DatabaseFactory {

  /**
   * Must return a transactional instance of a connection to the graph database.
   */
  public OrientGraph getTx();
  
  /**
   * Must return a non-transactional instance of a connection to the graph database.
   */
  public OrientGraphNoTx getNoTx();
  
}
