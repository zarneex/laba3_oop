import java.util.HashMap;
import java.util.Map;

public class MatrixProcessor {

    // Метод для вычисления суммы элементов на главной и побочной диагоналях матрицы
    public static int sumDiagonals(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть пустой");
        }

        int mainDiagonalSum = 0;
        int secondaryDiagonalSum = 0;
        int size = matrix.length;

        for (int i = 0; i < size; i++) {
            mainDiagonalSum += matrix[i][i];  // Элементы главной диагонали
            secondaryDiagonalSum += matrix[i][size - i - 1];  // Элементы побочной диагонали
        }

        // Если размер матрицы нечетный, исключаем двойное суммирование центрального элемента
        if (size % 2 == 1) {
            int middleIndex = size / 2;
            return mainDiagonalSum + secondaryDiagonalSum - matrix[middleIndex][middleIndex];  // Убираем центральный элемент
        }

        return mainDiagonalSum + secondaryDiagonalSum;
    }

    // Метод для нахождения наиболее часто встречающегося элемента в матрице
    public static int findMostFrequentElement(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть пустой");
        }

        // Создаем карту для хранения частоты элементов
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int[] row : matrix) {
            for (int value : row) {
                frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
            }
        }

        int mostFrequent = matrix[0][0];
        int maxFrequency = 0;

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequent = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return mostFrequent;
    }

    // Метод для вычисления геометрического среднего для элементов матрицы
    public static Double calculateGeometricMean(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матрица не должна быть пустой");
        }

        double product = 1.0;
        int count = 0;

        // Проверка на нулевые элементы в матрице
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int element = matrix[i][j];

                // Если в матрице есть нулевой элемент, выводим ошибку и возвращаем null
                if (element == 0) {
                    System.out.println("Ошибка: Элементы матрицы не должны быть равны нулю для вычисления геометрического среднего");
                    return null;  // Возвращаем null, чтобы в дальнейшем не выводить неправильный результат
                }

                product *= element;  // Умножаем элементы
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Ошибка: Нет элементов для вычисления геометрического среднего");
            return null;  // Если нет элементов, возвращаем null
        }

        return Math.pow(product, 1.0 / count);  // Вычисляем геометрическое среднее
    }
}
