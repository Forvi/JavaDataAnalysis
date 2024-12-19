package services.analysis;

public class DataProcessing {
    public static boolean isUniform(double[] array) {
        if (array == null || array.length == 0) {
            return false;
        }

        double firstValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] != firstValue) {
                return false;
            }
        }
        return true;
    }
}


