public class Main {

    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";

        String[] lineArray = line.split("\\s");

        ReverseArray.reverse(lineArray);

        for (String word : lineArray){
            System.out.println(word);
        }
        }
    }
