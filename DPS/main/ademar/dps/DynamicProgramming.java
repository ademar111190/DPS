package ademar.dps;

/**
 * The contract that Dynamic Programming implementations should use to Searcher use them
 */
public interface DynamicProgramming {

    /**
     * executes the Dynamic Programming alignment, it should receives 2 NonNulls CharSequences
     * the lhs and rhs and return a score based on implementation rules
     *
     * @param lhs a NonNull CharSequence to be aligned with rhs, cannot be null
     * @param rhs a NonNull CharSequence to be aligned with lhs, cannot be null
     * @return the global sum
     */
    int execute(CharSequence lhs, CharSequence rhs);

}
