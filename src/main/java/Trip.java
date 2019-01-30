import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating flights splices <br>
 * Contains methods: <br>
 * {@link Trip#generateFlightsSplices()} <br>
 * {@link Trip#getFlightsParts()} <br>
 * {@link Trip#setFlightsParts(List)} <br>
 */
public class Trip<E> {

    /**
     * Pieces of flights for concatenation
     */
    private List<List<E>> flightsParts;
    /**
     * Count of pieces of flights
     */
    private Integer partsCount = 0;

    public Trip(List<List<E>> flightsParts) {
        this.flightsParts = new ArrayList<>(flightsParts);
        if (!flightsParts.isEmpty())
            this.partsCount = flightsParts.get(0).size();
    }

    /**
     * Get pieces of flights for concatenation
     * @return list of flights splices
     */
    public List<List<E>> getFlightsParts() {
        return new ArrayList<>(flightsParts);
    }

    /**
     * Set new pieces of flights for concatenation
     * @param flightsParts list of pieces of flights for concatenation
     */
    public void setFlightsParts(List<List<E>> flightsParts) {
        this.flightsParts = new ArrayList<>(flightsParts);
        if (!flightsParts.isEmpty())
            this.partsCount = flightsParts.get(0).size();
    }

    /**
     * Generate all combinations of flights splices
     * @return list of flights splices
     */
    public List<List<E>> generateFlightsSplices() {

        List<List<E>> totalFlights = new ArrayList<>();

//       iterate every pieces of flights
        for (int i = 0, flightsPartsSize = flightsParts.size(); i < flightsPartsSize; ++i) {

            List<List<E>> subFlightsParts = new ArrayList<>(flightsParts.subList(i, flightsPartsSize));

//            get count of passes (null)
            int countNull = 0;
            for (int l = 0; l < partsCount; ++l) {

                if (flightsParts.get(i).get(l) != null) {
                    int finalL = l;
                    subFlightsParts.removeIf(strings -> strings.get(finalL) != null);
                } else {
                    countNull++;
                }

            }

//            count of possible pieces for concatenation - count of passes (null)
            if (countNull == 0)
                totalFlights.addAll(generateByCombinationsCount(subFlightsParts, flightsParts.get(i), 0));
            for (int l = 1; l <= countNull; ++l) {
                totalFlights.addAll(generateByCombinationsCount(subFlightsParts, flightsParts.get(i), l));
            }


        }


        return totalFlights;
    }

    /**
     * Generate combinations flights splices by count of pieces of flights
     * @param subFlightsParts pieces of flights for concatenation
     * @param firstFlightsPart basic piece of flights
     * @param combinationsCount count of pieces necessary to concatenation
     * @return list of flights splices
     */
    private List<List<E>> generateByCombinationsCount(List<List<E>> subFlightsParts, List<E> firstFlightsPart, int combinationsCount) {

        int elementsCount = subFlightsParts.size();

        List<E> combination = new ArrayList<>(firstFlightsPart);
        List<List<E>> totalCombinations = new ArrayList<>();

//        if count of elements not enough for concatenation at least one combination
        if (elementsCount < combinationsCount)
            return totalCombinations;

        boolean flagSuccessfulCombination = true;
        int[] arr = null;
        while ((arr = KCombinations.generate(arr, elementsCount, combinationsCount)) != null) {

            for (int i = 0; i < combinationsCount; i++) {

                if (flagSuccessfulCombination == true) {
                    for (int l = 0; l < partsCount; ++l) {

                        if (combination.get(l) == null && subFlightsParts.get(arr[i] - 1).get(l) != null) {
                            combination.set(l, subFlightsParts.get(arr[i] - 1).get(l));
                        } else if (combination.get(l) != null && subFlightsParts.get(arr[i] - 1).get(l) != null) {
                            flagSuccessfulCombination = false;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }

            if (flagSuccessfulCombination == true && isSuccessfulCombination(combination))
                totalCombinations.add(combination);

            flagSuccessfulCombination = true;
            combination = new ArrayList<>(firstFlightsPart);
        }

        return totalCombinations;
    }

    /**
     * Check integrity of flights splice
     * @param combination flights splice
     * @return if all successfully - true, else - false
     */
    private boolean isSuccessfulCombination(List<E> combination) {

        for (E str : combination) {
            if (str == null) return false;
        }
        return true;
    }
}
