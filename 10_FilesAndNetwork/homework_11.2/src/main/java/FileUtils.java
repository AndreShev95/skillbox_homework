import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Scanner;

public class FileUtils {
    public static Scanner scanner = new Scanner(System.in);

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        try {
            Path source = Paths.get(sourceDirectory);
            Path destination = Paths.get(destinationDirectory);

            Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            Path destinationDir = destination.resolve(source.relativize(dir));
                            try {
                                Files.copy(dir, destinationDir);
                            } catch (FileAlreadyExistsException e) {
                                if (!Files.isDirectory(destinationDir))
                                    throw e;
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                                throws IOException {
                            Path copyPlace = destination.resolve(source.relativize(file));

                            if (Files.notExists(copyPlace)) {
                                Files.copy(file, copyPlace);
                            } else if (file.getFileName().equals(copyPlace.getFileName()) &&
                                    (Files.size(file) == Files.size(copyPlace))) {
                                System.out.printf("Файл %s уже существует в директории назначения.%n" +
                                        "Если хотите его заменить, введите да.%n", file.getFileName());
                                String decisionCopy = scanner.nextLine().trim();
                                if (decisionCopy.equalsIgnoreCase("да")) {
                                    Files.copy(file, copyPlace, StandardCopyOption.REPLACE_EXISTING);
                                }
                            }
                            else {
                                Files.copy(file, copyPlace, StandardCopyOption.REPLACE_EXISTING);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
            System.out.println("Копирование прошло успешно.");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Произошла ошибка при копировании.");
        }
    }
}
