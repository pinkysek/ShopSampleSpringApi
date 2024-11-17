package com.springapi.shopsample.dto;

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
public class PagingDto<T> {

    /**
     * The items in the current page.
     */
    private List<T> items;

    /**
     * The current page number.
     */
    private int pageNumber;

    /**
     * The number of items per page.
     */
    private int pageSize;

    /**
     * The total number of items.
     */
    private int totalCount;

    /**
     * The total number of pages.
     */
    private int totalPages;

    /**
     * Indicates whether there is a previous page.
     */
    private boolean hasPrevious;

    /**
     * Indicates whether there is a next page.
     */
    private boolean hasNext;

    /**
     * Constructs a new PagingDto with the specified items, total count, page number, and page size.
     * Calculates the total pages, and determines if there are previous and next pages.
     *
     * @param items the items in the current page
     * @param totalCount the total number of items
     * @param pageNumber the current page number
     * @param pageSize the number of items per page
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