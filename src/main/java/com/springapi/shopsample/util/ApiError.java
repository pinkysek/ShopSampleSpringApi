package com.springapi.shopsample.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * ApiError is a class that represents an error response for the API.
 * It includes details such as the HTTP status, timestamp, message, and an optional detailed message.
 */
@Getter
@Setter
public class ApiError  {

    /**
     * The HTTP status of the error.
     */
    @JsonProperty()
    private String status;

    /**
     * The timestamp when the error occurred.
     * Formatted as an ISO 8601 string.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonProperty()
    private ZonedDateTime timestamp;

    /**
     * The error message.
     */
    @JsonProperty()
    private String message;

    /**
     * The detailed error message, if available.
     * This field is included only if it is not null.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty()
    private String detailedMessage;

    /**
     * Constructs a new ApiError with the given status and message.
     * The detailed message is set to null.
     *
     * @param status the HTTP status of the error
     * @param message the error message
     */
    public ApiError(HttpStatus status, String message) {
        this.timestamp = ZonedDateTimeMapper.getZoneLocalDateTime(LocalDateTime.now());
        this.status = status.name();
        this.message = message;
        this.detailedMessage = null;
    }

    /**
     * Constructs a new ApiError with the given status, message, and detailed message.
     *
     * @param status the HTTP status of the error
     * @param message the error message
     * @param detailedMessage the detailed error message
     */
    public ApiError(HttpStatus status, String message, String detailedMessage) {
        this.timestamp = ZonedDateTimeMapper.getZoneLocalDateTime(LocalDateTime.now());
        this.status = status.name();
        this.message = message;
        this.detailedMessage = detailedMessage;
    }
}