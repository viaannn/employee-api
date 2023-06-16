package com.edts.edtstechnicaltest.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse<T> extends BaseResponse<T> {
    private long totalPages;
    private long totalItems;

    public PageResponse(int status, String message, T data, long totalItems, long totalPages) {
        super(status, message, data);
        setTotalPages(totalPages);
        setTotalItems(totalItems);
    }
}
