package services.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


public class CorrelationAnalysis {
    public static Map<String, List<Double>> processStudentData(Map<String, List<double[]>> data, int threadPoolSize)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        List<Future<Map.Entry<String, List<Double>>>> futures = new ArrayList<>();

        for (Map.Entry<String, List<double[]>> entry : data.entrySet()) {
            StudentCorrelationTask task = new StudentCorrelationTask(entry.getKey(), entry.getValue());
            futures.add(executor.submit(task));
        }

        Map<String, List<Double>> studentCorrelationResults = new HashMap<>();
        for (Future<Map.Entry<String, List<Double>>> future : futures) {
            Map.Entry<String, List<Double>> result = future.get();
            studentCorrelationResults.put(result.getKey(), result.getValue());
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        return studentCorrelationResults;
    }

    public static void printCorrelationResults(Map<String, List<Double>> correlationData) {
        for (Map.Entry<String, List<Double>> entry : correlationData.entrySet()) {
            String studentName = entry.getKey();
            List<Double> correlations = entry.getValue();

            System.out.println("\nСтудент: " + studentName);
            System.out.println("Корреляции между темами:");
            correlations.forEach(corr -> System.out.printf("%s%n", corr));
        }
    }
}