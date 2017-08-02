package ademar.dps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearcherTest {

    private static final String SEARCHER_TARGET = "Some Name";
    private static final String SEARCHER_TARGET_SIMILAR = "Some Surname";
    private static final String SEARCHER_TARGET_INCOMPLETE = "Some Na";
    private static final String[] SEARCHER_WRONG = new String[]{"Another Name", "Flame", "Nothing todo"};

    private Foo fooTarget;
    private Searcher.Builder<Foo> builder;

    @Before
    public void setUp() {
        builder = new Searcher.Builder<Foo>();
        builder.global(); // Add global algorithm
        builder.local(); // Add local algorithm
        for (String characteristics : SEARCHER_WRONG) {
            builder.searchable(new Foo(), characteristics);
        }
        fooTarget = new Foo();
        builder.searchable(fooTarget, SEARCHER_TARGET);
    }

    @After
    public void tearDown() {
        fooTarget = null;
        builder = null;
    }

    @Test
    public void testSearch_complete() {
        List<Foo> result = builder.build().search(SEARCHER_TARGET);
        assertThat(result.get(0)).isEqualTo(fooTarget);
    }

    @Test
    public void testSearch_similar() {
        builder.threshold(.4);
        List<Foo> result = builder.build().search(SEARCHER_TARGET_SIMILAR);
        assertThat(result.get(0)).isEqualTo(fooTarget);
    }

    @Test
    public void testSearch_incomplete() {
        List<Foo> result = builder.build().search(SEARCHER_TARGET_INCOMPLETE);
        assertThat(result.get(0)).isEqualTo(fooTarget);
    }

    private static class Foo {
    }

}
