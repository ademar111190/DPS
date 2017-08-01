package ademar.dps;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

/**
 * The class that uses Dynamic Programming to make strings matches, rank it, and using a threshold provides a
 * searcher result
 *
 * @param <T> The class type you want to search
 */
public class Searcher<T> {

    private final List<CharSequence> characteristics;
    private final List<T> searchables;
    private final double threshold;
    private final boolean normalized;

    private Searcher(List<CharSequence> characteristics, List<T> searchables, double threshold, boolean normalized) {
        this.characteristics = characteristics;
        this.searchables = searchables;
        this.threshold = threshold;
        this.normalized = normalized;
    }

    /**
     * Search on your data using your query
     *
     * @param query the query to match on your data and score it
     * @return a list of elements that has at least the minimum allowed score,
     * the firs element has the best score. If the query is empty or null it returns an empty list
     */
    public List<T> search(CharSequence query) {
        if (normalized) {
            query = normalize(query);
        }
        if (query != null && query.length() > 0) {
            return execute(query);
        } else {
            return Collections.emptyList();
        }
    }

    private List<T> execute(CharSequence query) {
        // Collect the scores for each characteristics in both possibilities local and global
        Map<T, List<Integer>> scores = new HashMap<T, List<Integer>>();
        List<Integer> searchableScores;
        for (int i = 0; i < characteristics.size(); i++) {
            T hunting = searchables.get(i);
            searchableScores = scores.get(hunting);
            if (searchableScores == null) {
                searchableScores = new ArrayList<Integer>();
            }
            searchableScores.add(DynamicProgramming.local(characteristics.get(i), query));
            searchableScores.add(DynamicProgramming.global(characteristics.get(i), query));
            scores.put(hunting, searchableScores);
        }

        // Order the scores of each searchable
        for (T searchable : scores.keySet()) {
            Collections.sort(scores.get(searchable), new Comparator<Integer>() {
                @Override
                public int compare(Integer lhs, Integer rhs) {
                    return rhs.compareTo(lhs);
                }
            });
        }

        // Order the searchables by its scores
        List<Map.Entry<T, List<Integer>>> list = new ArrayList<Map.Entry<T, List<Integer>>>(scores.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<T, List<Integer>>>() {
            public int compare(Map.Entry<T, List<Integer>> left, Map.Entry<T, List<Integer>> right) {
                List<Integer> leftList = left.getValue();
                List<Integer> rightList = right.getValue();
                int lhs, rhs, size = Math.min(leftList.size(), rightList.size());
                for (int i = 0; i < size; i++) {
                    lhs = leftList.get(i);
                    rhs = rightList.get(i);
                    if (lhs < rhs) return +1;
                    if (lhs > rhs) return -1;
                }
                return leftList.size() < rightList.size() ? -1 : (leftList.size() == rightList.size() ? 0 : 1);
            }
        });

        // Verify the minimum allowed score using the threshold and match points
        double bestScore = DynamicProgramming.DEFAULT_MATCH * query.length();
        double minAllowedScore = bestScore * threshold;

        // Add every searchable that has enough point to result list
        List<T> found = new ArrayList<T>();
        for (Map.Entry<T, List<Integer>> item : list) {
            if (item.getValue().get(0) >= minAllowedScore) {
                found.add(item.getKey());
            }
        }

        return found;
    }

    /**
     * The class to build a Searcher instance
     *
     * @param <T> The class type you want to search
     */
    public static class Builder<T> {

        private List<CharSequence> characteristics = new ArrayList<CharSequence>();
        private List<T> searchables = new ArrayList<T>();
        private double threshold = 0.5;
        private boolean normalized = true;

        /**
         * Add an objects of type T to be matched by a characteristic. If searchable is null or characteristic is null it is not added
         *
         * @param searchable     the object that will be ranked and eventually returned when the search is executed
         * @param characteristic the value to be matched with your search query
         */
        public void searchable(T searchable, CharSequence characteristic) {
            if (searchable != null && characteristic != null) {
                searchables.add(searchable);
                characteristics.add(characteristic);
            }
        }

        /**
         * The threshold to be used on search.
         * Suppose a query "Hello World" it has 11 characters, and as far as the Searches uses the defaults
         * DynamicProgramming values, and the default value to a match is 2, the maximum score is 11 * 2 = 22.
         * Suppose a threshold as 0.3, it means that the minimum allowed score will be 22 * 0.3 = 6,6. It means
         * that any searchable with 6.6 or more will be added into your List result.
         * Corollary: if you pass 0.0 it means all searchables will returns even with no match, you are only ordering it
         * Corollary: if you pass 1.0 it means only exactly matches will returns
         *
         * @param threshold The value to filter the searchables, if it is less than 0.0 it is converted to 0.0, if it is
         *                  greater than 1.0 it is converted to 1.0
         * @return The Builder instance
         */
        public Builder<T> threshold(double threshold) {
            if (threshold < 0.0) {
                threshold = 0.0;
            } else if (threshold > 1.0) {
                threshold = 1.0;
            }
            this.threshold = threshold;
            return this;
        }

        /**
         * If true remove all accents, turn all characters as lower case and trim the string, otherwise change nothing
         * e.g. : The follow we have original strings and normalized strings
         * "São Paulo" -> "sao paulo"
         * " s p a c e d " -> "s p a c e d"
         * "" -> ""
         * "a1b2c3!!!" -> "a1b2c3!!!"
         *
         * @param normalized true if should normalize the string false otherwise
         * @return The Builder instance
         */
        public Builder<T> setNormalized(boolean normalized) {
            this.normalized = normalized;
            return this;
        }

        /**
         * Create a Searcher instance with the current builder values
         *
         * @return the Searcher instance
         */
        public Searcher<T> build() {
            if (normalized) {
                for (int i = 0; i < characteristics.size(); i++) {
                    characteristics.add(i, normalize(characteristics.remove(i)));
                }
            }
            return new Searcher<T>(characteristics, searchables, threshold, normalized);
        }

    }

    private static CharSequence normalize(CharSequence charSequence) {
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
