import java.util.Scanner;

public class Main {

    public static Scanner scanner;
    private final static double X = 1000;
    public static double totalStorage = 0.0d;

    private static String measureUnit;
    private final static String BYTES = "байт";
    private final static String KILOBYTES = "Кб";
    private final static String MEGABYTES = "Мб";
    private final static String GIGABYTES = "Гб";

    public static void main(String[] args) {

        for (; ; ) {
            try {
                System.out.print("Введите путь до папки: \n");
                scanner = new Scanner(System.in);
                String path = scanner.nextLine().trim();

                double usableStorage = FileUtils.calculateFolderSize(path);
                if (usableStorage / X < 1){
                    totalStorage = usableStorage;
                    measureUnit = BYTES;
                }
                else if(usableStorage / (X * X) < 1) {
                    totalStorage = usableStorage / X;
                    measureUnit = KILOBYTES;
                }
                else if(usableStorage / (X * X * X) < 1) {
                    totalStorage = usableStorage / (X * X);
                    measureUnit = MEGABYTES;
                }
                else {
                    totalStorage = usableStorage / (X * X * X);
                    measureUnit = GIGABYTES;
                }
                System.out.printf("Размер папки %s составляет %.2f %s\n", path, totalStorage, measureUnit);
            }
            catch (Exception ex){
                System.out.println("Произошла ошибка");
            }
        }
    }
}
