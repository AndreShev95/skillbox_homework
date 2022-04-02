import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

    public static int searchNumber (String[] editString){
        int index;
        if (editString[1].matches("[0-9]*"))
        {
            index = Integer.parseInt(editString[1]);
            return index;
        }
        else {
            return -1;
        }
    }

    public static String sumString (String[] addString, int index) {
        String deal = "";
        StringBuilder builder = new StringBuilder();
        builder.append(deal);
        if (index == -1) {
            for (int i = 1; i < addString.length; i++) {
                builder.append(addString[i]).append(" ");
            }
            deal = builder.toString();
        }
        else {
            for (int i = 2; i < addString.length; i++) {
                builder.append(addString[i]).append(" ");
            }
            deal = builder.toString();
        }
        return deal;
    }

    public static void addMethod (String[] addString){
        int index = searchNumber(addString);
        String deal = sumString(addString, index);
        if (index != -1){
            todoList.add(index, deal.trim());
        }
        else {
                todoList.add(deal.trim());
            }
        }


    public static void editMethod (String[] editString){
        int index = searchNumber(editString);
        String deal = sumString(editString, index);
        if (index != -1){
            todoList.add(index, deal.trim());
        }
        else {
            todoList.add(deal.trim());
        }
    }

    public static void deleteMethod (String[] deleteString){
        int index = searchNumber(deleteString);
        if (index != -1){
            todoList.delete(index);
        }
        else System.out.println("Дело с таким номером не существует");
    }

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            String[] words = input.split("\\s");

            if (words[0].matches("ADD"))
            {
                addMethod(words);
            }

            if (words[0].matches("LIST"))
            {
                todoList.getTodos();
            }

            if (words[0].matches("EDIT"))
            {
                editMethod(words);
            }

            if (words[0].matches("DELETE"))
            {
                deleteMethod(words);
            }
        }
    }
}
