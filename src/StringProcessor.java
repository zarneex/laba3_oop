public class StringProcessor {
    // Метод для замены всех гласных в строке на "-"
    public static String replaceVowels(String input) {
        // Заменяем все символы, которые являются гласными (как в латинице, так и в кириллице) на "-"
        return input.replaceAll("[AEIOUaeiouАЕЁИОУЫЭЮЯаеёиоуыэюя]", "-");
    }
}
