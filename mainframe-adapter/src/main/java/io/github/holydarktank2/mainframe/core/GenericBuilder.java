package io.github.holydarktank2.mainframe.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {
    private final Supplier<T> instantiator;
    private final List<java.util.function.Consumer<T>> modifiers = new ArrayList<>();

    private GenericBuilder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> GenericBuilder<T> of(Supplier<T> instantiator) {
        return new GenericBuilder<>(instantiator);
    }

    public <V> GenericBuilder<T> with(BiConsumer<T, V> consumer, V value) {
        modifiers.add(instance -> consumer.accept(instance, value));
        return this;
    }

    public T build() {
        T value = instantiator.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        return value;
    }
}
