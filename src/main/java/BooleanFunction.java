import com.sun.istack.internal.NotNull;

import java.util.Random;

/**
 * Класс, представляющий собой булеву функцию большой длины.
 */
public class BooleanFunction {

    public static final int BITS_IN_BASE = 32;

    /**
     * Значения булевой функции на наборе аргументов.
     * Набор - конкретные значения аргументов функции.
     * Например, набор 00000 означает, что x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0
     * Наборы начинаются с 0***00, затем 0***01, 0***10, 0***11 и т.д. до 1***11.
     * Значению функции на наборе 0***00 соответствует 0-й бит в values[0],
     * значению функции на наборе 0***01 соответствует 1-й бит в values[0].
     * Всего, values[0] хранит значения функции на наборах с 00000 (0-й бит) по 11111 (31-й бит).
     * Значения на остальных наборах хранятся соответствующим образом в элементах values
     */
    private int[] values;

    /**
     * Количество аргументов, от которых зависит функция. В случае, когда аргументов больше 5,
     * это число всегда равняется Log(2 , values.length() * {@link #BITS_IN_BASE})
     */
    private int argumentsNumber;

    /**
     * Конструктор от строки, которая представляет собой значения булевой функции на наборах аргументов.
     * Длина строки обязана быть степенью 2.
     *
     * @param string Строка, представляющая значения функции от меньшего набора к большему.
     */
    public BooleanFunction(@NotNull String string) {
        argumentsNumber = LogUtil.binlog(string.length());
        int numberOfBases = string.length() / BITS_IN_BASE;
        if (numberOfBases == 0) {
            // Длина строки либо будет делиться на 32, либо будет меньше 32-х, тогда хватит 1 базы.
            numberOfBases = 1;
        }
        values = new int[numberOfBases];
        int currentBase = 0;
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (currentChar != '0' && currentChar != '1') {
                throw new IllegalArgumentException("String must contain only 1 or 0 characters!");
            }
            if (currentChar == '1') {
                values[currentBase] |= (1 << (i % BITS_IN_BASE));
            }
            if (i % BITS_IN_BASE == 0 && i > 0) {
                currentBase++;
            }
        }
    }

    /**
     * Конструктор от количества переменных. Значения функции генерируются случайно.
     * @param n Количество переменных.
     */
    public BooleanFunction(int n) {
        argumentsNumber = n;
        int numberOfBases = (int) Math.pow(2, n) / BITS_IN_BASE;
        if (numberOfBases == 0) {
            numberOfBases = 1;
        }
        values = new int[numberOfBases];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < numberOfBases; i++) {
            values[i] = random.nextInt();
        }
        if (argumentsNumber < 5) {
            values[0] = values[0] >>> (BITS_IN_BASE - 2 << argumentsNumber);
        }
    }

    public int getArgumentsNumber() {
        return argumentsNumber;
    }

    public int getBasesNumber() {
        return values.length;
    }

    /**
     * Выводит значения функции на наборах, от младших к старшим.
     */
    public void print() {
        print(false);
    }

    /**
     * Выводит значения функции, а также дополнительные параметры.
     * @param isDebugPrint
     */
    public void print(boolean isDebugPrint) {
        System.out.println(getStringRepresentation(isDebugPrint));
        if (isDebugPrint) {
            System.out.println("Number of bases = " + values.length);
            System.out.println("Number of arguments = " + argumentsNumber);
        }
    }

    public String getStringRepresentation(boolean shouldDivideBases) {
        StringBuilder stringBuilder = new StringBuilder();
        int currentBase = 0;
        int numberOfValues = (int) Math.pow(2 ,argumentsNumber);
        for (int i = 0; i < numberOfValues; i++) {
            stringBuilder.append((values[currentBase] & (1 << (i % BITS_IN_BASE))) == 0 ? '0' : '1');
            if (i % BITS_IN_BASE == 0 && i > 0) {
                currentBase++;
                if (shouldDivideBases) {
                    stringBuilder.append(" ");
                }
            }
        }
        return stringBuilder.toString();
    }

}
