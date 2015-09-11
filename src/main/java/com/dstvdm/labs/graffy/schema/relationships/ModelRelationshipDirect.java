package com.dstvdm.labs.graffy.schema.relationships;

import com.dstvdm.labs.graffy.BaseModel;
import com.dstvdm.labs.graffy.Transaction;
import com.dstvdm.labs.graffy.interfaces.schema.ModelRelationship;
import com.tinkerpop.blueprints.Vertex;

/**
 * A direct, bidirectional relationship between two model instances.
 */
public class ModelRelationshipDirect<
    A extends BaseModel,
    B extends BaseModel> implements ModelRelationship {
  
  private final Class<A> srcModel;
  private final String srcField;
  private final Class<B> destModel;
  private final String reverseField;
  private final Transaction tx;
  private Vertex srcVertex = null, destVertex = null;

  /**
   * Constructor for when both source and destination vertices are known.
   * @param srcVertex The source vertex.
   * @param srcModel The source model.
   * @param srcField The source field name.
   * @param destVertex The destination vertex.
   * @param destModel The destination model.
   * @param reverseField The reverse connection field from the destination model.
   * @param tx An instance of a graph transaction.
   */
  public ModelRelationshipDirect(
      Vertex srcVertex,
      Class<A> srcModel,
      String srcField,
      Vertex destVertex,
      Class<B> destModel,
      String reverseField,
      Transaction tx) {
    this.srcVertex = srcVertex;
    this.srcModel = srcModel;
    this.srcField = srcField;
    
    this.destVertex = destVertex;
    this.destModel = destModel;
    this.reverseField = reverseField;
    
    this.tx = tx;
  }
  
  
  /**
   * Constructor for when the destination vertex is unknown.
   * @param srcVertex
   * @param srcModel
   * @param srcField
   * @param destModel
   * @param reverseField
   * @param tx
   */
  public ModelRelationshipDirect(
      Vertex srcVertex,
      Class<A> srcModel,
      String srcField,
      Class<B> destModel,
      String reverseField,
      Transaction tx) {
    this.srcVertex = srcVertex;
    this.srcModel = srcModel;
    this.srcField = srcField;
    
    this.destModel = destModel;
    this.reverseField = reverseField;
    
    this.tx = tx;
  }

  
  @Override
  public ModelRelationship load() throws Throwable {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void connect() throws Throwable {
    // TODO Auto-generated method stub

  }

}
