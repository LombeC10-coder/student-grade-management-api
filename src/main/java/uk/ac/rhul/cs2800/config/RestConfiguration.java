package uk.ac.rhul.cs2800.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import uk.ac.rhul.cs2800.model.Grade;
import uk.ac.rhul.cs2800.model.Module;
import uk.ac.rhul.cs2800.model.Student;

/**
 * Configuration class for customising Spring Data REST settings. This class allows exposure of
 * entity IDs and other REST-related configurations.
 */
@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

  /**
   * Configures the REST repository settings, including exposing entity IDs and enabling CORS
   * support.
   *
   * @param config the {@link RepositoryRestConfiguration} to customise
   * @param cors the {@link CorsRegistry} to configure CORS settings
   */
  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
      CorsRegistry cors) {
    // Expose entity IDs for the following classes
    config.exposeIdsFor(Student.class);
    config.exposeIdsFor(Module.class);
    config.exposeIdsFor(Grade.class);
  }
}
