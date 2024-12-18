package services;

import java.util.Arrays;
import java.util.List;

public class AverageValues {
    public static double[] fillWithAverage(double[] array, int len) {
        if (array.length >= len)
            return array;

        double sum = 0;
        for (double value : array)
            sum += value;

        double average = Math.round(sum / array.length);
        double[] newArray = Arrays.copyOf(array, len);

        for (int i = array.length; i < len; i++)
            newArray[i] = average;

        return newArray;
    }

    public static int WhichAverage(List<double[]> array) {
        int count = 0;
        for (double[] value : array) {
            if (count < value.length)
                count = value.length;
        }

        return count;
    }
}

