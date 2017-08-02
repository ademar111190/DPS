package ademar.dps;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * + Match ( add match value )
 * - Gap ( add gap value )
 * * Mismatch ( add mismatch value )
 * • Ignore ( add zero )
 */
public class LocalDynamicProgrammingTest {

    private static final String DYNAMIC_PROGRAMMING_BASE = "Oliveira";
    private static final String DYNAMIC_PROGRAMMING_LOCAL = "ivei";
    private static final String DYNAMIC_PROGRAMMING_GAPED = "Olira";
    private static final String DYNAMIC_PROGRAMMING_REPLACED = "Oliweyra";

    /**
     * Oliveira
     * Oliveira
     * ++++++++
     * 8 matches * 3 points = 24
     */
    @Test
    public void testLocal_match() {
        int result = new LocalDynamicProgramming(3, 0, 0).execute(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_BASE);
        assertThat(result).isEqualTo(24);
    }

    /**
     * Oliveira
     * __ivei__
     * ••++++••
     * (4 matches * 2 points) + (4 ignores) = 8
     */
    @Test
    public void testLocal_partialMatch() {
        int result = new LocalDynamicProgramming().execute(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_LOCAL);
        assertThat(result).isEqualTo(8);
    }

    /**
     * Oliveira
     * Oli___ra
     * +++---++
     * (5 matches * 2 points) + (3 gaps * -1 points) = 7
     */
    @Test
    public void testLocal_gap() {
        int result = new LocalDynamicProgramming().execute(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_GAPED);
        assertThat(result).isEqualTo(7);
    }

    /**
     * Oliveira
     * Oliweyra
     * +++*+*++
     * (6 matches * 2 points) + (2 mismatches * -1 points) = 10
     */
    @Test
    public void testLocal_replace() {
        int result = new LocalDynamicProgramming().execute(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_REPLACED);
        assertThat(result).isEqualTo(10);
    }

}
