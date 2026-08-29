package com.example.ecommerce.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.stream.Collectors;


@ControllerAdvice
public class GraphQLExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError handle(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return GraphQLError.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(message)
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(ResourceNotFoundException ex) {
        return GraphQLError.newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(ex.getMessage())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(IllegalArgumentException ex) {
        return GraphQLError.newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(ex.getMessage())
                .build();
    }


}
