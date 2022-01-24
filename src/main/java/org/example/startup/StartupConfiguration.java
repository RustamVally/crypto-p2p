package org.example.startup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import javax.sql.DataSource;

@Configuration
public class StartupConfiguration {
  @Bean
  public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
    final DatabaseStartupValidator databaseStartupValidator = new DatabaseStartupValidator();
    databaseStartupValidator.setDataSource(dataSource);
    return databaseStartupValidator;
  }

  @Bean
  @DependsOn("databaseStartupValidator")
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
