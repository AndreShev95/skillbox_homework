import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        Path folder = Paths.get(path);
        long[] size = {0};
        try {
            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    size[0] = size[0] + attrs.size();
                    return FileVisitResult.CONTINUE;
                }
                public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
                    System.out.printf("Невозможно получить размер файла %s%n\n", file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println("Ошибка при подсчёте размера директории");
        }
        return size[0];
    }
}
