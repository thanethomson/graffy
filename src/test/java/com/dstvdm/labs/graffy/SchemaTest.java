package com.dstvdm.labs.graffy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dstvdm.labs.graffy.models.User;
import com.dstvdm.labs.graffy.modules.TestModule;
import com.dstvdm.labs.graffy.schema.DatabaseSchema;
import com.dstvdm.labs.graffy.schema.ModelFieldMeta;
import com.dstvdm.labs.graffy.schema.ModelFieldType;
import com.dstvdm.labs.graffy.schema.ModelSchema;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.orientechnologies.orient.core.metadata.schema.OType;

/**
 * Tests our database schema generation.
 */
public class SchemaTest {
  
  private Injector injector;

  @Before
  public void setUp() throws Exception {
    injector = Guice.createInjector(new TestModule());
  }

  @Test
  public void testSchemaBuilding() {
    GraffyConfig cfg = injector.getInstance(GraffyConfig.class);
    DatabaseSchema schema = cfg.getDatabaseSchema();
    ModelSchema userSchema = schema.get(User.class);
    ModelFieldMeta fieldMeta;
    
    fieldMeta = userSchema.getFieldMeta("firstName");
    assertEquals(ModelFieldType.VALUE, fieldMeta.getType());
    assertEquals(OType.STRING, fieldMeta.getOrientType());
    
    fieldMeta = userSchema.getFieldMeta("lastName");
    assertEquals(ModelFieldType.VALUE, fieldMeta.getType());
    assertEquals(OType.STRING, fieldMeta.getOrientType());
    
    fieldMeta = userSchema.getFieldMeta("email");
    assertEquals(ModelFieldType.VALUE, fieldMeta.getType());
    assertEquals(OType.STRING, fieldMeta.getOrientType());
  }

}
