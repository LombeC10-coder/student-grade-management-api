package uk.ac.rhul.cs2800.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for the {@link Registration} class. This class tests the functionality of the
 * {@code Registration} class, including the proper initialization of fields and the behavior of
 * getter and setter methods.
 */
public class RegistrationTest {

  private Registration registration;
  private Student student;
  private Module module;

  /**
   * Sets up the test environment by initializing a {@code Student} and {@code Module} object and
   * creating a new {@code Registration} object.
   */
  @BeforeEach
  void setUp() {
    student = new Student("John", "Doe", "jdoe", "jdoe@example.com");
    module = new Module("CS101", "Introduction to Programming", true);
    registration = new Registration(student, module);
  }

  /**
   * Tests the constructor and getters to ensure proper initialization of a {@code Registration}
   * object.
   */
  @Test
  void testConstructorAndGetters() {
    assertNotNull(registration, "Registration object should be created");
    assertEquals(student, registration.getStudent(),
        "Expected student to match the one passed to the constructor");
    assertEquals(module, registration.getModule(),
        "Expected module to match the one passed to the constructor");
  }

  /**
   * Tests the {@code setStudent} method to ensure the student in the registration can be updated.
   */
  @Test
  void testSetStudent() {
    Student newStudent = new Student("Jane", "Smith", "jsmith", "jsmith@example.com");
    registration.setStudent(newStudent);
    assertEquals(newStudent, registration.getStudent(),
        "Expected student to be updated to newStudent");
  }

  /**
   * Tests the {@code setModule} method to ensure the module in the registration can be updated.
   */
  @Test
  void testSetModule() {
    Module newModule = new Module("CS102", "Data Structures", true);
    registration.setModule(newModule);
    assertEquals(newModule, registration.getModule(), "Expected module to be updated to newModule");
  }

  /**
   * Tests the {@code getId} and {@code setId} methods to ensure the ID in the registration can be
   * updated and retrieved.
   */
  @Test
  void testSetAndGetId() {
    registration.setId(1L);
    assertEquals(1L, registration.getId(), "Expected ID to be set to 1");
  }
}
