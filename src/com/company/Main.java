package com.company;

import com.company.buffer.ProxyRingBuffer;
import com.company.buffer.factory.CreateRingBufferFactory;
import com.company.buffer.factory.IntegerRingBufferFactory;
import com.company.buffer.factory.StringRingBufferFactory;

import java.util.Scanner;

public class Main {

    private static int numberType = 0;

    /**
     * Метод для считывания пункта меню
     * @param min
     * @param max
     * @return
     */
    private static int selectMenuItem(int min, int max) {
        int num = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите число в диапазоне [" + min + ".." + max + "]:");
        do {
            System.out.print("--> ");
            num = in.nextInt();
            if (num < min || num > max) {
                System.out.println("Ошибка! Введите число в диапазоне [" + min + ".." + max + "]:");
            }
        } while (num < min || num > max);
        System.out.println();
        return num;
    }

    /**
     * Метод для считывания, добавляемого в буфер, элемента с консоли
     * @return
     */
    private static Object readingFromConsole() {
        System.out.println("Введите элемент:");
        System.out.print("--> ");
        Scanner in = new Scanner(System.in);
        if (numberType == 1) {
            return in.nextInt();
        }
        return in.nextLine();
    }
    
    private static CreateRingBufferFactory selectingInstanceOfClass(int number) {
        numberType = number;
        return switch (number) {
            case 1 -> new IntegerRingBufferFactory<Integer>();
            case 2 -> new StringRingBufferFactory<String>();
            default -> null;
        };
    }

    private static void printStartMenu() {
        System.out.println("Выберите пункт меню:");
        System.out.println("1. Создать буфер на снове целых чисел");
        System.out.println("2. Создать буфер на снове строк");
    }

    private static void printMainMenu() {
        System.out.println("Выберите пункт меню:");
        System.out.println("1. Добавить элемент");
        System.out.println("2. Вывести элемент из начала очереди без удаления");
        System.out.println("3. Вывести элемент из начала очереди и удалить его");
        System.out.println("4. Вывести максимальный размер буфера");
        System.out.println("5. Вывести текущее количество элементов в буфере");
        System.out.println("6. Печать буфера");
        System.out.println("0. Выход");
    }

    public static void main(String[] args) throws Exception {
        printStartMenu();
        CreateRingBufferFactory<Integer> ringBufferFactory = selectingInstanceOfClass(selectMenuItem(1, 2));
        ProxyRingBuffer proxyRingBuffer = ringBufferFactory.createRingBuffer();
        int select = 0;
        do {
            printMainMenu();
            select = selectMenuItem(0, 6);
            switch (select) {
                case 1:
                    proxyRingBuffer.add(readingFromConsole());
                    break;
                case 2:
                    System.out.println("Элемент из начала очереди - " + proxyRingBuffer.peek());
                    break;
                case 3:
                    System.out.println("Элемент из начала очереди - " + proxyRingBuffer.poll());
                    break;
                case 4:
                    System.out.println("Максимальный размер буфера = " + proxyRingBuffer.getSize());
                    break;
                case 5:
                    System.out.println("Текущее количество элементов в буфере = " + proxyRingBuffer.getCurrentNumberItems());
                    break;
                case 6:
                    proxyRingBuffer.print();
                    break;

            }
            System.out.println();
        } while (select != 0);
    }
}
