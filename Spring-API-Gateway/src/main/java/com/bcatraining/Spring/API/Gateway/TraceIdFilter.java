package com.bcatraining.Spring.API.Gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Component
public class TraceIdFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(TraceIdFilter.class);
    private static final String TRACE_ID_HEADER = "X-Request-Trace";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Instant start = Instant.now();
        String traceId = UUID.randomUUID().toString();

        // Mutate request to include new header
        exchange = exchange.mutate()
                .request(builder -> builder.header(TRACE_ID_HEADER, traceId))
                .build();

        String method = exchange.getRequest().getMethod().toString();
        String path = exchange.getRequest().getURI().getPath();

        int status = exchange.getResponse().getStatusCode() != null
                ? exchange.getResponse().getStatusCode().value()
                : 0;
        return chain.filter(exchange).doFinally(signalType -> {
            Instant end = Instant.now();
            long durationMs = Duration.between(start, end).toMillis();

            logger.info("[{}] {} {} - {} - {} ms",
                    traceId, method, path, status, durationMs);
        });
    }

    @Override
    public int getOrder() {
        return -1; // Ensure this runs early
    }
}
