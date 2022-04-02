import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "E:/Java/Multithreading/src";
        String dstFolder = "E:/Java/Multithreading/dst";
        String dstFolder2 = "E:/Java/Multithreading/dst2";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int numberCores = getNumberOfCPUCores();

        int border = files.length / numberCores;

        for (int i = 1; i <= numberCores; i++) {

            if (i == numberCores) {
                File[] files1 = new File[files.length - (border * (i - 1))];
                System.arraycopy(files, (border * (i - 1)), files1, 0, files1.length);

                //ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
                //new Thread(resizer1).start();

                NewImageResizer newResizer1 = new NewImageResizer(files1, newWidth,dstFolder2, start);
                new Thread(newResizer1).start();

            } else {
                File[] files2 = new File[border];
                System.arraycopy(files, (border * (i - 1)), files2, 0, files2.length);

                //ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder, start);
                //new Thread(resizer2).start();

                NewImageResizer newResizer2 = new NewImageResizer(files2, newWidth,dstFolder2, start);
                new Thread(newResizer2).start();
            }
        }
    }

    private static int getNumberOfCPUCores() {
        String command = "cmd /C WMIC CPU Get /Format:List";
        Process process = null;
        int numberOfCores = 0;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains("NumberOfCores")) {
                    numberOfCores = Integer.parseInt(line.split("=")[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberOfCores;
    }

}
