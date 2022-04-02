public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Граничные значения всех типов чисел:");
        System.out.println("Тип byte - от " + Byte.MIN_VALUE + " до " + Byte.MAX_VALUE);
        System.out.println("Тип int - от " + Integer.MIN_VALUE + " до " + Integer.MAX_VALUE);
        System.out.println("Тип short - от " + Short.MIN_VALUE + " до " + Short.MAX_VALUE);
        System.out.println("Тип long - от " + Long.MIN_VALUE + " до " + Long.MAX_VALUE);
        System.out.println("Тип float - от " + -Float.MAX_VALUE + " до " + -Float.MIN_VALUE + " и от " + Float.MIN_VALUE + " до " + Float.MAX_VALUE);
        System.out.println("Тип double - от " + -Double.MAX_VALUE + " до " + -Double.MIN_VALUE + " и от " + Double.MIN_VALUE + " до " + Double.MAX_VALUE);
    }
}
