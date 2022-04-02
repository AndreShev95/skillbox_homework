import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class Main {
    public static final String PATH_SITE = "https://lenta.ru";
    public static final String PATH_FOLDER = "E:/Java/java_basics/11_FilesAndNetwork/homework_11.4/images";

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect(PATH_SITE).get();
            Elements elements = doc.select("img");
            File newFolder = new File(PATH_FOLDER);
            newFolder.mkdir();
            downloadImages(elements);
            System.out.println("Скачивание завершено.");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public static void downloadImages(Elements elements){
        System.out.printf("Скачаны следующие файлы с сайта %s:%n", PATH_SITE);
        elements.forEach(element -> {
            try {
                String ref = element.attr("abs:src");
                Connection.Response content = Jsoup.connect(ref).ignoreContentType(true).execute();
                if (content.contentType().contains("image")) {
                String name = ref.substring(ref.lastIndexOf('/') + 1).trim();
                String formatName = name.substring(name.indexOf('.') + 1).trim();
                URL url = new URL(ref);
                BufferedImage image = ImageIO.read(url);
                ImageIO.write(image, formatName, new File("images/" + name));
                System.out.println(name);
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
