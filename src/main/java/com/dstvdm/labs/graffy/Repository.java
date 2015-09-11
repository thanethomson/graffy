package com.dstvdm.labs.graffy;

import com.dstvdm.labs.graffy.interfaces.DatabaseFactory;
import com.dstvdm.labs.graffy.interfaces.cache.CacheSystemFactory;
import com.dstvdm.labs.graffy.interfaces.logging.Logger;
import com.dstvdm.labs.graffy.interfaces.transactions.Tx;
import com.dstvdm.labs.graffy.interfaces.transactions.TxV;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

/**
 * The base Graffy repository library, from which you can derive all of your
 * repositories, if you're following the repository pattern in your coding.
 */
public abstract class Repository {
  
  protected final DatabaseFactory dbFactory;
  protected final Logger logger;
  protected final CacheSystemFactory cacheFactory;

  /**
   * Constructor.
   * @param config The Graffy configuration.
   */
  public Repository(GraffyConfig config) {
    this.dbFactory = config.getDatabaseFactory();
    // get a connection to the cache system
    this.cacheFactory = config.getCacheFactory();
    // provide logging for this class' namespace
    this.logger = config.getLoggerFactory().provide(this.getClass().getName());
  }
  
  /**
   * Retrieves a transactional instance of a graph database connection.
   */
  public OrientGraph getTx() {
    return dbFactory.getTx();
  }
  
  /**
   * Retrieves a non-transactional instance of a graph database connection.
   */
  public OrientGraphNoTx getNoTx() {
    return dbFactory.getNoTx();
  }
  
  
  /**
   * Executes a block of code within a transaction.
   * @param block The code block to execute.
   * @return The return value from the code execution.
   * @throws Throwable
   */
  public <T> T tx(Tx<T> block) throws Throwable {
    OrientGraph graph = getTx();
    Transaction transaction = new Transaction(graph, cacheFactory.provide());
    T result = null;
    
    try {
      result = block.execute(transaction);
    } catch (Throwable e) {
      // log the error
      logger.error("Unable to execute transaction", e);
      // re-throw the error
      throw e;
      
    } finally {
      graph.shutdown();
    }
    
    return result;
  }
  
  
  /**
   * Executes a block of code within a transaction, without requiring a return value.
   * @param block The code block to execute.
   * @throws Throwable
   */
  public void tx(TxV block) throws Throwable {
    OrientGraph graph = getTx();
    Transaction transaction = new Transaction(graph, cacheFactory.provide());
    
    try {
      block.execute(transaction);
    } catch (Throwable e) {
      // log the error
      logger.error("Unable to execute transaction", e);
      // re-throw the error
      throw e;
      
    } finally {
      graph.shutdown();
    }
  }

}
