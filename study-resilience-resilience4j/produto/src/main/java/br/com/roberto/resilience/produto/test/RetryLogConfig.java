package br.com.roberto.resilience.produto.test;

import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.retry.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class RetryLogConfig {

    private final Logger logger = LoggerFactory.getLogger(RetryLogConfig.class);

    @Bean
    public RegistryEventConsumer<Retry> retryLog() {
        return new RegistryEventConsumer<Retry>() {


            @Override
            public void onEntryAddedEvent(EntryAddedEvent<Retry> entryAddedEvent) {
                AtomicInteger acum = new AtomicInteger();
                entryAddedEvent.getAddedEntry()
                        .getEventPublisher()
                        .onRetry(event -> {
                            logger.info("Retry "+ (acum.getAndIncrement()) +event.toString());
                        });
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<Retry> entryRemoveEvent) {

            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<Retry> entryReplacedEvent) {

            }
        };
    }


}
