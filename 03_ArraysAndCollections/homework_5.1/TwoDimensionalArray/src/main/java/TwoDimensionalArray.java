public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {

        char[][] twoDimArray = new char[size][size];
        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                if (j == i || j == twoDimArray.length - 1 - i)
                {
                    twoDimArray[i][j] = symbol;

                }
                else
                {
                    twoDimArray[i][j] = ' ';
                }
                System.out.print(twoDimArray[i][j]);
            }
            System.out.println();
        }

        //TODO: Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ symbol по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]

        return twoDimArray;
    }
}
