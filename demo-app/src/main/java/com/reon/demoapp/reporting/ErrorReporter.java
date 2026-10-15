package com.reon.demoapp.reporting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Clock;
import java.util.UUID;

@Component
public class ErrorReporter {
    private static final int MAX_STACK_TRACE_LENGTH = 4000;

    private final ErrorEventSender sender;
    private final Clock clock;
    private final String serviceName;

    public ErrorReporter(ErrorEventSender sender,
                         Clock clock,
                         @Value("${spring.application.name}") String serviceName) {
        this.sender = sender;
        this.clock = clock;
        this.serviceName = serviceName;
    }

    public void report(Exception ex) {
        ErrorEvent event = ErrorEvent.builder()
                .eventId(UUID.randomUUID())
                .serviceName(serviceName)
                .exceptionType(ex.getClass().getName())
                .message(ex.getMessage())
                .stackTrace(stackTraceOf(ex))
                .occurredAt(clock.instant())
                .build();

        sender.send(event);
    }

    private String stackTraceOf(Throwable ex) {
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        String trace = writer.toString();
        return trace.length() > MAX_STACK_TRACE_LENGTH
                ? trace.substring(0, MAX_STACK_TRACE_LENGTH)
                : trace;
    }
}
