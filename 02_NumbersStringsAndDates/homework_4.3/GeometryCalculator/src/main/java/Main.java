import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Пожалуйста, введите радиус круга и шара: ");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        Double radius = Double.parseDouble(number);

        System.out.println("Площадь круга равна - " + GeometryCalculator.getCircleSquare(radius));
        System.out.println("Объем шара равен - " + GeometryCalculator.getSphereVolume(radius));
        System.out.println("Площадь треугольника равна - " + GeometryCalculator.getTriangleSquare(1.0,5.0,1.0));
    }
}
