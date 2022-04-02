public class Main
{
    public static void main(String[] args)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int alphabetLength = alphabet.length();
        for (int i = 0; i < alphabetLength; i++)
        {
            char c = alphabet.charAt(i);
            int code = (int) c;
            System.out.println(c + " - " + code);
        }
    }
}
