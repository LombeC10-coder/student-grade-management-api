package uk.ac.rhul.cs2800.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.rhul.cs2800.model.Student;
import uk.ac.rhul.cs2800.model.Module;
import uk.ac.rhul.cs2800.model.Grade;
import uk.ac.rhul.cs2800.repository.ModuleRepository;
import uk.ac.rhul.cs2800.repository.StudentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class GradeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private StudentRepository studentRepository;
  private Student student;

  @Autowired
  private ModuleRepository moduleRepository; // Add ModuleRepository
  // private Module module;



  @BeforeEach
  void beforeEach() {
    // Save a student to the repository
    student = new Student("John", "Doe", "jdoe", "jdoe@example.com");
    student = studentRepository.save(student);

    // Save a module to the repository
    Module module = new Module("CS102", "Data Structures", true);
    module = moduleRepository.save(module);
  }

  @Test
  void addGradeTest() throws JsonProcessingException, Exception {
    Map<String, String> params = new HashMap<>();
    params.put("student_id", student.getId().toString());
    params.put("module_code", "CS102");
    params.put("score", "85");


    MvcResult action = mockMvc
        .perform(MockMvcRequestBuilders.post("/grades/addGrade")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(params)).accept(MediaType.APPLICATION_JSON))
        .andReturn();

    assertEquals(HttpStatus.OK.value(), action.getResponse().getStatus());



    // Deserialize the response into a Grade object
    Grade grade = objectMapper.readValue(action.getResponse().getContentAsString(), Grade.class);

    // Validate the Grade object
    assertNotNull(grade.getId(), "Grade ID should not be null");
    assertEquals(85, grade.getScore(), "Grade score should match the input");

    // Validate the associated Student object
    // assertNotNull(grade.getStudent(), "Grade should be associated with a Student");
    // assertEquals(student.getId(), grade.getStudent().getId(), "Student ID should match the
    // input");

    // Validate the associated Module object
    assertNotNull(grade.getModule(), "Grade should be associated with a Module");
    assertEquals("CS102", grade.getModule().getCode(), "Module code should match the input");
  }
}
