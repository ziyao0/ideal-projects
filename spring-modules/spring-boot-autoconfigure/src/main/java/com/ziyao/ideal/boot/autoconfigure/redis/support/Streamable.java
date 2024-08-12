package com.ziyao.ideal.boot.autoconfigure.redis.support;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author ziyao zhang
 */
@FunctionalInterface
public interface Streamable<T> extends Iterable<T>, Supplier<Stream<T>> {

    /**
     * Returns an empty {@link Streamable}.
     * <p>
     * will never be {@literal null}.
     */
    static <T> Streamable<T> empty() {
        return Collections::emptyIterator;
    }

    /**
     * Returns a {@link Streamable} with the given elements.
     *
     * @param t the elements to return.
     */
    @SafeVarargs
    static <T> Streamable<T> of(T... t) {
        return () -> Arrays.asList(t).iterator();
    }

    /**
     * Returns a {@link Streamable} for the given {@link Iterable}.
     *
     * @param iterable must not be {@literal null}.
     */
    static <T> Streamable<T> of(Iterable<T> iterable) {

        Assert.notNull(iterable, "Iterable must not be null");

        return iterable::iterator;
    }

    static <T> Streamable<T> of(Supplier<? extends Stream<T>> supplier) {
        return LazyStreamable.of(supplier);
    }

    /**
     * Creates a non-parallel {@link Stream} of the underlying {@link Iterable}.
     * <p>
     * will never be {@literal null}.
     */
    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Returns a new {@link Streamable} that will apply the given {@link Function} to the current one.
     *
     * @param mapper must not be {@literal null}.
     * @see Stream#map(Function)
     */
    default <R> Streamable<R> map(Function<? super T, ? extends R> mapper) {

        Assert.notNull(mapper, "Mapping function must not be null");

        return Streamable.of(() -> stream().map(mapper));
    }

    /**
     * Returns a new {@link Streamable} that will apply the given {@link Function} to the current one.
     *
     * @param mapper must not be {@literal null}.
     * @see Stream#flatMap(Function)
     */
    default <R> Streamable<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {

        Assert.notNull(mapper, "Mapping function must not be null");

        return Streamable.of(() -> stream().flatMap(mapper));
    }

    /**
     * Returns a new {@link Streamable} that will apply the given filter {@link Predicate} to the current one.
     *
     * @param predicate must not be {@literal null}.
     * @see Stream#filter(Predicate)
     */
    default Streamable<T> filter(Predicate<? super T> predicate) {

        Assert.notNull(predicate, "Filter predicate must not be null");

        return Streamable.of(() -> stream().filter(predicate));
    }

    /**
     * Returns whether the current {@link Streamable} is empty.
     */
    default boolean isEmpty() {
        return !iterator().hasNext();
    }

    /**
     * Creates a new {@link Streamable} from the current one and the given {@link Stream} concatenated.
     *
     * @param stream must not be {@literal null}.
     */
    default Streamable<T> and(Supplier<? extends Stream<? extends T>> stream) {

        Assert.notNull(stream, "Stream must not be null");

        return Streamable.of(() -> Stream.concat(this.stream(), stream.get()));
    }

    /**
     * Creates a new {@link Streamable} from the current one and the given values concatenated.
     *
     * @param others must not be {@literal null}.
     *               will never be {@literal null}.
     */
    @SuppressWarnings("unchecked")
    default Streamable<T> and(T... others) {

        Assert.notNull(others, "Other values must not be null");

        return Streamable.of(() -> Stream.concat(this.stream(), Arrays.stream(others)));
    }

    /**
     * Creates a new {@link Streamable} from the current one and the given {@link Iterable} concatenated.
     *
     * @param iterable must not be {@literal null}.
     *                 will never be {@literal null}.
     */
    default Streamable<T> and(Iterable<? extends T> iterable) {

        Assert.notNull(iterable, "Iterable must not be null");

        return Streamable.of(() -> Stream.concat(this.stream(), StreamSupport.stream(iterable.spliterator(), false)));
    }

    /**
     * Convenience method to allow adding a {@link Streamable} directly as otherwise the invocation is ambiguous between
     * {@link #and(Iterable)} and {@link #and(Supplier)}.
     *
     * @param streamable must not be {@literal null}.
     *                   will never be {@literal null}.
     */
    default Streamable<T> and(Streamable<? extends T> streamable) {
        return and((Supplier<? extends Stream<? extends T>>) streamable);
    }

    /**
     * Creates a new, unmodifiable {@link List}.
     * <p>
     * will never be {@literal null}.
     */
    default List<T> toList() {
        return stream().collect(StreamUtils.toUnmodifiableList());
    }

    /**
     * Creates a new, unmodifiable {@link Set}.
     * <p>
     * will never be {@literal null}.
     */
    default Set<T> toSet() {
        return stream().collect(StreamUtils.toUnmodifiableSet());
    }

    /*
     * (non-Javadoc)
     * @see java.util.function.Supplier#get()
     */
    default Stream<T> get() {
        return stream();
    }

    /**
     * A collector to easily produce a {@link Streamable} from a {@link Stream} using {@link Collectors#toList} as
     * intermediate collector.
     *
     * @see #toStreamable(Collector)
     */
    public static <S> Collector<S, ?, Streamable<S>> toStreamable() {
        return toStreamable(Collectors.toList());
    }

    /**
     * A collector to easily produce a {@link Streamable} from a {@link Stream} and the given intermediate collector.
     */
    @SuppressWarnings("unchecked")
    public static <S, T extends Iterable<S>> Collector<S, ?, Streamable<S>> toStreamable(
            Collector<S, ?, T> intermediate) {

        return Collector.of( //
                (Supplier<T>) intermediate.supplier(), //
                (BiConsumer<T, S>) intermediate.accumulator(), //
                (BinaryOperator<T>) intermediate.combiner(), //
                Streamable::of);
    }
}
