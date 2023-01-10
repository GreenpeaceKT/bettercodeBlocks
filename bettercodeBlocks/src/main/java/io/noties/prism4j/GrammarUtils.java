package io.noties.prism4j;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.noties.prism4j.Prism4j.Grammar;
import io.noties.prism4j.Prism4j.Pattern;
import io.noties.prism4j.Prism4j.Token;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GrammarUtils {
    private static final Cloner CLONER = Cloner.create();

    public interface TokenFilter {
        boolean test(@NonNull Token token);
    }

    @Nullable
    public static Token findToken(@NonNull Grammar grammar, @NonNull String path) {
        return findToken(grammar, path.split("/"), 0);
    }

    @Nullable
    private static Token findToken(@NonNull Grammar grammar, @NonNull String[] parts, int index) {
        String part = parts[index];
        boolean z = true;
        if (index != parts.length - 1) {
            z = false;
        }
        boolean last = z;
        for (Token token : grammar.tokens()) {
            if (part.equals(token.name())) {
                if (last) {
                    return token;
                }
                Grammar inside = findFirstInsideGrammar(token);
                if (inside != null) {
                    return findToken(inside, parts, index + 1);
                }
                return null;
            }
        }
        return null;
    }

    public static void insertBeforeToken(@NonNull Grammar grammar, @NonNull String path, Token... tokens) {
        if (tokens != null && tokens.length != 0) {
            insertBeforeToken(grammar, path.split("/"), 0, tokens);
        }
    }

    private static void insertBeforeToken(@NonNull Grammar grammar, @NonNull String[] parts, int index, @NonNull Token[] tokens) {
        String part = parts[index];
        boolean z = true;
        if (index != parts.length - 1) {
            z = false;
        }
        boolean last = z;
        List<Token> grammarTokens = grammar.tokens();
        int i = 0;
        int size = grammarTokens.size();
        while (i < size) {
            Token token = (Token) grammarTokens.get(i);
            if (!part.equals(token.name())) {
                i++;
            } else if (last) {
                insertTokensAt(i, grammarTokens, tokens);
                return;
            } else {
                Grammar inside = findFirstInsideGrammar(token);
                if (inside != null) {
                    insertBeforeToken(inside, parts, index + 1, tokens);
                    return;
                }
                return;
            }
        }
    }

    @Nullable
    public static Grammar findFirstInsideGrammar(@NonNull Token token) {
        for (Pattern pattern : token.patterns()) {
            if (pattern.inside() != null) {
                return pattern.inside();
            }
        }
        return null;
    }

    private static void insertTokensAt(int start, @NonNull List<Token> grammarTokens, @NonNull Token[] tokens) {
        int length = tokens.length;
        for (int i = 0; i < length; i++) {
            grammarTokens.add(start + i, tokens[i]);
        }
    }

    @NonNull
    public static Grammar clone(@NonNull Grammar grammar) {
        return CLONER.clone(grammar);
    }

    @NonNull
    public static Token clone(@NonNull Token token) {
        return CLONER.clone(token);
    }

    @NonNull
    public static Pattern clone(@NonNull Pattern pattern) {
        return CLONER.clone(pattern);
    }

    @NonNull
    public static Grammar extend(@NonNull Grammar grammar, @NonNull String name, Token... tokens) {
        int size;
        int i = 0;
        if (tokens != null) {
            size = tokens.length;
        } else {
            size = 0;
        }
        if (size == 0) {
            return new GrammarImpl(name, clone(grammar).tokens());
        }
        Map<String, Token> overrides = new HashMap(size);
        int length = tokens.length;
        while (i < length) {
            Token token = tokens[i];
            overrides.put(token.name(), token);
            i++;
        }
        List<Token> origins = grammar.tokens();
        List<Token> out = new ArrayList(origins.size());
        for (Token origin : origins) {
            Token override = (Token) overrides.get(origin.name());
            if (override != null) {
                out.add(override);
            } else {
                out.add(clone(origin));
            }
        }
        return new GrammarImpl(name, out);
    }

    @NonNull
    public static Grammar extend(@NonNull Grammar grammar, @NonNull String name, @NonNull TokenFilter filter, Token... tokens) {
        int size;
        Map<String, Token> overrides;
        int i = 0;
        if (tokens != null) {
            size = tokens.length;
        } else {
            size = 0;
        }
        if (size == 0) {
            overrides = Collections.emptyMap();
        } else {
            Map<String, Token> overrides2 = new HashMap(size);
            int length = tokens.length;
            while (i < length) {
                Token token = tokens[i];
                overrides2.put(token.name(), token);
                i++;
            }
            overrides = overrides2;
        }
        List<Token> origins = grammar.tokens();
        List<Token> out = new ArrayList(origins.size());
        for (Token origin : origins) {
            if (filter.test(origin)) {
                Token override = (Token) overrides.get(origin.name());
                if (override != null) {
                    out.add(override);
                } else {
                    out.add(clone(origin));
                }
            }
        }
        return new GrammarImpl(name, out);
    }

    @NonNull
    public static Grammar require(@NonNull Prism4j prism4j, @NonNull String name) {
        Grammar grammar = prism4j.grammar(name);
        if (grammar != null) {
            return grammar;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected state, requested language is not found: ");
        stringBuilder.append(name);
        throw new IllegalStateException(stringBuilder.toString());
    }

    private GrammarUtils() {
    }
}
