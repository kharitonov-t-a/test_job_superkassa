public class Main {

    private static final String FILENAMEIN = "./input.json";
    private static final String FILENAMEOUT = "./output.json";

    public static void main(String[] args) {

        Trip trip = new Trip(JsonWriterReader.readJsonFile(FILENAMEIN));
        JsonWriterReader.writeJsonFile(FILENAMEOUT, trip.generateFlightsSplices());

    }

}
