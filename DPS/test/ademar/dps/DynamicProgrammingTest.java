package ademar.dps;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * + Match ( add match value )
 * - Gap ( add gap value )
 * Mismatch ( add mismatch value )
 * • Ignore ( add zero )
 */
public class DynamicProgrammingTest {

    private static final String DYNAMIC_PROGRAMMING_BASE = "Oliveira";
    private static final String DYNAMIC_PROGRAMMING_LOCAL = "ivei";
    private static final String DYNAMIC_PROGRAMMING_GAPED = "Olira";
    private static final String DYNAMIC_PROGRAMMING_REPLACED = "Oliweyra";
    private static final String DYNAMIC_PROGRAMMING_ARM = "All";

    /**
     * Oliveira
     * Oliveira
     * ++++++++
     * 8 matches * 3 points = 24
     */
    @Test
    public void testGlobal_match() {
        int result = DynamicProgramming.global(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_BASE, 3, 0, 0);
        assertThat(result).isEqualTo(24);
    }

    /**
     * Oliveira
     * Oli___ra
     * +++---++
     * (5 matches * 2 points) + (3 gaps * -1 points) = 7
     */
    @Test
    public void testGlobal_gap() {
        int result = DynamicProgramming.global(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_GAPED);
        assertThat(result).isEqualTo(7);
    }

    /**
     * Oliveira
     * Oliweyra
     * +++*+*++
     * (6 matches * 2 points) + (2 mismatches * -1 points) = 10
     */
    @Test
    public void testGlobal_replace() {
        int result = DynamicProgramming.global(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_REPLACED);
        assertThat(result).isEqualTo(10);
    }

    /**
     * Oliveira
     * All_____
     * *+-----
     * (1 matches * 2 points) + (2 mismatches * -1 points) + (5 gaps * -1 points) = -5
     */
    @Test
    public void testGlobal_gapedReplace() {
        int result = DynamicProgramming.global(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_ARM);
        assertThat(result).isEqualTo(-5);
    }

    /**
     * Oliveira
     * Oliveira
     * ++++++++
     * 8 matches * 3 points = 24
     */
    @Test
    public void testLocal_match() {
        int result = DynamicProgramming.local(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_BASE, 3, 0, 0);
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
        int result = DynamicProgramming.local(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_LOCAL);
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
        int result = DynamicProgramming.local(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_GAPED);
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
        int result = DynamicProgramming.local(DYNAMIC_PROGRAMMING_BASE, DYNAMIC_PROGRAMMING_REPLACED);
        assertThat(result).isEqualTo(10);
    }

}
