package uk.ac.rhul.cs2800.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.rhul.cs2800.exception.NoGradeAvailableException;
import uk.ac.rhul.cs2800.exception.NoRegistrationException;

/**
 * This class tests the functionality of the {@link Student} class. It ensures that methods within
 * the Student class, such as adding grades, computing averages, and registering modules, work as
 * expected.
 */
public class StudentTest {

  private Student student;
  private Module module1;
  private Module module2;

  /**
   * Sets up common test data before each test. Initializes a new Student and two Modules to be used
   * in each test case.
   */
  @BeforeEach
  void setUp() {
    student = new Student("John", "Doe", "jdoe", "jdoe@example.com");
    module1 = new Module("CS101", "Programming Lab", true);
    module2 = new Module("CS102", "Data Structures", true);
  }

  @Test
  void testComputeAverage() throws NoGradeAvailableException {
    // Add grades to the student
    student.addGrade(new Grade(50, module1));
    student.addGrade(new Grade(30, module2));

    // Test the average
    assertEquals(40.0, student.computeAverage(), "The average grade should be 40.0");
  }

  @Test
  void testComputeAverageWithNoGrades() {
    assertThrows(NoGradeAvailableException.class, student::computeAverage,
        "Expected NoGradeAvailableException when no grades are available");
  }

  @Test
  void testAddGrade() {
    Grade grade = new Grade(85, module1);
    student.addGrade(grade);

    assertEquals(1, student.getGrades().size(), "Expected one grade in the list");
    assertEquals(85, student.getGrades().get(0).getScore(), "Expected grade score to match input");
  }

  @Test
  void testGetGrade() {
    Grade grade = new Grade(85, module1);
    student.addGrade(grade);

    Grade retrievedGrade = student.getGrade(module1);

    // Assert the retrieved grade matches the expected grade score
    assertEquals(85, retrievedGrade.getScore(), "Expected grade score to match input");
  }

  @Test
  void testGetGradeForUnregisteredModule() {
    Grade retrievedGrade = student.getGrade(module1);
    assertEquals(null, retrievedGrade, "Expected no grade for unregistered module");
  }

  @Test
  void testRegisterModuleSuccess() throws NoRegistrationException {
    // Register the first module
    student.registerModule(module1);

    // Check if the registration list contains the module
    List<Registration> registrations = student.getRegistration();
    assertEquals(1, registrations.size(), "Expected one registration in the list");
    assertEquals(module1, registrations.get(0).getModule(), "Expected module to match module1");

    // Register the second module
    student.registerModule(module2);

    // Check if the second registration is correctly added
    registrations = student.getRegistration();
    assertEquals(2, registrations.size(), "Expected two registrations in the list");
    assertEquals(module2, registrations.get(1).getModule(), "Expected module to match module2");
  }

  @Test
  void testRegisterModuleDuplicate() {
    assertThrows(NoRegistrationException.class, () -> {
      student.registerModule(module1);
      student.registerModule(module1); // Registering the same module again
    }, "Expected NoRegistrationException when registering a duplicate module");
  }

  @Test
  void testSetRegistration() {
    Registration registration = new Registration(student, module1);
    student.setRegistration(List.of(registration));

    assertEquals(1, student.getRegistration().size(), "Expected registration size to be 1");
    assertEquals(module1, student.getRegistration().get(0).getModule(),
        "Expected module to match the one registered");
  }

  @Test
  void testGetFirstName() {
    assertEquals("John", student.getFirstName(), "Expected first name to be 'John'");
  }

  @Test
  void testSetFirstName() {
    student.setFirstName("Jane");
    assertEquals("Jane", student.getFirstName(), "Expected first name to be updated to 'Jane'");
  }

  @Test
  void testGetLastName() {
    assertEquals("Doe", student.getLastName(), "Expected last name to be 'Doe'");
  }

  @Test
  void testSetLastName() {
    student.setLastName("Smith");
    assertEquals("Smith", student.getLastName(), "Expected last name to be updated to 'Smith'");
  }

  @Test
  void testGetUsername() {
    assertEquals("jdoe", student.getUsername(), "Expected username to be 'jdoe'");
  }

  @Test
  void testSetUsername() {
    student.setUsername("jsmith");
    assertEquals("jsmith", student.getUsername(), "Expected username to be updated to 'jsmith'");
  }

  @Test
  void testGetEmail() {
    assertEquals("jdoe@example.com", student.getEmail(), "Expected email to be 'jdoe@example.com'");
  }

  @Test
  void testSetEmail() {
    student.setEmail("jsmith@example.com");
    assertEquals("jsmith@example.com", student.getEmail(),
        "Expected email to be updated to 'jsmith@example.com'");
  }

  @Test
  void testDefaultConstructor() {
    Student defaultStudent = new Student();

    // Verify that the grade and registration lists are initialized
    assertNotNull(defaultStudent.getGrades(), "Grades collection should be initialized");
    assertNotNull(defaultStudent.getRegistration(), "Registration list should be initialized");

    // Verify that both lists are empty upon initialization
    assertTrue(defaultStudent.getGrades().isEmpty(), () -> "Grades collection should be empty");
    assertTrue(defaultStudent.getRegistration().isEmpty(),
        () -> "Registration list should be empty");
  }

  @Test
  void testSetId() {
    student.setId(123L);
    assertEquals(123L, student.getId(), "Expected ID to be set to 123");
  }

  @Test
  void testGetId() {
    student.setId(456L);
    assertEquals(456L, student.getId(), "Expected ID to be retrieved as 456");
  }
}
