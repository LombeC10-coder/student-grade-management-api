package uk.ac.rhul.cs2800.repository;

import org.springframework.data.repository.CrudRepository;
import uk.ac.rhul.cs2800.model.Grade;

/**
 * Repository interface for {@link Grade} entity. Provides CRUD operations inherited from
 * {@link CrudRepository}.
 */

public interface GradeRepository extends CrudRepository<Grade, Long> {

}
