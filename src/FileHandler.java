import java.io.*;  // Импортируем классы для работы с файлами (BufferedReader, BufferedWriter, FileReader, FileWriter)
import java.util.Scanner;  // Импортируем Scanner для ввода данных с клавиатуры

public class FileHandler {

    // Метод для загрузки строки из файла с возможностью ввода имени файла
    public static String loadStringFromFile() {
        Scanner scanner = new Scanner(System.in);  // Создаем объект для ввода данных
        System.out.print("Введите название файла для загрузки строки (по умолчанию .txt): ");
        String fileName = scanner.nextLine().trim();  // Получаем имя файла от пользователя

        // Если имя файла не заканчивается на ".txt", добавляем это расширение
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {  // Открываем файл для чтения
            String line = reader.readLine();  // Считываем первую строку
            if (line == null) {  // Если файл пуст или не удается прочитать строку
                System.out.println("Файл пуст или произошла ошибка чтения.");
                return null;
            }
            System.out.println("Строка успешно загружена.");
            return line;  // Возвращаем прочитанную строку
        } catch (IOException e) {  // Обработка исключений при ошибке чтения
            System.out.println("Ошибка загрузки строки из файла: " + e.getMessage());
            return null;
        }
    }

    // Метод для загрузки матрицы из файла с возможностью ввода имени файла
    public static int[][] loadMatrixFromFile() {
        Scanner scanner = new Scanner(System.in);  // Создаем объект для ввода данных
        System.out.print("Введите название файла для загрузки матрицы (по умолчанию .txt): ");
        String fileName = scanner.nextLine().trim();  // Получаем имя файла от пользователя

        // Если имя файла не заканчивается на ".txt", добавляем это расширение
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {  // Открываем файл для чтения
            // Считываем первую строку для определения размера матрицы
            String line = reader.readLine();
            if (line == null || line.isEmpty()) {  // Если файл пуст или данные некорректны
                System.out.println("Файл пуст или содержит некорректные данные.");
                return null;
            }

            String[] values = line.split(" ");  // Разделяем строку по пробелам для получения элементов
            int size = values.length;  // Размер матрицы
            int[][] matrix = new int[size][size];  // Создаем матрицу

            // Считываем матрицу построчно
            int rowIndex = 0;
            do {
                values = line.split(" ");  // Разделяем строку на элементы
                for (int col = 0; col < values.length; col++) {
                    matrix[rowIndex][col] = Integer.parseInt(values[col]);  // Заполняем матрицу
                }
                rowIndex++;
            } while ((line = reader.readLine()) != null);  // Читаем остальные строки

            System.out.println("Матрица успешно загружена.");
            return matrix;  // Возвращаем загруженную матрицу
        } catch (IOException | NumberFormatException e) {  // Обработка ошибок при чтении или преобразовании данных
            System.out.println("Ошибка при загрузке матрицы из файла: " + e.getMessage());
            return null;
        }
    }

    // Метод для сохранения строки в файл
    public static void saveStringToFile(String data) {
        Scanner scanner = new Scanner(System.in);  // Создаем объект для ввода данных
        System.out.print("Введите название файла для сохранения строки (по умолчанию .txt): ");
        String fileName = scanner.nextLine().trim();  // Получаем имя файла от пользователя

        // Если имя файла не заканчивается на ".txt", добавляем это расширение
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {  // Открываем файл для записи
            writer.write(data);  // Записываем строку в файл
            System.out.println("Строка сохранена в файл " + fileName);
        } catch (IOException e) {  // Обработка ошибок при записи в файл
            System.out.println("Ошибка сохранения строки в файл: " + e.getMessage());
        }
    }

    // Метод для сохранения матрицы в файл
    public static void saveMatrixToFile(int[][] matrix) {
        if (matrix == null) {  // Проверяем, что матрица существует
            System.out.println("Матрица не загружена или не введена.");
            return;  // Если матрица не существует, выходим из метода
        }

        Scanner scanner = new Scanner(System.in);  // Создаем объект для ввода данных
        System.out.print("Введите название файла для сохранения матрицы (по умолчанию .txt): ");
        String fileName = scanner.nextLine().trim();  // Получаем имя файла от пользователя

        // Если имя файла не заканчивается на ".txt", добавляем это расширение
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {  // Открываем файл для записи
            for (int[] row : matrix) {  // Проходим по строкам матрицы
                for (int element : row) {  // Проходим по элементам строки
                    writer.write(element + " ");  // Записываем элемент в файл
                }
                writer.newLine();  // Переход на новую строку
            }
            System.out.println("Матрица сохранена в файл " + fileName);
        } catch (IOException e) {  // Обработка ошибок при записи в файл
            System.out.println("Ошибка сохранения матрицы в файл: " + e.getMessage());
        }
    }
}
