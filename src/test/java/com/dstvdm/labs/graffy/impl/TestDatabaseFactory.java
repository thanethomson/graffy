package com.dstvdm.labs.graffy.impl;

import com.dstvdm.labs.graffy.interfaces.DatabaseFactory;
import com.dstvdm.labs.graffy.interfaces.logging.LoggerFactory;
import com.dstvdm.labs.graffy.schema.DatabaseSchema;
import com.google.inject.Inject;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

/**
 * Database factory for testing purposes.
 */
public class TestDatabaseFactory implements DatabaseFactory {
  
  private final OrientGraphFactory factory;
  @Inject LoggerFactory loggerFactory;

  public TestDatabaseFactory() {
    factory = new OrientGraphFactory("remote:localhost/GraffyTest").setupPool(1, 10);
  }

  @Override
  public DatabaseSchema buildSchema() {
    return new DatabaseSchema("com.dstvdm.labs.graffy.models", loggerFactory.provide(DatabaseSchema.class.getName()));
  }

  @Override
  public OrientGraph getTx() {
    return factory.getTx();
  }

  @Override
  public OrientGraphNoTx getNoTx() {
    return factory.getNoTx();
  }

}
