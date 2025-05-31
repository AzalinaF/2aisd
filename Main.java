package SegmentTreeJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. Создание массива из 10000 случайных чисел
        int[] nums = new int[10000];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(1000);
        }


        SegmentTree st = new SegmentTree(new int[nums.length]);

        // 3. Вставка элементов с замерами
        for (int i = 0; i < nums.length; i++) {
            st.insert(i, nums[i]);
        }

        // 4. Поиск 100 случайных элементов
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(nums.length);
            st.search(index);
        }

        // 5. Удаление 1000 случайных элементов
        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(nums.length);
            st.delete(index);
        }

        // 6. Статистика
        st.printStatistics();

        // 7. Оценка сложности
        System.out.println("Анализ данных");
        System.out.println("Insert (update): O(log n) — нужно обновить значение в листе и всех предках до корня.");
        System.out.println("Search (query): O(log n) — просматриваются не более 2 * log n отрезков.");
        System.out.println("Delete: O(log n) — как вставка, только зануление.");
        System.out.println("\nПолученные средние значения должны приблизительно подтверждать логарифмическую сложность.");
    }
}
