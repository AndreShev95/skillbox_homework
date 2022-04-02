public class GeometryCalculator {
    private static double circleSquare;
    private static double sphereVolume;
    private static double triangleSquare;
    private static double p;

    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius)
    {
        circleSquare = Math.PI * Math.pow(Math.abs(radius), 2);
        return circleSquare;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius)
    {
        sphereVolume = 4 * (Math.PI * Math.pow(Math.abs(radius), 3)) / 3;
        return sphereVolume;
    }

    public static boolean isTrianglePossible(double a, double b, double c)
    {
        if (a + b > c & a + c > b & b + c > a)
        {
            return true;
        }
            else
        {
            return false;
        }
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c)
    {
        if (GeometryCalculator.isTrianglePossible(a, b, c) == true)
        {
            p = (a + b + c) / 2;
            triangleSquare = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            return triangleSquare;
        }
        else
        {
            return -1.0;
        }
    }
}
