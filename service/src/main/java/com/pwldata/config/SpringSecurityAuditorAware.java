package com.pwldata.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

class SpringSecurityAuditorAware implements AuditorAware<String> {

  public Optional<String> getCurrentAuditor() {
    String uname = SecurityContextHolder.getContext().getAuthentication().getName();
    return Optional.of(uname);
  }
}