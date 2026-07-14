package uk.ac.rhul.cs2800.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GradeTest {

  private Grade grade;
  private Module module;
  private Student student;

  @BeforeEach
  void setUp() {
    module = new Module("CS101", "Introduction to Programming", true);
    student = new Student("John", "Doe", "jdoe", "jdoe@example.com");
    grade = new Grade(85, module);
  }

  @Test
  void testConstructorAndGetters() {
    assertNotNull(grade, "Grade object should be created");
    assertEquals(85, grade.getScore(), "Expected grade score to be 85");
    assertNotNull(grade.getModule(), "Expected module to be associated with the grade");
    assertEquals(module, grade.getModule(),
        "Expected module to match the one associated with the grade");
  }

  @Test
  void testGetModule() {
    assertEquals(module, grade.getModule(), "Expected module to match the one used in the grade");
  }

  @Test
  void testSetScore() {
    grade.setScore(90);
    assertEquals(90, grade.getScore(), "Expected grade score to be updated to 90");
  }

  @Test
  void testSetScoreWithNull() {
    assertThrows(IllegalArgumentException.class, () -> grade.setScore(null),
        "Expected IllegalArgumentException when score is null");
  }

  @Test
  void testSetScoreWithNegativeValue() {
    assertThrows(IllegalArgumentException.class, () -> grade.setScore(-10),
        "Expected IllegalArgumentException when score is negative");
  }

  @Test
  void testSetModule() {
    Module newModule = new Module("CS102", "Data Structures", false);
    grade.setModule(newModule);
    assertEquals(newModule, grade.getModule(), "Expected module to be updated");
  }

  @Test
  void testSetModuleWithNull() {
    assertThrows(IllegalArgumentException.class, () -> grade.setModule(null),
        "Expected IllegalArgumentException when module is null");
  }

  @Test
  void testSetStudent() {
    grade.setStudent(student);
    assertSame(student, grade.getStudent(), "Expected student to match the one set");
  }

  @Test
  void testSetStudentWithNull() {
    assertThrows(IllegalArgumentException.class, () -> grade.setStudent(null),
        "Expected IllegalArgumentException when student is null");
  }

  @Test
  void testGetId() {
    grade.setId(1L);
    assertEquals(1L, grade.getId(), "Expected ID to be set to 1");
  }

  @Test
  void testSetId() {
    grade.setId(2L);
    assertEquals(2L, grade.getId(), "Expected ID to be set to 2");
  }
}
