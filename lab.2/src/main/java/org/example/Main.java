package org.example;

import java.util.Random;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Введіть кількість кидків грального кубика (від 1 до 10): ");
        int throwCount = scan.nextInt();

        while(throwCount < 1 || throwCount > 10) // Перевірка введеної кількості підкидань
        {
            System.out.print("\n[ВВЕДЕНО НЕПРАВИЛЬНУ КІЛЬКІСТЬ ПІДКИДАНЬ] - Введіть ще раз кількість кидків грального кубика (від 1 до 10): ");
            throwCount = scan.nextInt();
        }

        int[] luckyArr = new int[throwCount]; // Ініціалізація масиву цілих чисел, що зберігає випадкові значення від 1 до 6

        int current;

        int[] countArr = new int[6]; // Ініціалізація масиву цілих чисел, що містить в собі кількість випадіння кожної з 6 граней кубика

        for (int i = 0; i < luckyArr.length; i++)
        {
            current = rand.nextInt(1, 7);
            luckyArr[i] = current;
            countArr[current - 1]++;
        }

        System.out.print("\nПісля серії з " + throwCount + " кидків вдалося отримати такі значення:\n\n[ ");
        for (int i : luckyArr)
        {
            System.out.print(i + " ");
        }
        System.out.println("]\n");
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

        for(int i = 0; i < countArr.length; i++)
        {
            System.out.println("[УВАГА] - Число " + (i+1) + " випало " + countArr[i] +
                    (countArr[i] >= 2 && countArr[i] <= 4 ? " рази!" : (countArr[i] >= 5 || countArr[i] == 0  ? " разів!" : " раз!")));
        }

        System.out.println("\nНайбільше число, що випало за " + throwCount + " кидків: " + max);
        System.out.println("Найменше число, що випало за " + throwCount + " кидків: " + min);

        scan.close(); // Закриття потоку введення
    }
}

