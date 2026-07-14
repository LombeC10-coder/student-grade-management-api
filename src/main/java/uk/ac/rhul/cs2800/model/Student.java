package uk.ac.rhul.cs2800.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import uk.ac.rhul.cs2800.exception.NoGradeAvailableException;
import uk.ac.rhul.cs2800.exception.NoRegistrationException;

/**
 * Represents a student with personal information and a collection of grades for different modules.
 * This class also manages the modules the student is registered for and allows grade calculations.
 */
@Entity

public class Student {

  @Id
  @GeneratedValue
  private Long id;

  private String firstName;
  private String lastName;
  private String username;
  private String email;


  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Grade> grade = new ArrayList<>();

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Registration> registration = new ArrayList<>();

  /**
   * Constructs a new Student with the given details.
   *
   * @param firstName the first name of the student
   * @param lastName the last name of the student
   * @param username the username of the student
   * @param email the email address of the student
   */
  public Student(String firstName, String lastName, String username, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.grade = new ArrayList<>();
    this.registration = new ArrayList<>();
  }

  /**
   * Default constructor for creating an empty Student object.
   */
  public Student() {
    this.grade = new ArrayList<>();
    this.registration = new ArrayList<>();
  }

  /**
   * Adds a grade for the student.
   *
   * @param grade the grade to be added
   */
  public void addGrade(Grade grade) {
    this.grade.add(grade); // Add the grade to the list
    grade.setStudent(this); // Associate this student with the grade
  }

  /**
   * Computes the average score of the student's grades.
   *
   * @return the average score as a double
   * @throws NoGradeAvailableException if no grades are available
   */
  public double computeAverage() throws NoGradeAvailableException {
    if (grade.isEmpty()) {
      throw new NoGradeAvailableException("No grades available");
    }
    return grade.stream().mapToDouble(Grade::getScore).average().orElse(0.0);
  }

  /**
   * Retrieves all grades for the student.
   *
   * @return a list of grades
   */
  public List<Grade> getGrades() {
    return new ArrayList<>(grade); // Return a defensive copy of grades
  }

  /**
   * Retrieves the grade for a specific module.
   *
   * @param module the module to find the grade for
   * @return the grade for the given module, or null if not found
   */
  public Grade getGrade(Module module) {
    return grade.stream().filter(g -> g.getModule().equals(module)).findFirst().orElse(null);
  }

  /**
   * Registers a module for the student. Throws an exception if the module is already registered.
   *
   * @param module the module to register
   * @throws NoRegistrationException if the module is already registered
   */
  public void registerModule(Module module) throws NoRegistrationException {
    if (registration.stream().anyMatch(r -> r.getModule().equals(module))) {
      throw new NoRegistrationException("Module " + module.getCode() + " is already registered.");
    }
    registration.add(new Registration(this, module));
  }

  /**
   * Retrieves all registrations for the student.
   *
   * @return a list of registrations
   */
  public List<Registration> getRegistration() {
    return registration;
  }

  /**
   * Sets the list of registrations for the student.
   *
   * @param registration the list of registrations to set
   */
  public void setRegistration(List<Registration> registration) {
    this.registration = registration;
  }

  // Getters and Setters for other fields

  /**
   * Retrieves the first name of the student.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the student.
   *
   * @param firstName the first name to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Retrieves the last name of the student.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the student.
   *
   * @param lastName the last name to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Retrieves the username of the student.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of the student.
   *
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Retrieves the email address of the student.
   *
   * @return the email address
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the student.
   *
   * @param email the email address to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Retrieves the ID of the student.
   *
   * @return the student ID
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the ID of the student.
   *
   * @param id the student ID to set
   */
  public void setId(Long id) {
    this.id = id;
  }
}
