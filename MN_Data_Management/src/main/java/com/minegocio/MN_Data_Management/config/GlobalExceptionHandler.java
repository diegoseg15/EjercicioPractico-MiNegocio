package com.minegocio.MN_Data_Management.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ProblemDetail handleValidationErrors(WebExchangeBindException ex, ServerWebExchange exchange) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Solicitud inválida");
        pd.setDetail("Existen errores de validación en el cuerpo de la petición.");
        pd.setInstance(exchange.getRequest().getURI());
        pd.setProperty("errors", errors);
        return pd;
    }

    @ExceptionHandler({ DuplicateKeyException.class, DataIntegrityViolationException.class })
    public ProblemDetail handleConflict(RuntimeException ex, ServerWebExchange exchange) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Conflicto de datos");
        pd.setDetail("El recurso ya existe o viola una restricción de unicidad.");
        pd.setInstance(exchange.getRequest().getURI());
        pd.setProperty("cause", ex.getClass().getSimpleName());
        return pd;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex, ServerWebExchange exchange) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Parámetros inválidos");
        pd.setDetail(ex.getMessage() != null ? ex.getMessage() : "Parámetros inválidos en la solicitud.");
        pd.setInstance(exchange.getRequest().getURI());
        return pd;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail handleResponseStatus(ResponseStatusException ex, ServerWebExchange exchange) {
        ProblemDetail pd = ProblemDetail.forStatus(ex.getStatusCode());
        pd.setTitle(ex.getReason() != null ? ex.getReason() : ex.getStatusCode().toString());
        pd.setDetail(ex.getMessage());
        pd.setInstance(exchange.getRequest().getURI());
        return pd;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAny(Exception ex, ServerWebExchange exchange) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Error interno del servidor");
        pd.setDetail("Ha ocurrido un error inesperado. Intenta más tarde.");
        pd.setInstance(exchange.getRequest().getURI());
        pd.setProperty("cause", ex.getClass().getName());
        return pd;
    }
}
