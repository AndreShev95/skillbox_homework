import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

@SuppressWarnings("unchecked")


public class Main {
    public static final String PATH_SITE = "https://www.moscowmap.ru/metro.html#lines";
    public static final String PATH_FILE = "E:/Java/java_basics/11_FilesAndNetwork/homework_11.5/resources/map.json";

    public static TreeMap<String, String> mapLines = new TreeMap<>();
    public static JSONObject rootObject = new JSONObject();
    public static String numStationOnLine;
    public static HashSet <String> namesAllStations = new HashSet<>();
    public static boolean repeat = false;

    public static void main(String[] args) {
        System.out.println("Программа проводит расчет количества станций на каждой линии Московского метрополитена.");
        System.out.println("И также программа высчитывает количество переходов в метро.");
        try {
            writeFile();
            printResult();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void writeFile(){
        try{
            Document document = Jsoup.connect(PATH_SITE).maxBodySize(0).get();
            writeLines(document);
            writeStations(document);
            writeConnections(document);
            String total = changeFormat(rootObject);
            try (FileWriter file = new FileWriter(PATH_FILE)) {
                file.write(total);
                file.flush();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void writeLines(Document document){
        JSONArray arrayLines = new JSONArray();
        Elements lines = document.select("span[data-line]");
        lines.forEach(line ->{
            JSONObject lin = new JSONObject();
            lin.put("number", line.attr("data-line"));
            lin.put("name", line.text());
            arrayLines.add(lin);
        });
        rootObject.put("lines", arrayLines);
    }

    private static void writeStations(Document document){
        JSONObject stations = new JSONObject();
        Elements allStations = document.select("div[data-line]");
        allStations.forEach(station -> {
            String numberLine = station.attr("data-line");
            JSONArray stationsOnOneLine = new JSONArray();
            Elements nameStations = station.select("span.name");
            nameStations.forEach(nameStation -> stationsOnOneLine.add(nameStation.text()));
            stations.put(numberLine, stationsOnOneLine);
        });
        rootObject.put("stations", stations);
    }

    private static void writeConnections(Document document){
        JSONArray arrayConnections = new JSONArray();
        Elements connections = document.select("a[data-metrost]:has(span[title]) > span");
        JSONArray arrayConnection = new JSONArray();
        connections.forEach(connection -> {
            if (connection.attr("class").matches("num")){
                numStationOnLine = connection.text();
                if (!arrayConnection.isEmpty()) {
                    arrayConnections.add(arrayConnection.clone());
                    arrayConnection.clear();
                }
            }
            else if (connection.attr("class").matches("name")){
                String nameStation = connection.text();
                if (!repeatStation(nameStation)) {
                    repeat = false;
                    namesAllStations.add(nameStation);
                    JSONObject connect = new JSONObject();
                    String string = numStationOnLine + " " + nameStation;
                    Elements numberLines = document.select("div[data-line]:contains(" + string + ")");
                    connect.put("line", numberLines.attr("data-line"));
                    connect.put("station", nameStation);
                    arrayConnection.add(connect);
                }
                else repeat = true;
            }
            else {
                if (!repeat) {
                    String name = connection.attr("title");
                    String nameResult = name.substring(name.indexOf("«") + 1, name.indexOf("»"));
                    namesAllStations.add(nameResult);
                    JSONObject connect = new JSONObject();
                    String number = connection.attr("class");
                    String numberResult = number.substring(number.indexOf("ln-") + 3);
                    connect.put("line", numberResult);
                    connect.put("station", nameResult);
                    arrayConnection.add(connect);
                }
            }
        });
        rootObject.put("connections", arrayConnections);
    }

    private static boolean repeatStation(String name){
        return namesAllStations.contains(name);
    }

    private static String changeFormat(JSONObject object) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = object.toString().split("(?<=[\\[\\{,])|(?=(\\]|\\}),?)");
        int i = 0;
        for(String stringOne : strings) {
            if (stringOne.matches(".*(\\}|\\]).*")){
                i--;
            }
            for (int j = i; j > 0; j--){
                stringBuilder.append("\t");
            }
            stringBuilder.append(stringOne + "\n");
            if (stringOne.matches(".*(\\{|\\[).*")){
                i++;
            }
        }
        return stringBuilder.toString();
    }

    private static void printResult(){
        try{
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
            parseLines(jsonData);
            parseStations(jsonData);
            parseConnections(jsonData);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(PATH_FILE));
            for (String line : lines) {
                builder.append(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }

    private static void parseLines(JSONObject jsonObject){
        JSONArray linesArray = (JSONArray) jsonObject.get("lines");
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            mapLines.put((String) lineJsonObject.get("number"), (String) lineJsonObject.get("name"));
        });
    }

    private static void parseStations(JSONObject jsonObject){
        JSONObject stationsObject = (JSONObject) jsonObject.get("stations");
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            String lineNumber = (String) lineNumberObject;
            String valueMap = mapLines.get(lineNumber);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            System.out.printf("Количество станций на линии %s - %s - %d шт.%n",
                    lineNumber,valueMap,stationsArray.size());
        });
    }

    private static void parseConnections(JSONObject jsonObject){
        JSONArray connectionsArray = (JSONArray) jsonObject.get("connections");
        System.out.printf("Количество переходов в метро - %d шт.%n", connectionsArray.size());
    }

}