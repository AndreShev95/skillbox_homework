
public class Main {

    public static void snakePrint (float[][] array){
        int x, y;
        int index = array.length;

        for (int diag = 0; diag < index; diag++) {
            if (diag % 2 == 0) {
                x = diag;
                y = 0;

                while (x >= 0) {
                    System.out.print(array[x][y] + " ");
                    x--;
                    y++;
                }
            } else {
                x = 0;
                y = diag;

                while (y >= 0) {
                    System.out.print(array[x][y] + " ");
                    x++;
                    y--;
                }
            }
        }

        for (int diag = 1; diag < index; diag++) {
            if (diag % 2 == 0)
            {
                x = diag;
                y = 9;

                while (x <= 9) {
                    System.out.print(array[x][y] + " ");
                    x++;
                    y--;
                }
            } else {
                x = 9;
                y = diag;

                while (y <= 9) {
                    System.out.print(array[x][y] + " ");
                    x--;
                    y++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int a = 10;
        float number = 0.0F;
        double scale = Math.pow(10, 1);
        float[][] snakeArray = new float[a][a];
        for (int i = 0; i < snakeArray.length; i++) {
            for (int j = 0; j < snakeArray[i].length; j++) {
                snakeArray[i][j] = (float) (Math.round(number * scale) / scale);
                number += 0.1F;
                System.out.print(snakeArray[i][j] + " ");
            }
            System.out.println();
        }
        snakePrint(snakeArray);
    }
}


