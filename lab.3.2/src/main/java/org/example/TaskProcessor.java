package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Клас, що виконує серію випробовувань над ігровим кубиком.
 * За умовою кубик має підкидатися більше одного, але не більше 10-ти разів.
 * Всередині класу методи прораховують, скільки разів випала кожна грань під час випробовувань,
 * найменше та найбільше значення, що при цьому виникли.
 */
public class TaskProcessor {

    /**
     * Виконує основні обчислення. Відкриває файл для запису, проводить випробування над ігровим кубиком,
     * викликає метод виводу результатів.
     * Метод має публічний тип доступу.
     * У даному методі використовуються конструкція try-with-resources для безпечного відкриття файлу.
     * @param curIter
     * @param inputNumber
     */
    public void processTask(int curIter, int inputNumber)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true)))
        {
            int throwCount = inputNumber;

            if (throwCount < 1 || throwCount > 10)
            {
                writingLogicError(writer);
                return;
            }

            Random rand = new Random();
            int[] luckyArr = new int[throwCount];
            int[] countArr = new int[6];

            for (int i = 0; i < luckyArr.length; i++)
            {
                int current = rand.nextInt(1, 7);
                luckyArr[i] = current;
                countArr[current - 1]++;
            }

            int min = luckyArr[0];
            int max = luckyArr[0];
            for (int i = 1; i < luckyArr.length; i++)
            {
                if (min > luckyArr[i]) {
                    min = luckyArr[i];
                }
                if (max < luckyArr[i]) {
                    max = luckyArr[i];
                }
            }

            printResults(writer, curIter, throwCount, luckyArr, countArr, min, max);
            System.out.println("\n[УВАГА] - Дані успішно записані у файл!\n");

        } catch (IOException e) {
            System.out.println("[ПОМИЛКА] - Проблема з доступом до файлу: " + e.getMessage());
        }
    }

    /**
     * Друкує вивід як у консоль, так і у файл
     * @param writer
     * @param curIter
     * @param throwCount
     * @param luckyArr
     * @param countArr
     * @param min
     * @param max
     */
    private void printResults(PrintWriter writer, int curIter, int throwCount, int[] luckyArr, int[] countArr, int min, int max)
    {
        String header = "\nНабір №" + curIter + ":\nОтримані дані з файлу input.txt: " + throwCount + " підкидань";
        System.out.println(header);
        writer.println("-".repeat(50));
        writer.println(header);

        String arrayMsg = "\nПісля серії з " + throwCount + " кидків вдалося отримати такі значення:\n\n[ ";
        System.out.print(arrayMsg);
        writer.print(arrayMsg);

        for (int i : luckyArr) {
            System.out.print(i + " ");
            writer.print(i + " ");
        }

        System.out.println("]\n");
        writer.println("]\n");

        for (int i = 0; i < countArr.length; i++) {
            String statMsg = "[УВАГА] - Число " + (i + 1) + " випало " + countArr[i] +
                    (countArr[i] >= 2 && countArr[i] <= 4 ? " рази!" : (countArr[i] >= 5 || countArr[i] == 0 ? " разів!" : " раз!"));
            System.out.println(statMsg);
            writer.println(statMsg);
        }

        String maxMsg = "\nНайбільше число, що випало за " + throwCount + " кидків: " + max;
        String minMsg = "Найменше число, що випало за " + throwCount + " кидків: " + min;

        System.out.println(maxMsg);
        System.out.println(minMsg);
        writer.println(maxMsg);
        writer.println(minMsg);

        // Роздільник для файлу
        writer.println();
        writer.println("-".repeat(50));
        writer.println();
    }

    /**
     * Зберігає в собі текст обробки помилки, якщо введені дані не задовольняють заданий за умовою числовий діапазон
     * @param writer
     */
    private void writingLogicError(PrintWriter writer) {
        String errorMsg = "\n[УВАГА] - Дані не задовольняють умову задачі! (Число підкидань має бути більше 1 або менше 10!)\n";
        System.out.println(errorMsg);
        writer.println(errorMsg);
    }
}

