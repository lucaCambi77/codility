package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InterviewBitStringTest {

    @Test
    public void fullJustify() {
        assertArrayEquals(new String[]{"This    is    an",
                "example  of text",
                "justification.  "}, fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));

        assertArrayEquals(new String[]{}, fullJustify(new String[]{}, 10));
        assertArrayEquals(new String[]{"What must be", "shall be.   "}, fullJustify(new String[]{"What", "must", "be", "shall", "be."}, 12));
        assertArrayEquals(new String[]{"glu  muskzjyen  ahxkp  t  djmgzzyh  jzudvh  raji  vmipiz  sg rv mekoexzfmq fsrihvdnt yvnppem gidia fxjlzekp uvdaj ua pzagn bjffryz nkdd osrownxj"
                        , "fvluvpdj  kkrpr  khp  eef aogrl gqfwfnaen qhujt vabjsmj ji f opihimudj awi jyjlyfavbg tqxupaaknt dvqxay ny ezxsvmqk ncsckq nzlce cxzdirg dnmaxql", "bhrgyuyc qtqt yka wkjriv xyfoxfcqzb fttsfs m                                                                                                    "},
                fullJustify(new String[]{"glu", "muskzjyen", "ahxkp", "t", "djmgzzyh", "jzudvh", "raji", "vmipiz", "sg", "rv", "mekoexzfmq", "fsrihvdnt", "yvnppem", "gidia", "fxjlzekp", "uvdaj", "ua", "pzagn", "bjffryz", "nkdd", "osrownxj", "fvluvpdj", "kkrpr", "khp", "eef", "aogrl", "gqfwfnaen", "qhujt", "vabjsmj", "ji", "f", "opihimudj", "awi", "jyjlyfavbg", "tqxupaaknt", "dvqxay", "ny", "ezxsvmqk", "ncsckq", "nzlce", "cxzdirg", "dnmaxql", "bhrgyuyc", "qtqt", "yka", "wkjriv", "xyfoxfcqzb", "fttsfs", "m"}, 144));

    }

    public String[] fullJustify(String[] A, int B) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0, w; i < A.length; i = w) {
            int l = -1;
            for (w = i; w < A.length && A[w].length() + 1 + l <= B; w++)
                l += A[w].length() + 1;
            StringBuilder sb = new StringBuilder(A[i]);
            int spaces = 1, extra = 0;
            if (w != i + 1 && w != A.length) {
                spaces = (B - l) / (w - i - 1) + 1;
                extra = (B - l) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = 0; s < spaces; s++)
                    sb.append(' ');
                if (extra-- > 0)
                    sb.append(' ');
                sb.append(A[j]);
            }
            int remaining = B - sb.length();
            while (remaining-- > 0) sb.append(' ');
            list.add(sb.toString());
        }
        return list.stream().toArray(String[]::new);
    }
}

