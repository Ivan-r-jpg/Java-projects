package org.example;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main
{
    /**
     * Метод бере інформацію про файл з аргументів командного рядка.
     * Якщо файл не було передано, то програма безпечно завершує роботу.
     * У переданому файлі перший рядок відповідає за кількість випробовуваних наборів.
     * Під час кожної ітерації викликає метод processTask класу TaskProcessor, якщо значення у файлі все ж існує.
     * Ловить помилки на предмет роботи потоку Scanner.
     * У даному методі використовуються конструкція try-with-resources.
     * @param args
     */
    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            System.out.println("\n[ПОМИЛКА] - Файл config.txt порожній!");
            return;
        }
        try(Scanner fileReader = new Scanner(new File(args[0])))
        {
            if(fileReader.hasNextInt())
            {
                int iterCount = fileReader.nextInt();
                TaskProcessor newTask =  new TaskProcessor();
                for(int i = 0; i < iterCount; i++)
                {
                    if(fileReader.hasNextInt())
                    {
                        int inputNumber = fileReader.nextInt();
                        newTask.processTask(i+1, inputNumber);
                    }
                    else
                    {
                        System.out.println("\nКінець файлу - числових значень не виявлено!");
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

