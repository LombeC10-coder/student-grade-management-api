package uk.ac.rhul.cs2800.repository;


import org.springframework.data.repository.CrudRepository;
import uk.ac.rhul.cs2800.model.Student;

/**
 * Repository interface for {@link Student} entity. Provides CRUD operations inherited from
 * {@link CrudRepository}.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {


}
