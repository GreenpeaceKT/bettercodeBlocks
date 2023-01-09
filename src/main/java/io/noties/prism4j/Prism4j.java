package io.noties.prism4j;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

public class Prism4j {
    private final GrammarLocator grammarLocator;

    public interface Visitor {
        void visit(@NonNull List<? extends Node> list);
    }

    public interface Grammar {
        @NonNull
        String name();

        @NonNull
        List<Token> tokens();
    }

    public interface Pattern {
        @Nullable
        String alias();

        boolean greedy();

        @Nullable
        Grammar inside();

        boolean lookbehind();

        @NonNull
        java.util.regex.Pattern regex();
    }

    public interface Node {
        boolean isSyntax();

        int textLength();
    }

    public interface Syntax extends Node {
        @Nullable
        String alias();

        @NonNull
        List<? extends Node> children();

        boolean greedy();

        @NonNull
        String matchedString();

        boolean tokenized();

        @NonNull
        String type();
    }

    public interface Text extends Node {
        @NonNull
        String literal();
    }

    public interface Token {
        @NonNull
        String name();

        @NonNull
        List<Pattern> patterns();
    }

    @NonNull
    public static Grammar grammar(@NonNull String name, @NonNull List<Token> tokens) {
        return new GrammarImpl(name, tokens);
    }

    @NonNull
    public static Grammar grammar(@NonNull String name, Token... tokens) {
        return new GrammarImpl(name, ArrayUtils.toList(tokens));
    }

    @NonNull
    public static Token token(@NonNull String name, @NonNull List<Pattern> patterns) {
        return new TokenImpl(name, patterns);
    }

    @NonNull
    public static Token token(@NonNull String name, Pattern... patterns) {
        return new TokenImpl(name, ArrayUtils.toList(patterns));
    }

    @NonNull
    public static Pattern pattern(@NonNull java.util.regex.Pattern regex) {
        return new PatternImpl(regex, false, false, null, null);
    }

    @NonNull
    public static Pattern pattern(@NonNull java.util.regex.Pattern regex, boolean lookbehind) {
        return new PatternImpl(regex, lookbehind, false, null, null);
    }

    @NonNull
    public static Pattern pattern(@NonNull java.util.regex.Pattern regex, boolean lookbehind, boolean greedy) {
        return new PatternImpl(regex, lookbehind, greedy, null, null);
    }

    @NonNull
    public static Pattern pattern(@NonNull java.util.regex.Pattern regex, boolean lookbehind, boolean greedy, @Nullable String alias) {
        return new PatternImpl(regex, lookbehind, greedy, alias, null);
    }

    @NonNull
    public static Pattern pattern(@NonNull java.util.regex.Pattern regex, boolean lookbehind, boolean greedy, @Nullable String alias, @Nullable Grammar inside) {
        return new PatternImpl(regex, lookbehind, greedy, alias, inside);
    }

    public Prism4j(@NonNull GrammarLocator grammarLocator) {
        this.grammarLocator = grammarLocator;
    }

    @NonNull
    public List<Node> tokenize(@NonNull String text, @NonNull Grammar grammar) {
        List<Node> entries = new ArrayList(3);
        entries.add(new TextImpl(text));
        matchGrammar(text, entries, grammar, 0, 0, false, null);
        return entries;
    }

    @Nullable
    public Grammar grammar(@NonNull String name) {
        return this.grammarLocator.grammar(this, name);
    }

    private void matchGrammar(@NonNull String text, @NonNull List<Node> entries, @NonNull Grammar grammar, int index, int startPosition, boolean oneShot, @Nullable Token target) {
        String str = text;
        List<Node> list = entries;
        int textLength = text.length();
        Iterator it = grammar.tokens().iterator();
        while (it.hasNext()) {
            Token token = (Token) it.next();
            if (token != target) {
                Iterator it2;
                Iterator it3 = token.patterns().iterator();
                while (it3.hasNext()) {
                    java.util.regex.Pattern regex;
                    int textLength2;
                    Iterator it4;
                    Pattern pattern;
                    int i;
                    Token token2;
                    Pattern pattern2 = (Pattern) it3.next();
                    boolean lookbehind = pattern2.lookbehind();
                    boolean greedy = pattern2.greedy();
                    int lookbehindLength = 0;
                    java.util.regex.Pattern regex2 = pattern2.regex();
                    int i2 = index;
                    int position = startPosition;
                    while (i2 < entries.size()) {
                        if (entries.size() <= textLength) {
                            int i3;
                            Node node = (Node) list.get(i2);
                            if (isSyntaxNode(node)) {
                                regex = regex2;
                                textLength2 = textLength;
                                it2 = it;
                                it4 = it3;
                                pattern = pattern2;
                                i3 = 1;
                            } else {
                                int lookbehindLength2;
                                Matcher matcher;
                                String str2;
                                int greedyAdd;
                                boolean greedyMatch;
                                String str3 = ((Text) node).literal();
                                int greedyAdd2;
                                if (!greedy || i2 == entries.size() - 1) {
                                    lookbehindLength2 = lookbehindLength;
                                    i = i2;
                                    greedyAdd2 = 0;
                                    textLength2 = textLength;
                                    matcher = regex2.matcher(str3);
                                    textLength = 1;
                                    str2 = str3;
                                    greedyAdd = greedyAdd2;
                                    greedyMatch = false;
                                    i2 = i;
                                } else {
                                    matcher = regex2.matcher(str);
                                    matcher.region(position, textLength);
                                    if (!matcher.find()) {
                                        textLength2 = textLength;
                                        it2 = it;
                                        it4 = it3;
                                        break;
                                    }
                                    int from;
                                    int to;
                                    greedyAdd2 = matcher.start();
                                    if (lookbehind) {
                                        from = greedyAdd2 + matcher.group(1).length();
                                    } else {
                                        from = greedyAdd2;
                                    }
                                    lookbehindLength2 = lookbehindLength;
                                    lookbehindLength = matcher.start() + matcher.group(0).length();
                                    greedyAdd2 = i2;
                                    greedyAdd = position;
                                    i = i2;
                                    i2 = entries.size();
                                    textLength2 = textLength;
                                    textLength = greedyAdd;
                                    Matcher matcher2 = matcher;
                                    int k = greedyAdd2;
                                    greedyAdd2 = 0;
                                    int greedyAdd3 = position;
                                    position = i;
                                    while (k < i2) {
                                        if (textLength >= lookbehindLength) {
                                            if (!isSyntaxNode((Node) list.get(k))) {
                                                to = lookbehindLength;
                                                if (isGreedyNode((Node) list.get(k - 1)) != 0) {
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        to = lookbehindLength;
                                        textLength += ((Node) list.get(k)).textLength();
                                        if (from >= textLength) {
                                            position++;
                                            greedyAdd3 = textLength;
                                        }
                                        k++;
                                        lookbehindLength = to;
                                    }
                                    to = lookbehindLength;
                                    if (isSyntaxNode((Node) list.get(position)) != 0) {
                                        i2 = position;
                                        position = greedyAdd3;
                                        regex = regex2;
                                        it2 = it;
                                        it4 = it3;
                                        pattern = pattern2;
                                        lookbehindLength = lookbehindLength2;
                                        i3 = 1;
                                    } else {
                                        lookbehindLength = k - position;
                                        str3 = str.substring(greedyAdd3, textLength);
                                        textLength = lookbehindLength;
                                        greedyMatch = true;
                                        i2 = position;
                                        str2 = str3;
                                        position = greedyAdd3;
                                        Matcher matcher3 = matcher2;
                                        greedyAdd = -greedyAdd3;
                                        matcher = matcher3;
                                    }
                                }
                                if (greedyMatch || matcher.find()) {
                                    int lookbehindLength3;
                                    String substring;
                                    int position2;
                                    int i22;
                                    List<? extends Node> tokenEntries;
                                    if (lookbehind) {
                                        String group = matcher.group(1);
                                        lookbehindLength3 = group != null ? group.length() : 0;
                                    } else {
                                        lookbehindLength3 = lookbehindLength2;
                                    }
                                    lookbehindLength = (matcher.start() + greedyAdd) + lookbehindLength3;
                                    if (lookbehindLength3 > 0) {
                                        substring = matcher.group().substring(lookbehindLength3);
                                    } else {
                                        substring = matcher.group();
                                    }
                                    it2 = it;
                                    int to2 = lookbehindLength + substring.length();
                                    i = lookbehindLength3;
                                    for (lookbehindLength3 = 0; lookbehindLength3 < textLength; lookbehindLength3++) {
                                        list.remove(i2);
                                    }
                                    lookbehindLength3 = i2;
                                    if (lookbehindLength != 0) {
                                        regex = regex2;
                                        String before = str2.substring(null, lookbehindLength);
                                        i2++;
                                        position += before.length();
                                        lookbehindLength2 = lookbehindLength3 + 1;
                                        list.add(lookbehindLength3, new TextImpl(before));
                                        position2 = position;
                                        i22 = lookbehindLength2;
                                        lookbehindLength2 = i2;
                                    } else {
                                        regex = regex2;
                                        lookbehindLength2 = i2;
                                        position2 = position;
                                        i22 = lookbehindLength3;
                                    }
                                    Grammar inside = pattern2.inside();
                                    boolean hasInside = inside != null;
                                    if (hasInside) {
                                        tokenEntries = tokenize(substring, inside);
                                    } else {
                                        tokenEntries = Collections.singletonList(new TextImpl(substring));
                                    }
                                    SyntaxImpl syntaxImpl = r0;
                                    it4 = it3;
                                    int i23 = i22 + 1;
                                    String match = substring;
                                    String str4 = str2;
                                    pattern = pattern2;
                                    i3 = 1;
                                    SyntaxImpl syntaxImpl2 = new SyntaxImpl(token.name(), tokenEntries, pattern2.alias(), substring, greedy, hasInside);
                                    list.add(i22, syntaxImpl);
                                    if (to2 < str4.length()) {
                                        list.add(i23, new TextImpl(str4.substring(to2)));
                                    }
                                    if (textLength != i3) {
                                        matchGrammar(text, entries, grammar, lookbehindLength2, position2, true, token);
                                    }
                                    if (oneShot) {
                                        break;
                                    }
                                    i2 = lookbehindLength2;
                                    lookbehindLength = i;
                                    position = position2;
                                } else if (oneShot) {
                                    it2 = it;
                                    it4 = it3;
                                    break;
                                } else {
                                    regex = regex2;
                                    it2 = it;
                                    it4 = it3;
                                    pattern = pattern2;
                                    lookbehindLength = lookbehindLength2;
                                    i3 = 1;
                                }
                            }
                            position += ((Node) list.get(i2)).textLength();
                            i2 += i3;
                            token2 = target;
                            regex2 = regex;
                            textLength = textLength2;
                            it = it2;
                            it3 = it4;
                            pattern2 = pattern;
                        } else {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Prism4j internal error. Number of entry nodes is greater that the text length.\nNodes: ");
                            stringBuilder.append(list);
                            stringBuilder.append("\nText: ");
                            stringBuilder.append(str);
                            throw new RuntimeException(stringBuilder.toString());
                        }
                    }
                    i = i2;
                    regex = regex2;
                    textLength2 = textLength;
                    it2 = it;
                    it4 = it3;
                    pattern = pattern2;
                    token2 = target;
                    textLength = textLength2;
                    it = it2;
                    it3 = it4;
                }
                it2 = it;
            } else {
                return;
            }
        }
    }

    private static boolean isSyntaxNode(@NonNull Node node) {
        return node.isSyntax();
    }

    private static boolean isGreedyNode(@NonNull Node node) {
        return node.isSyntax() && ((Syntax) node).greedy();
    }
}
