package com.padc.demo.core.auditing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "utcDateTimeProvider" )
public class PersistenceConfig
{
    @Bean
    public AuditorAware<String> auditorAware()
    {
        return new AuditorAwareImplementation();
    }

    /**
     * EnableJpaAuditing has a parametre "dateTimeProviderRef". We make a bean, that provides
     * the correct wintertime for auditing in database.
     * https://www.baeldung.com/java-zone-offset
     * https://stackoverflow.com/questions/55250489/spring-jpa-hibernate-cet-timezone-for-auditingentitylistener
     * @return DateTimeProvider
     */
    @Bean
    public DateTimeProvider utcDateTimeProvider() {
        ZoneOffset zoneOffSet= ZoneOffset.of("+02:00");
        OffsetDateTime date = OffsetDateTime.now(zoneOffSet);
        return () -> Optional.of(LocalDateTime.now(ZoneId.from(date)));
    }
}
