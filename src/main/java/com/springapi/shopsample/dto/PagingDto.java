package com.springapi.shopsample.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * PagingDto is a generic class that represents a paginated response.
 *
 * @param <T> the type of the items in the page
 */
@Getter
@Setter
@Schema(description = "Data Transfer Object for Paging.")
public class PagingDto<T> {

    @Schema(description = "The items in the current page.", example = "[{...},{...}]")
    private List<T> items;

    @Schema(description = "The current page number.", example = "1")
    private int pageNumber;

    @Schema(description = "The number of items per page.", example = "10")
    private int pageSize;

    @Schema(description = "The total number of items.", example = "100")
    private int totalCount;

    @Schema(description = "The total number of pages.", example = "10")
    private int totalPages;

    @Schema(description = "Indicates whether there is a previous page.", example = "true")
    private boolean hasPrevious;

    @Schema(description = "Indicates whether there is a next page.", example = "true")
    private boolean hasNext;

    /**
     * Constructs a new PagingDto with the specified items, total count, page number, and page size.
     * Calculates the total pages, and determines if there are previous and next pages.
     *
     * @param items      the items in the current page
     * @param totalCount the total number of items
     * @param pageNumber the current page number
     * @param pageSize   the number of items per page
     */
    public PagingDto(List<T> items, int totalCount, int pageNumber, int pageSize) {
        this.items = items;
        this.totalCount = totalCount;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
        this.hasPrevious = pageNumber > 1;
        this.hasNext = pageNumber < totalPages;
    }

}