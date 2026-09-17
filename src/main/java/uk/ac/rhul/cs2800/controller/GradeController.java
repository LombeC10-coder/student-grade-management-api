package uk.ac.rhul.cs2800.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.rhul.cs2800.model.Grade;
import uk.ac.rhul.cs2800.model.Module;
import uk.ac.rhul.cs2800.model.Student;
import uk.ac.rhul.cs2800.repository.GradeRepository;
import uk.ac.rhul.cs2800.repository.ModuleRepository;
import uk.ac.rhul.cs2800.repository.StudentRepository;

/**
 * Controller for managing grades. It handles HTTP POST requests for adding grades to students in
 * specific modules.
 */
@RestController
public class GradeController {

  private final GradeRepository gradeRepository;

  private final StudentRepository studentRepository;

  private final ModuleRepository moduleRepository;

  /**
   * Constructs a GradeController with the provided repositories for grade, student, and module.
   *
   * @param gradeRepository the repository for managing grades
   * @param studentRepository the repository for managing students
   * @param moduleRepository the repository for managing modules
   */
  public GradeController(GradeRepository gradeRepository, StudentRepository studentRepository,
      ModuleRepository moduleRepository) {
    this.gradeRepository = gradeRepository;
    this.moduleRepository = moduleRepository;
    this.studentRepository = studentRepository;
  }

  /**
   * Adds a grade to a student for a specific module. This method expects a JSON payload with the
   * student ID, module code, and grade score. It fetches the student and module from the
   * repositories and creates a new grade entry.
   *
   * @param params a map containing the student ID, module code, and grade score
   * @return a ResponseEntity containing the saved Grade object, or an error if the student or
   *         module is not found
   */
  @PostMapping(value = "/grades/addGrade")
  public ResponseEntity<Grade> addGrade(@RequestBody Map<String, String> params) {
    Long studentId = parseLongParameter(params, "student_id");
    String moduleCode = requireParameter(params, "module_code");
    Integer score = parseIntegerParameter(params, "score");

    if (score < 0 || score > 100) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Score must be between 0 and 100");
    }

    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Student not found with ID: " + studentId));

    Module module = moduleRepository.findById(moduleCode)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Module not found with code: " + moduleCode));

    Grade grade = new Grade();
    grade.setScore(score);
    grade.setStudent(student);
    grade.setModule(module);

    Grade savedGrade = gradeRepository.save(grade);
    return ResponseEntity.ok(savedGrade);
  }

  private String requireParameter(Map<String, String> params, String name) {
    String value = params.get(name);
    if (value == null || value.isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Missing required parameter: " + name);
    }
    return value;
  }

  private Long parseLongParameter(Map<String, String> params, String name) {
    try {
      return Long.valueOf(requireParameter(params, name));
    } catch (NumberFormatException exception) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Parameter must be a whole number: " + name, exception);
    }
  }

  private Integer parseIntegerParameter(Map<String, String> params, String name) {
    try {
      return Integer.valueOf(requireParameter(params, name));
    } catch (NumberFormatException exception) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Parameter must be a whole number: " + name, exception);
    }
  }

}
