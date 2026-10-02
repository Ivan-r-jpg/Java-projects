package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Головний клас програми.
 * Ініціалізує запуск програми.
 * Містить в собі публічний метод main.
 */
public class Main
{
    /**
     * Головний метод програми.
     * Проводить випробування над ігровим кубиком.
     * Вхідні дані беруться з файлу input.txt.
     * Вихідні дані записуються у файл output.txt.
     * У даному методі використовуються конструкція try-with-resources для безпечної роботи з файлом.
     * @param args
     */
    public static void main(String[] args)
    {
        try(Scanner fileReader = new Scanner(new File("input.txt"));
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true)))
        {
            if(fileReader.hasNextInt())
            {
                int throwCount = fileReader.nextInt();

                System.out.println("Отримані дані з файлу input.txt: " + throwCount + " підкидань");
                writer.println("-".repeat(50));
                writer.println("\nОтримані дані з файлу input.txt: " + throwCount + " підкидань");

                if (throwCount < 1 || throwCount > 10)
                {
                    System.out.println("\n[УВАГА] - Дані не задовольняють умову задачі! (Число підкидань має бути більше 1 або менше 10!)");
                    writer.println("\n[УВАГА] - Дані не задовольняють умову задачі! (Число підкидань має бути більше 1 або менше 10!)");
                    return;
                }
                int current;
                Random rand = new Random();
                int[] luckyArr = new int[throwCount]; // Ініціалізація масиву цілих чисел, що зберігає випадкові значення від 1 до 6
                int[] countArr = new int[6]; // Ініціалізація масиву цілих чисел, що містить в собі кількість випадіння кожної з 6 граней кубика

                for (int i = 0; i < luckyArr.length; i++)
                {
                    current = rand.nextInt(1, 7);
                    luckyArr[i] = current;
                    countArr[current - 1]++;
                }
                int min = luckyArr[0];
                int max = luckyArr[0];

                for(int i = 1; i < luckyArr.length; i++)
                {
                    if (min > luckyArr[i])
                    {
                        min = luckyArr[i];
                    }
                    if (max < luckyArr[i])
                    {
                        max = luckyArr[i];
                    }
                }

                System.out.print("\nПісля серії з " + throwCount + " кидків вдалося отримати такі значення:\n\n[ ");
                writer.print("\nПісля серії з " + throwCount + " кидків вдалося отримати такі значення:\n\n[ ");
                for (int i : luckyArr)
                {
                    System.out.print(i + " ");
                    writer.print(i + " ");

                }
                System.out.println("]\n");
                writer.println("]\n");


                for(int i = 0; i < countArr.length; i++)
                {
                    System.out.println("[УВАГА] - Число " + (i+1) + " випало " + countArr[i] +
                            (countArr[i] >= 2 && countArr[i] <= 4 ? " рази!" : (countArr[i] >= 5 || countArr[i] == 0  ? " разів!" : " раз!")));
                    writer.println("[УВАГА] - Число " + (i+1) + " випало " + countArr[i] +
                            (countArr[i] >= 2 && countArr[i] <= 4 ? " рази!" : (countArr[i] >= 5 || countArr[i] == 0  ? " разів!" : " раз!")));

                }

                System.out.println("\nНайбільше число, що випало за " + throwCount + " кидків: " + max);
                System.out.println("Найменше число, що випало за " + throwCount + " кидків: " + min);
                writer.println("\nНайбільше число, що випало за " + throwCount + " кидків: " + max);
                writer.println("Найменше число, що випало за " + throwCount + " кидків: " + min);

                System.out.println("\n[УВАГА] - Дані успішно записані у файл!");
                writer.println();
                writer.println("-".repeat(50));
                writer.println();
            }
            else
            {
                System.out.println("\n[УВАГА] - Файл input.txt порожній або містить нечислові значення!\n");
                return;
            }
        }
        catch(IOException e) // Блок, що ловить помилку, якщо та виникне
        {
            e.printStackTrace();
        }
    }
}

