package io.noties.prism4j;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Pattern;
import io.noties.prism4j.Prism4j.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Cloner {

    static class Impl extends Cloner {

        interface Context {
            @Nullable
            Grammar grammar(@NonNull Grammar grammar);

            @Nullable
            Pattern pattern(@NonNull Pattern pattern);

            void save(@NonNull Grammar grammar, @NonNull Grammar grammar2);

            void save(@NonNull Pattern pattern, @NonNull Pattern pattern2);

            void save(@NonNull Token token, @NonNull Token token2);

            @Nullable
            Token token(@NonNull Token token);
        }

        private static class ContextImpl implements Context {
            private final Map<Integer, Object> cache;

            private ContextImpl() {
                this.cache = new HashMap(3);
            }

            @Nullable
            public Grammar grammar(@NonNull Grammar origin) {
                return (Grammar) this.cache.get(Integer.valueOf(key(origin)));
            }

            @Nullable
            public Token token(@NonNull Token origin) {
                return (Token) this.cache.get(Integer.valueOf(key(origin)));
            }

            @Nullable
            public Pattern pattern(@NonNull Pattern origin) {
                return (Pattern) this.cache.get(Integer.valueOf(key(origin)));
            }

            public void save(@NonNull Grammar origin, @NonNull Grammar clone) {
                this.cache.put(Integer.valueOf(key(origin)), clone);
            }

            public void save(@NonNull Token origin, @NonNull Token clone) {
                this.cache.put(Integer.valueOf(key(origin)), clone);
            }

            public void save(@NonNull Pattern origin, @NonNull Pattern clone) {
                this.cache.put(Integer.valueOf(key(origin)), clone);
            }

            private static int key(@NonNull Object o) {
                return System.identityHashCode(o);
            }
        }

        Impl() {
        }

        /* Access modifiers changed, original: 0000 */
        @NonNull
        public Grammar clone(@NonNull Grammar grammar) {
            return clone(new ContextImpl(), grammar);
        }

        /* Access modifiers changed, original: 0000 */
        @NonNull
        public Token clone(@NonNull Token token) {
            return clone(new ContextImpl(), token);
        }

        /* Access modifiers changed, original: 0000 */
        @NonNull
        public Pattern clone(@NonNull Pattern pattern) {
            return clone(new ContextImpl(), pattern);
        }

        @NonNull
        private Grammar clone(@NonNull Context context, @NonNull Grammar grammar) {
            Grammar clone = context.grammar(grammar);
            if (clone != null) {
                return clone;
            }
            List<Token> tokens = grammar.tokens();
            List<Token> out = new ArrayList(tokens.size());
            clone = new GrammarImpl(grammar.name(), out);
            context.save(grammar, clone);
            for (Token token : tokens) {
                out.add(clone(context, token));
            }
            return clone;
        }

        @NonNull
        private Token clone(@NonNull Context context, @NonNull Token token) {
            Token clone = context.token(token);
            if (clone != null) {
                return clone;
            }
            List<Pattern> patterns = token.patterns();
            List<Pattern> out = new ArrayList(patterns.size());
            clone = new TokenImpl(token.name(), out);
            context.save(token, clone);
            for (Pattern pattern : patterns) {
                out.add(clone(context, pattern));
            }
            return clone;
        }

        @NonNull
        private Pattern clone(@NonNull Context context, @NonNull Pattern pattern) {
            Pattern clone = context.pattern(pattern);
            if (clone != null) {
                return clone;
            }
            Grammar inside = pattern.inside();
            clone = new PatternImpl(pattern.regex(), pattern.lookbehind(), pattern.greedy(), pattern.alias(), inside != null ? clone(context, inside) : null);
            context.save(pattern, clone);
            return clone;
        }
    }

    @NonNull
    public abstract Grammar clone(@NonNull Grammar grammar);

    @NonNull
    public abstract Pattern clone(@NonNull Pattern pattern);

    @NonNull
    public abstract Token clone(@NonNull Token token);

    Cloner() {
    }

    @NonNull
    static Cloner create() {
        return new Impl();
    }
}
