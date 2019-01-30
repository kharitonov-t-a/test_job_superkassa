/**
 * Class for generateFlightsSplices K-combinations <br>
 * Contains methods: <br>
 * {@link KCombinations#generate(int[], int, int)}
 */
public class KCombinations {

    /**
     * Generation combination of n by k based on previous combination <br>
     * @param arr array for combination, starts from null
     * @param N count elements for combination
     * @param K count necessary elements in combination
     * @return array containing combination
     */
    public static int[] generate(int[] arr, int N, int K) {
        if (arr == null) {
            arr = new int[K];
            for (int i = 0; i < K; i++)
                arr[i] = i + 1;
            return arr;
        }
        for (int i = K - 1; i >= 0; i--)
            if (arr[i] < N - K + i + 1) {
                arr[i]++;
                for (int j = i; j < K - 1; j++)
                    arr[j + 1] = arr[j] + 1;
                return arr;
            }
        return null;
    }
}