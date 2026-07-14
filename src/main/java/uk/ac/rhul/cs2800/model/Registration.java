package uk.ac.rhul.cs2800.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a registration record for a student enrolled in a module. This class maps the
 * relationship between students and modules.
 */
@Entity
public class Registration {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "module_code")
  private Module module;

  /**
   * Default constructor for JPA.
   */
  public Registration() {}

  /**
   * Constructs a new Registration linking a student to a module.
   *
   * @param student the student registering for the module
   * @param module the module being registered
   */
  public Registration(Student student, Module module) {
    this.student = student;
    this.module = module;
  }

  // Getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Module getModule() {
    return module;
  }

  public void setModule(Module module) {
    this.module = module;
  }
}
