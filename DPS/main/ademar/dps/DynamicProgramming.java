package ademar.dps;

/**
 * Holds the Dynamic Programming methods, all methods are statics and this class cannot be instantiated
 */
public final class DynamicProgramming {

    public static final int DEFAULT_MATCH = 2;
    public static final int DEFAULT_MISMATCH = -1;
    public static final int DEFAULT_GAP = -1;

    private DynamicProgramming() {
    }

    /**
     * Global alignment the lhs and rhs CharSequences and use 2 as match value, -1 as mismatch
     * value and -1 as gap value to calculate the score and returns it
     *
     * @param lhs The CharSequence to be aligned with rhs, cannot be null
     * @param rhs The CharSequence to be aligned with lhs, cannot be null
     * @return the global sum
     */
    public static int global(CharSequence lhs, CharSequence rhs) {
        return global(lhs, rhs, DEFAULT_MATCH, DEFAULT_MISMATCH, DEFAULT_GAP);
    }

    /**
     * Global alignment the lhs and rhs CharSequences and use the match, mismatch and gap
     * arguments to calculate the score and returns it
     *
     * @param lhs      The CharSequence to be aligned with rhs, cannot be null
     * @param rhs      The CharSequence to be aligned with lhs, cannot be null
     * @param match    The value to sum for every match
     * @param mismatch The value to sum for every mismatch
     * @param gap      The value to sum for every gap
     * @return the global sum
     */
    public static int global(CharSequence lhs, CharSequence rhs, int match, int mismatch, int gap) {
        int leftSize = lhs.length() + 1;
        int rightSize = rhs.length() + 1;
        int table[][] = new int[leftSize][rightSize];
        for (int l = 0; l < leftSize; l++) {
            for (int r = 0; r < rightSize; r++) {
                if (l == 0 && r == 0) {
                    table[l][r] = 0;
                } else if (l == 0) {
                    table[l][r] = table[l][r - 1] + gap;
                } else if (r == 0) {
                    table[l][r] = table[l - 1][r] + gap;
                } else {
                    table[l][r] = Math.max(
                            table[l - 1][r - 1] + (lhs.charAt(l - 1) == rhs.charAt(r - 1) ? match : mismatch),
                            Math.max(table[l - 1][r] + gap, table[l][r - 1] + gap));
                }
            }
        }
        return table[lhs.length()][rhs.length()];
    }

    /**
     * Local alignment the lhs and rhs CharSequences and use 2 as match value, -1 as mismatch
     * value and -1 as gap value to calculate the score and returns it
     *
     * @param lhs The CharSequence to be aligned with rhs, cannot be null
     * @param rhs The CharSequence to be aligned with lhs, cannot be null
     * @return the grater local sum
     */
    public static int local(CharSequence lhs, CharSequence rhs) {
        return local(lhs, rhs, DEFAULT_MATCH, DEFAULT_MISMATCH, DEFAULT_GAP);
    }

    /**
     * Local alignment the lhs and rhs CharSequences and use the match, mismatch and gap
     * arguments to calculate the score and returns it
     *
     * @param lhs      The CharSequence to be aligned with rhs, cannot be null
     * @param rhs      The CharSequence to be aligned with lhs, cannot be null
     * @param match    The value to sum for every match
     * @param mismatch The value to sum for every mismatch
     * @param gap      The value to sum for every gap
     * @return the grater local sum
     */
    public static int local(CharSequence lhs, CharSequence rhs, int match, int mismatch, int gap) {
        int leftSize = lhs.length() + 1;
        int rightSize = rhs.length() + 1;
        int table[][] = new int[leftSize][rightSize];
        for (int l = 0; l < leftSize; l++) {
            for (int r = 0; r < rightSize; r++) {
                if (l == 0 && r == 0) {
                    table[l][r] = 0;
                } else if (l == 0) {
                    table[l][r] = Math.max(0, table[l][r - 1] + gap);
                } else if (r == 0) {
                    table[l][r] = Math.max(0, table[l - 1][r] + gap);
                } else {
                    table[l][r] = Math.max(0, Math.max(
                            table[l - 1][r - 1] + (lhs.charAt(l - 1) == rhs.charAt(r - 1) ? match : mismatch),
                            Math.max(table[l - 1][r] + gap, table[l][r - 1] + gap)));
                }
            }
        }
        int maxScore = Integer.MIN_VALUE;
        for (int l = 0; l < leftSize; l++) {
            for (int r = 0; r < rightSize; r++) {
                maxScore = Math.max(maxScore, table[l][r]);
            }
        }
        return maxScore;
    }

}
