import java.io.*;
import java.util.*;

public class QuickSortMeasurement2 {
    private static long iterationCount = 0;

    public static void main(String[] args) {

        DataCreator dataCreator = new DataCreator();
        dataCreator.generateFile();

        List<int[]> arrays = loadArraysFromFile("inputData.txt", dataCreator.getSizes());


        System.out.println("Размер - Среднее время  - Среднее итераций");

        for (int[] array : arrays) {
            // Прогрев JVM
            for (int i = 0; i < 10; i++) {
                int[] warmup = array.clone();
                quickSort(warmup, 0, warmup.length - 1);
            }

            long totalTime = 1000000000;
            long totalIterations = 100000000;

            for (int i = 0; i < 10000; i++) {
                int[] copy = array.clone();
                iterationCount = 0;

                long startTime = System.nanoTime();
                quickSort(copy, 0, copy.length - 1);
                long endTime = System.nanoTime();

                totalTime += (endTime - startTime);
                totalIterations += iterationCount;
            }

            double averageTime = totalTime / 10000.0;
            double averageIterations = totalIterations / 10000.0;

            System.out.printf("%6d - %12.0f  - %10d %n ",
                    array.length, averageTime, Math.round(averageIterations));

        }
    }

    private static void quickSort(int[] array, int leftIndex, int rightIndex) {
        if (array.length == 0 || leftIndex >= rightIndex) return;

        int pivot = array[(leftIndex + rightIndex) / 2];
        int leftMarkerIndex = leftIndex;
        int rightMarkerIndex = rightIndex;

        while (leftMarkerIndex <= rightMarkerIndex) {
            while (array[leftMarkerIndex] < pivot) {
                leftMarkerIndex++;
                iterationCount++;
            }
            while (array[rightMarkerIndex] > pivot) {
                rightMarkerIndex--;
                iterationCount++;
            }
            if (leftMarkerIndex <= rightMarkerIndex) {
                int swap = array[leftMarkerIndex];
                array[leftMarkerIndex] = array[rightMarkerIndex];
                array[rightMarkerIndex] = swap;
                leftMarkerIndex++;
                rightMarkerIndex--;
                iterationCount++;
            }
        }

        if (leftIndex < rightMarkerIndex) quickSort(array, leftIndex, rightMarkerIndex);
        if (rightIndex > leftMarkerIndex) quickSort(array, leftMarkerIndex, rightIndex);
    }
    private static List<int[]> loadArraysFromFile(String filename, int[] sizes) {
        List<int[]> arrays = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] numbers = reader.readLine().trim().split("\\s+");
            int index = 0;
            for (int size : sizes) {
                int[] array = new int[size];
                for (int i = 0; i < size; i++) {
                    array[i] = Integer.parseInt(numbers[index++]);
                }
                arrays.add(array);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла данных", e);
        }
        return arrays;
    }
}
