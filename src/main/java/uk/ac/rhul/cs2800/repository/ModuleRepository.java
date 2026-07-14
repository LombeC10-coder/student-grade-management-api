package uk.ac.rhul.cs2800.repository;

import org.springframework.data.repository.CrudRepository;
import uk.ac.rhul.cs2800.model.Module;

/**
 * Repository interface for {@link Module} entity. Provides CRUD operations inherited from
 * {@link CrudRepository}.
 */

public interface ModuleRepository extends CrudRepository<Module, String> {



}
