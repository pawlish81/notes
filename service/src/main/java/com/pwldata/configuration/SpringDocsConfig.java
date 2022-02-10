package com.pwldata.configuration;

import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SpringDocConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SpringDocsConfig {

  @Bean
  @Primary
  SpringDocConfiguration springDocConfiguration() {
    return new SpringDocConfiguration();
  }

  @Bean
  @Primary
  public SpringDocConfigProperties springDocConfigProperties() {
    return new SpringDocConfigProperties();
  }
}