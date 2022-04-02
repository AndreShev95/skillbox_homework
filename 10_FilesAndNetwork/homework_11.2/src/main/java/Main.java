import java.util.Scanner;

public class Main {
    public static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        for (; ; ) {
            try {
                System.out.print("Введите путь до папки для копирования: \n");
                String sourceDirectory = scanner.nextLine().trim();
                System.out.print("Введите путь до папки для вставки: \n");
                String destinationDirectory = scanner.nextLine().trim();

                FileUtils.copyFolder(sourceDirectory,destinationDirectory);
            }
            catch (Exception ex){
                System.out.println("Произошла ошибка");
            }
        }
    }
}
