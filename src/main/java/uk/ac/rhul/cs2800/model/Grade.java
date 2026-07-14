package uk.ac.rhul.cs2800.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a grade assigned to a student for a specific module. This class encapsulates the
 * score, associated module, and student details.
 */
@Entity
public class Grade {

  @Id
  @GeneratedValue
  private Long id;

  private Integer score;

  @ManyToOne
  @JoinColumn(name = "student_id")
  @JsonBackReference
  private Student student;

  @ManyToOne
  @JoinColumn(name = "module_code")
  private Module module;

  /**
   * Default constructor for JPA.
   */
  public Grade() {}

  /**
   * Constructs a new Grade with the specified score and associated module.
   *
   * @param score the grade score
   * @param module the module associated with the grade
   */
  public Grade(Integer score, Module module) {
    this.score = score;
    this.module = module;
  }

  /**
   * Retrieves the score of the grade.
   *
   * @return the grade score as an {@code Integer}
   */
  public Integer getScore() {
    return score;
  }

  /**
   * Sets the score of the grade.
   *
   * @param score the score to set
   * @throws IllegalArgumentException if the score is null or negative
   */
  public void setScore(Integer score) {
    if (score == null || score < 0) {
      throw new IllegalArgumentException("Score must be a non-negative integer");
    }
    this.score = score;
  }

  /**
   * Retrieves the module associated with the grade.
   *
   * @return the associated {@code Module}
   */
  public Module getModule() {
    return module;
  }

  /**
   * Sets the module associated with the grade.
   *
   * @param module the module to set
   * @throws IllegalArgumentException if the module is null
   */
  public void setModule(Module module) {
    if (module == null) {
      throw new IllegalArgumentException("Module cannot be null");
    }
    this.module = module;
  }

  /**
   * Retrieves the student associated with the grade.
   *
   * @return the associated {@code Student}
   */
  public Student getStudent() {
    return student;
  }

  /**
   * Sets the student associated with the grade.
   *
   * @param student the student to set
   * @throws IllegalArgumentException if the student is null
   */
  public void setStudent(Student student) {
    if (student == null) {
      throw new IllegalArgumentException("Student cannot be null");
    }
    this.student = student;
  }

  /**
   * Retrieves the ID of the grade.
   *
   * @return the grade ID as a {@code Long}
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the ID of the grade.
   *
   * @param id the ID to set
   */
  public void setId(Long id) {
    this.id = id;
  }
}
