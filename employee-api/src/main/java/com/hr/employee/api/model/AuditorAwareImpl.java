package com.hr.employee.api.model;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {


	@Override
	public Optional<String> getCurrentAuditor() {
		// todo get token decoder and get user info
			return Optional.of("UNKNOWN");
	}

}
