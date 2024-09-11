package com.assignment.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for error responses.
 * Contains an error message.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    /**
     * The error message.
     */
    private String message;
}
