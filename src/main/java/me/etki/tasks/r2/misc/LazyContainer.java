package me.etki.tasks.r2.misc;

import java.util.concurrent.atomic.AtomicReference;

public class LazyContainer<T> {
    private final AtomicReference<T> value = new AtomicReference<>();

    public T get() {
        return value.get();
    }

    public LazyContainer<T> set(T value) {
        this.value.set(value);
        return this;
    }
}
