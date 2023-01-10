package io.noties.prism4j;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Pattern;
import io.noties.prism4j.Prism4j.Token;
import java.util.HashSet;
import java.util.Set;

abstract class ToString {

    private interface Cache {
        void markVisited(@NonNull Object obj);

        boolean visited(@NonNull Object obj);
    }

    private static class CacheImpl implements Cache {
        private final Set<Integer> cache;

        private CacheImpl() {
            this.cache = new HashSet(3);
        }

        public boolean visited(@NonNull Object o) {
            return this.cache.contains(key(o));
        }

        public void markVisited(@NonNull Object o) {
            this.cache.add(key(o));
        }

        @NonNull
        private static Integer key(@NonNull Object o) {
            return Integer.valueOf(System.identityHashCode(o));
        }
    }

    @NonNull
    static String toString(@NonNull Grammar grammar) {
        StringBuilder builder = new StringBuilder();
        toString(builder, new CacheImpl(), grammar);
        return builder.toString();
    }

    @NonNull
    static String toString(@NonNull Token token) {
        StringBuilder builder = new StringBuilder();
        toString(builder, new CacheImpl(), token);
        return builder.toString();
    }

    @NonNull
    static String toString(@NonNull Pattern pattern) {
        StringBuilder builder = new StringBuilder();
        toString(builder, new CacheImpl(), pattern);
        return builder.toString();
    }

    private ToString() {
    }

    private static void toString(@NonNull StringBuilder builder, @NonNull Cache cache, @NonNull Grammar grammar) {
        builder.append("Grammar{id=0x");
        builder.append(Integer.toHexString(System.identityHashCode(grammar)));
        builder.append(",name=\"");
        builder.append(grammar.name());
        builder.append('\"');
        if (cache.visited(grammar)) {
            builder.append(",[...]");
        } else {
            cache.markVisited(grammar);
            builder.append(",tokens=[");
            boolean first = true;
            for (Token token : grammar.tokens()) {
                if (first) {
                    first = false;
                } else {
                    builder.append(',');
                }
                toString(builder, cache, token);
            }
            builder.append(']');
        }
        builder.append('}');
    }

    private static void toString(@NonNull StringBuilder builder, @NonNull Cache cache, @NonNull Token token) {
        builder.append("Token{id=0x");
        builder.append(Integer.toHexString(System.identityHashCode(token)));
        builder.append(",name=\"");
        builder.append(token.name());
        builder.append('\"');
        if (cache.visited(token)) {
            builder.append(",[...]");
        } else {
            cache.markVisited(token);
            builder.append(",patterns=[");
            boolean first = true;
            for (Pattern pattern : token.patterns()) {
                if (first) {
                    first = false;
                } else {
                    builder.append(',');
                }
                toString(builder, cache, pattern);
            }
            builder.append(']');
        }
        builder.append('}');
    }

    private static void toString(@NonNull StringBuilder builder, @NonNull Cache cache, @NonNull Pattern pattern) {
        builder.append("Pattern{id=0x");
        builder.append(Integer.toHexString(System.identityHashCode(pattern)));
        if (cache.visited(pattern)) {
            builder.append(",[...]");
        } else {
            cache.markVisited(pattern);
            builder.append(",regex=\"");
            builder.append(pattern.regex());
            builder.append('\"');
            if (pattern.lookbehind()) {
                builder.append(",lookbehind=true");
            }
            if (pattern.greedy()) {
                builder.append(",greedy=true");
            }
            if (pattern.alias() != null) {
                builder.append(",alias=\"");
                builder.append(pattern.alias());
                builder.append('\"');
            }
            Grammar inside = pattern.inside();
            if (inside != null) {
                builder.append(",inside=");
                toString(builder, cache, inside);
            }
        }
        builder.append('}');
    }
}
