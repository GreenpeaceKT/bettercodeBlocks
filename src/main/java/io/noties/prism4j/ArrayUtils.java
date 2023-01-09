package io.noties.prism4j;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class ArrayUtils {
    @NonNull
    @SafeVarargs
    static <T> List<T> toList(T... args) {
        int length;
        if (args != null) {
            length = args.length;
        } else {
            length = 0;
        }
        List<T> list = new ArrayList(length);
        if (length > 0) {
            Collections.addAll(list, args);
        }
        return list;
    }

    private ArrayUtils() {
    }
}
