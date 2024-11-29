import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static String currentString;
    private static int[][] currentMatrix;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");

            // Очищаем буфер после ввода числа, чтобы избежать дублирования меню
            scanner.nextLine(); // Очищаем оставшийся символ новой строки в буфере

            switch (choice) {
                case 1:
                    currentString = FileHandler.loadStringFromFile();
                    break;
                case 2:
                    currentMatrix = FileHandler.loadMatrixFromFile();
                    break;
                case 3:
                    inputStringManually();
                    break;
                case 4:
                    inputMatrixManually();
                    break;
                case 5:
                    if (currentString == null || currentString.isEmpty()) {
                        System.out.println("Ошибка: строка не введена.");
                    } else {
                        currentString = StringProcessor.replaceVowels(currentString);
                        System.out.println("Строка после замены гласных: " + currentString);
                    }
                    break;
                case 6:
                    if (currentMatrix == null) {
                        System.out.println("Ошибка: матрица не введена.");
                    } else {
                        int sum = MatrixProcessor.sumDiagonals(currentMatrix);
                        System.out.println("Сумма элементов на диагоналях: " + sum);
                    }
                    break;
                case 7:
                    if (currentMatrix == null) {
                        System.out.println("Ошибка: матрица не введена.");
                    } else {
                        int mostFrequent = MatrixProcessor.findMostFrequentElement(currentMatrix);
                        System.out.println("Наиболее частый элемент в матрице: " + mostFrequent);
                    }
                    break;
                case 8:
                    if (currentMatrix == null) {
                        System.out.println("Ошибка: матрица не введена.");
                    } else {
                        Double geometricMean = MatrixProcessor.calculateGeometricMean(currentMatrix);
                        if (geometricMean != null) {  // Проверяем, не равно ли значение null
                            System.out.println("Геометрическое среднее: " + geometricMean);
                        }
                    }
                    break;
                case 9:
                    if (currentString == null || currentString.isEmpty()) {
                        System.out.println("Ошибка: строка не введена.");
                    } else {
                        FileHandler.saveStringToFile(currentString);
                    }
                    break;
                case 10:
                    if (currentMatrix == null) {
                        System.out.println("Ошибка: матрица не введена.");
                    } else {
                        FileHandler.saveMatrixToFile(currentMatrix);
                    }
                    break;
                case 11:
                    printCurrentData();
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Загрузить строку из файла");
        System.out.println("2. Загрузить матрицу из файла\n");
        System.out.println("3. Ввести строку вручную");
        System.out.println("4. Ввести матрицу вручную\n");
        System.out.println("5. (Задача A) Заменить гласные в строке");
        System.out.println("6. (Задача B) Суммировать элементы на диагоналях матрицы");
        System.out.println("7. (Задача C) Найти наиболее частый элемент в матрице");
        System.out.println("8. (Задача D) Вычислить геометрическое среднее для матрицы\n");
        System.out.println("9.  Сохранить строку в файл");
        System.out.println("10. Сохранить матрицу в файл");
        System.out.println("11. Показать текущую строку и матрицу\n");
        System.out.println("0. Выйти\n");
        //System.out.print("Выберите действие: ");
    }

    private static void inputStringManually() {
        System.out.print("Введите строку: ");
        currentString = scanner.nextLine();  // Теперь это сработает корректно
    }

    private static void inputMatrixManually() {
        int size = getIntInput("Введите размерность матрицы: ");
        while (size <= 0) {
            System.out.println("Ошибка: размерность матрицы должна быть положительным числом. Попробуйте снова.");
            size = getIntInput("Введите размерность матрицы: ");
        }
        currentMatrix = new int[size][size];
        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currentMatrix[i][j] = getIntInput("Введите элемент матрицы [" + (i + 1) + "][" + (j + 1) + "]: ");
            }
        }
    }

    private static void printCurrentData() {
        if (currentString != null && !currentString.isEmpty()) {
            System.out.println("Текущая строка: " + currentString);
        } else {
            System.out.println("Строка не введена.");
        }

        if (currentMatrix != null) {
            System.out.println("Текущая матрица:");
            for (int[] row : currentMatrix) {
                for (int value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Матрица не введена.");
        }
    }

    private static int getIntInput(String prompt) {
        int value = -1;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число.");
                scanner.next(); // Очищаем неверный ввод
            }
        }
        return value;
    }
}
