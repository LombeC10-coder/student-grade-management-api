package uk.ac.rhul.cs2800.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import uk.ac.rhul.cs2800.model.Grade;
import uk.ac.rhul.cs2800.model.Module;
import uk.ac.rhul.cs2800.model.Student;
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
  private ModuleRepository moduleRepository;

  @BeforeEach
  void beforeEach() {
    student = new Student("John", "Doe", "jdoe", "jdoe@example.com");
    student = studentRepository.save(student);

    Module module = new Module("CS102", "Data Structures", true);
    moduleRepository.save(module);
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

    Grade grade = objectMapper.readValue(action.getResponse().getContentAsString(), Grade.class);

    assertNotNull(grade.getId(), "Grade ID should not be null");
    assertEquals(85, grade.getScore(), "Grade score should match the input");
    assertNotNull(grade.getModule(), "Grade should be associated with a Module");
    assertEquals("CS102", grade.getModule().getCode(), "Module code should match the input");
  }

  @Test
  void addGradeReturnsNotFoundForUnknownStudent() throws Exception {
    Map<String, String> params = validGradeRequest();
    params.put("student_id", "999999");

    mockMvc.perform(MockMvcRequestBuilders.post("/grades/addGrade")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(params)))
        .andExpect(status().isNotFound());
  }

  @Test
  void addGradeReturnsNotFoundForUnknownModule() throws Exception {
    Map<String, String> params = validGradeRequest();
    params.put("module_code", "UNKNOWN");

    mockMvc.perform(MockMvcRequestBuilders.post("/grades/addGrade")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(params)))
        .andExpect(status().isNotFound());
  }

  @Test
  void addGradeRejectsScoreAboveOneHundred() throws Exception {
    Map<String, String> params = validGradeRequest();
    params.put("score", "101");

    mockMvc.perform(MockMvcRequestBuilders.post("/grades/addGrade")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(params)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void addGradeRejectsMissingScore() throws Exception {
    Map<String, String> params = validGradeRequest();
    params.remove("score");

    mockMvc.perform(MockMvcRequestBuilders.post("/grades/addGrade")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(params)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void addGradeRejectsNonNumericStudentId() throws Exception {
    Map<String, String> params = validGradeRequest();
    params.put("student_id", "not-a-number");

    mockMvc.perform(MockMvcRequestBuilders.post("/grades/addGrade")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(params)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void addGradeRejectsNonNumericScore() throws Exception {
    Map<String, String> params = validGradeRequest();
    params.put("score", "not-a-number");

    mockMvc.perform(MockMvcRequestBuilders.post("/grades/addGrade")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(params)))
        .andExpect(status().isBadRequest());
  }

  private Map<String, String> validGradeRequest() {
    Map<String, String> params = new HashMap<>();
    params.put("student_id", student.getId().toString());
    params.put("module_code", "CS102");
    params.put("score", "85");
    return params;
  }
}
