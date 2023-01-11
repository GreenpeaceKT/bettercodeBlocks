package io.noties.prism4j.languages;

import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.compile;
import static io.noties.prism4j.Prism4j.grammar;
import static io.noties.prism4j.Prism4j.pattern;
import static io.noties.prism4j.Prism4j.token;

import androidx.annotation.NonNull;

import io.noties.prism4j.Prism4j;

@SuppressWarnings("unused")
public class Prism_diff {

  @NonNull
  public static Prism4j.Grammar create(@NonNull Prism4j prism4j) {
    return grammar("diff",
      token("comment", pattern(compile("^#.*", MULTILINE))),
      token("deleted", pattern(compile("^[-–].*", MULTILINE))),
      token("inserted", pattern(compile("^\\+.*", MULTILINE))),
      token("string", pattern(compile("(\"|')(?:\\\\.|(?!\\1)[^\\\\\\r\\n])*\\1", MULTILINE))),
      token("command", pattern(
        compile("^.*\\$ diff .*$", MULTILINE),
        false,
        false,
        null,
        grammar("inside",
          token("parameter", pattern(compile("\\s--?\\w+", MULTILINE)))
        )
      )),
      token("coord", pattern(compile("^@@.*@@$", MULTILINE))),
      token("commit_sha1", pattern(compile("^commit \\w{40}$", MULTILINE)))
    );
  }
}