import java.util.Scanner;

public class Main {

    private static final int MAX_BOXES = 27;
    private static final int MAX_CONTAINERS = 12;
    public static int amountContainers;
    public static int amountTrucks;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int amountBoxes = Integer.parseInt(boxes);

        if (amountBoxes % MAX_BOXES == 0)
        {
            amountContainers = amountBoxes / MAX_BOXES;
        }
        else
        {
            amountContainers = Math.floorDiv(amountBoxes, MAX_BOXES) + 1;
        }

        if (amountContainers % MAX_CONTAINERS == 0)
        {
            amountTrucks = amountContainers / MAX_CONTAINERS;
        }
        else
        {
            amountTrucks = Math.floorDiv(amountContainers, MAX_CONTAINERS) + 1;
        }

        int aNumber = 1;
        int bNumber = 1;
        int cNumber = 1;

                for (int c = 1; cNumber <= amountBoxes; c++)
                {
                    if ((cNumber == 1) || ((cNumber - 1) % (MAX_BOXES * MAX_CONTAINERS) == 0)) {
                        System.out.println("Грузовик: " + aNumber);
                        aNumber++;
                    }

                    if ((cNumber == 1) || ((cNumber - 1) % MAX_BOXES == 0)) {
                        System.out.println("\tКонтейнер: " + bNumber);
                        bNumber++;
                    }

                    System.out.println("\t\tЯщик: " + cNumber);
                    cNumber++;
                }

        System.out.println("Необходимо:");
        System.out.println("грузовиков - " + amountTrucks + " шт.");
        System.out.println("контейнеров - " + amountContainers + " шт.");

        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */
    }

}
