package com.ziyao.ideal.elasticsearch.query;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.data.elasticsearch.core.query.Field;
import org.springframework.data.elasticsearch.core.query.SimpleField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ziyao zhang
 */
@Getter
public class BetweenQueries {


    private final List<BetweenQuery> queries;

    public BetweenQueries(BetweenQuery... queries) {
        this.queries = Lists.newArrayList(queries);
    }

    public static BetweenQueries of(BetweenQuery... queries) {
        return new BetweenQueries(queries);
    }

    public static BetweenQuery of(String fieldName, Object upperBound, Object lowerBound) {
        return new BetweenQuery(new SimpleField(fieldName), upperBound, lowerBound);
    }

    public static BetweenQuery of(Field field, Object upperBound, Object lowerBound) {
        return new BetweenQuery(field, upperBound, lowerBound);
    }

    @Getter
    public static class BetweenQuery {
        private final Field field;
        private final Object upperBound;
        private final Object lowerBound;

        public BetweenQuery(Field field, Object upperBound, Object lowerBound) {
            this.field = field;
            this.upperBound = upperBound;
            this.lowerBound = lowerBound;
        }
    }
}
