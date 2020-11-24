package com.padc.demo.core.auditing;

import org.springframework.data.domain.AuditorAware;

import javax.management.relation.Role;
import java.util.Optional;

public class AuditorAwareImplementation implements AuditorAware<String>
{

    @Override
    public Optional<String> getCurrentAuditor()
    {
        return Optional.of("admin");
    }
}
