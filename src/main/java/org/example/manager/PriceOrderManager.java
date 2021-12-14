package org.example.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceOrderManager {
    private final NamedParameterJdbcTemplate template;

    @Scheduled(fixedRate = 1000)
    public void update() {
    }
}
