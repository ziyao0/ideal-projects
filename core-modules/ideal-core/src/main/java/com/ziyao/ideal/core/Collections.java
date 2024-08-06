package com.ziyao.ideal.core;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author ziyao zhang
 */
public abstract class Collections {

    /**
     * Return {@code true} if the supplied Collection is {@code null} or empty.
     * Otherwise, return {@code false}.
     *
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean nonNull(Collection<?> collection) {
        return (collection != null && !collection.isEmpty());
    }

    /**
     * Return {@code true} if the supplied Map is {@code null} or empty.
     * Otherwise, return {@code false}.
     *
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static <T> List<List<T>> partition(List<T> list, int size) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        } else if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        } else {
            return new Partition<>(list, size);
        }
    }

    private static class Partition<T> extends AbstractList<List<T>> {
        private final List<T> list;
        private final int size;

        private Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        public List<T> get(int index) {
            int listSize = this.size();
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
            } else if (index >= listSize) {
                throw new IndexOutOfBoundsException("Index " + index + " must be less than size " + listSize);
            } else {
                int start = index * this.size;
                int end = Math.min(start + this.size, this.list.size());
                return this.list.subList(start, end);
            }
        }

        public int size() {
            return (int)Math.ceil((double)this.list.size() / (double)this.size);
        }

        public boolean isEmpty() {
            return this.list.isEmpty();
        }
    }

}
