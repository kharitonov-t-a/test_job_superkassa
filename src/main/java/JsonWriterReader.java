import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for reading and writing json file <br>
 * Contains methods: <br>
 * {@link JsonWriterReader#readJsonFile(String)} <br>
 * {@link JsonWriterReader#writeJsonFile(String, List)}
 */
public class JsonWriterReader {

    /**
     * Read from json file some specific data
     * @param fileName name of json readable file
     * @param <T> everything you can get from json
     * @return specific data for {@link Trip#flightsParts}
     */
    public static <T> List<List<T>> readJsonFile(String fileName) {
        JSONParser parser = new JSONParser();
        List<List<T>> rows = new ArrayList<>();

        try {
            JSONArray messages = (JSONArray) parser.parse(
                    new FileReader(fileName));

            Iterator<List<T>> iterator = messages.listIterator();
            while (iterator.hasNext()) {
                rows.add(iterator.next());
//                writeJsonFile"> " + rows.get(rows.size() - 1));
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JsonWriterReader.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return rows;
    }

    /**
     * Write in json file some specific data <br>
     * If file is not exist, it will be created
     * @param fileName name of json written file
     * @param rows specific data from {@link Trip#generateFlightsSplices()}
     * @param <T> everything you can get from json
     */
    public static <T> void writeJsonFile(String fileName, List<List<T>> rows) {
        JSONObject object = new JSONObject();
        JSONArray messages = new JSONArray();

        messages.addAll(rows);

        object.put(null, messages);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(object.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(JsonWriterReader.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
