package com.springapi.shopsample.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapi.shopsample.util.ZonedDateTimeMapper;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object for Api Error.")
public class ApiErrorDto {

    @Schema(description = "The HTTP status of the error.", example = "BAD_REQUEST")
    @JsonProperty()
    private String status;

    @Schema(description = "The timestamp when the error occurred [date-time-format](https://tools.ietf.org/html/rfc3339#section-5.6).", example = "2024-12-01T12:00:00+01:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @JsonProperty()
    private ZonedDateTime timestamp;

    @Schema(description = "The error message.", example = "An error occurred.")
    @JsonProperty()
    private String message;

    @Schema(description = "TThe detailed error message, if available (This field is included only if it is not null).", example = "The value of the field 'name' must be a string.")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty()
    private String detailedMessage;

    /**
     * Constructs a new ApiError with the given status and message.
     * The detailed message is set to null.
     *
     * @param status  the HTTP status of the error
     * @param message the error message
     */
    public ApiErrorDto(HttpStatus status, String message) {
        this.timestamp = ZonedDateTimeMapper.getZoneLocalDateTime(LocalDateTime.now());
        this.status = status.name();
        this.message = message;
        this.detailedMessage = null;
    }

    /**
     * Constructs a new ApiError with the given status, message, and detailed message.
     *
     * @param status          the HTTP status of the error
     * @param message         the error message
     * @param detailedMessage the detailed error message
     */
    public ApiErrorDto(HttpStatus status, String message, String detailedMessage) {
        this.timestamp = ZonedDateTimeMapper.getZoneLocalDateTime(LocalDateTime.now());
        this.status = status.name();
        this.message = message;
        this.detailedMessage = detailedMessage;
    }
}