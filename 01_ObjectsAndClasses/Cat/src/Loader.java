
public class Loader
{
        private static Cat getKitten()
        {
                Cat murka = new Cat(1100.0);
                System.out.println("Вес котенка: " + murka.getWeight() + " грамм.");
                return murka;
        }

        public static void main(String[] args)
       {
               for (int i = 0; i < 3; i++)
               {
                       System.out.println(getKitten());
               }
       }
}