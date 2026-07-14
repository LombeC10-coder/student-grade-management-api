package uk.ac.rhul.cs2800.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a university module with a unique code, name, and mandatory status. This class is used
 * to associate grades and registrations with students.
 */
@Entity
public class Module {

  @Id
  private String code; // Changed from Integer to String
  private String name;
  private boolean mnc;

  @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Grade> grades = new ArrayList<>();

  /**
   * Default constructor for JPA.
   */
  public Module() {}

  /**
   * Constructs a new Module with the specified details.
   *
   * @param code the unique code of the module
   * @param name the name of the module
   * @param mnc a boolean indicating whether the module is mandatory
   */
  public Module(String code, String name, boolean mnc) {
    this.code = code;
    this.name = name;
    this.mnc = mnc;
  }

  /**
   * Gets the unique code of the module.
   *
   * @return the module's code as a string
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the unique code of the module.
   *
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Gets the name of the module.
   *
   * @return the module's name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the module.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Checks if the module is mandatory.
   *
   * @return {@code true} if the module is mandatory, otherwise {@code false}
   */
  public boolean isMnc() {
    return mnc;
  }

  /**
   * Sets the mandatory status of the module.
   *
   * @param mnc {@code true} if the module is mandatory, otherwise {@code false}
   */
  public void setMnc(boolean mnc) {
    this.mnc = mnc;
  }
}
