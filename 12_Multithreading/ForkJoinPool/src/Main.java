import java.io.FileWriter;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static final String PATH_SITE = "https://lenta.ru/";

    public static void main(String[] args) {
        try {
            String links = new ForkJoinPool().invoke(new FindLinks(PATH_SITE, PATH_SITE));

            FileWriter writer = new FileWriter("data/links.txt", false);
            writer.write(links);
            writer.flush();
            writer.close();
            System.out.println("Файл готов");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
