package me.etki.tasks.r2.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.List;

public class Page<T> {
    @Getter
    private final List<T> content;
    @Getter
    private final Pagination pagination;

    @JsonCreator
    public Page(List<T> content, Pagination pagination) {
        this.content = content;
        this.pagination = pagination;
    }

    public static class Pagination {
        @Getter
        private final long page;
        @Getter
        private final long pages;
        @Getter
        private final long size;
        @Getter
        private final long total;

        @SuppressWarnings("WeakerAccess")
        @JsonCreator
        public Pagination(long page, long pages, long size, long total) {
            this.page = page;
            this.pages = pages;
            this.size = size;
            this.total = total;
        }

        public static Pagination from(long page, long size, long total) {
            return new Pagination(page, (total + size - 1) / size, size, total);
        }
    }
}
