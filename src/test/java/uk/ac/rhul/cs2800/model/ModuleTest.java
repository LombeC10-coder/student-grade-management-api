package uk.ac.rhul.cs2800.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModuleTest {

  private Module module;

  @BeforeEach
  void setUp() {
    module = new Module("CS101", "Introduction to Programming", true); // Changed code to String
  }

  @Test
  void testConstructorAndGetters() {
    assertNotNull(module, "Module object should be created");
    assertEquals("CS101", module.getCode(), "Expected module code to be 'CS101'");
    assertEquals("Introduction to Programming", module.getName(),
        "Expected module name to be 'Introduction to Programming'");
    assertEquals(true, module.isMnc(), "Expected module to be mandatory");
  }

  @Test
  void testSetCode() {
    module.setCode("CS102");
    assertEquals("CS102", module.getCode(), "Expected module code to be updated to 'CS102'");
  }

  @Test
  void testSetName() {
    module.setName("Data Structures");
    assertEquals("Data Structures", module.getName(),
        "Expected module name to be updated to 'Data Structures'");
  }

  @Test
  void testSetMnc() {
    module.setMnc(false);
    assertEquals(false, module.isMnc(),
        "Expected module mandatory status to be updated to 'false'");
  }
}
