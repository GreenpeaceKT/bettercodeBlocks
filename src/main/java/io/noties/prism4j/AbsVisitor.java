package io.noties.prism4j;

import androidx.annotation.NonNull;
import io.noties.prism4j.Prism4j.Node;
import io.noties.prism4j.Prism4j.Syntax;
import io.noties.prism4j.Prism4j.Text;
import io.noties.prism4j.Prism4j.Visitor;
import java.util.List;

public abstract class AbsVisitor implements Visitor {
    public abstract void visitSyntax(@NonNull Syntax syntax);

    public abstract void visitText(@NonNull Text text);

    public void visit(@NonNull List<? extends Node> nodes) {
        for (Node node : nodes) {
            if (node.isSyntax()) {
                visitSyntax((Syntax) node);
            } else {
                visitText((Text) node);
            }
        }
    }
}
