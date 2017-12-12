package ademar.dps;

import java.text.Normalizer;
import java.util.regex.Pattern;

public final class CharSequenceExt {

    private CharSequenceExt() {
    }

    /* package */ static CharSequence normalize(CharSequence charSequence) {
        if (charSequence == null) {
            return "";
        } else {
            return Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
                    .matcher(Normalizer.normalize(charSequence, Normalizer.Form.NFD))
                    .replaceAll("")
                    .toLowerCase()
                    .trim();
        }
    }

}
