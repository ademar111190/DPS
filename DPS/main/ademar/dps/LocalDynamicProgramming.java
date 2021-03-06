package ademar.dps;

/**
 * Executes the Local Dynamic Programming algorithm
 */
public class LocalDynamicProgramming implements DynamicProgramming {

    public static final int DEFAULT_MATCH = 2;
    public static final int DEFAULT_MISMATCH = -1;
    public static final int DEFAULT_GAP = -1;

    private final int match;
    private final int mismatch;
    private final int gap;

    /**
     * Just call the main constructor with the defaults values
     */
    public LocalDynamicProgramming() {
        this(DEFAULT_MATCH, DEFAULT_MISMATCH, DEFAULT_GAP);
    }

    /**
     * @param match    The value to sum for every match
     * @param mismatch The value to sum for every mismatch
     * @param gap      The value to sum for every gap
     */
    public LocalDynamicProgramming(int match, int mismatch, int gap) {
        this.match = match;
        this.mismatch = mismatch;
        this.gap = gap;
    }

    /**
     * Local alignment the lhs and rhs CharSequences and use the match, mismatch and gap
     * class arguments to calculate the score and returns it
     *
     * @param lhs The CharSequence to be aligned with rhs, cannot be null
     * @param rhs The CharSequence to be aligned with lhs, cannot be null
     * @return the grater local sum
     */
    @Override
    public int execute(CharSequence lhs, CharSequence rhs) {
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
