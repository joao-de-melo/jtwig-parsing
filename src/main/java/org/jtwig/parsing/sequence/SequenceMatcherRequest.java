package org.jtwig.parsing.sequence;

import org.jtwig.parsing.character.EndOfInputCharacterMatcher;
import org.jtwig.parsing.tree.TextNode;

public class SequenceMatcherRequest {
    private final char[] content;
    private final int offset;

    public SequenceMatcherRequest(char[] content, int offset) {
        this.content = content;
        this.offset = offset;
    }

    public SequenceMatcherRequest incrementOffset (int offset) {
        return new SequenceMatcherRequest(content, this.offset + offset);
    }

    public char getCharacter(int index) {
        if (index + offset >= content.length) return (char) -1;
        return content[index + offset];
    }

    public char getCurrentCharacter() {
        if (offset >= content.length) return EndOfInputCharacterMatcher.EOI;
        return content[offset];
    }

    public boolean isEndOfInput() {
        return getCurrentCharacter() == EndOfInputCharacterMatcher.EOI;
    }

    public MatchResult text(int jump) {
        return new MatchResult(offset, offset + jump, new TextNode(content, offset, offset + jump));
    }

    public int getOffset() {
        return offset;
    }
}
