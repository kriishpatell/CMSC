package Labs.Lab6;

public class AlgorithmAnalysisLab{

    public static void main(String[] args) throws Exception {
        Integer[] sampleSize = {10, 20, 30, 50, 75, 100};
        long overallStartTime = System.nanoTime();
        for (Integer size : sampleSize) {
            int fragNum = 1;
            long startTime = System.nanoTime();
            // Fragment 1
            long sum = 0;
            for (int i = 0; i < size; i++) {
                sum++;
                Thread.sleep(1);
            }

            long endTime = System.nanoTime();
            long diff = endTime - startTime;
            // 1 second = 1_000_000_000 nano seconds
            double elapsedTimeInSecond = (double) diff / 1_000_000_000;
            System.out.println("Time to compute Fragment " + fragNum++ + " on a sample size of " + size + " is " + elapsedTimeInSecond + " seconds.");

            startTime = System.nanoTime();
            // Fragment 2
            sum = 0;
            for (int i = 0; i < size; i += 2) {
                sum++;
                Thread.sleep(1);
            }


            endTime = System.nanoTime();
            diff = endTime - startTime;
            // 1 second = 1_000_000_000 nano seconds
            elapsedTimeInSecond = (double) diff / 1_000_000_000;
            System.out.println("Time to compute Fragment " + fragNum++ + " on a sample size of " + size + " is " + elapsedTimeInSecond + " seconds.");
            startTime = System.nanoTime();
            // Fragment 3
            sum = 0;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    sum++;
                    Thread.sleep(1);
                }

            endTime = System.nanoTime();
            diff = endTime - startTime;
            // 1 second = 1_000_000_000 nano seconds
            elapsedTimeInSecond = (double) diff / 1_000_000_000;
            System.out.println("Time to compute Fragment " + fragNum++ + " on a sample size of " + size + " is " + elapsedTimeInSecond + " seconds.");
            startTime = System.nanoTime();
            // Fragment 4
            sum = 0;
            for (int i = 0; i < size; i++) {
                sum++;
                Thread.sleep(1);
            }
            for (int j = 0; j < size; j++) {
                sum++;
                Thread.sleep(1);
            }

            endTime = System.nanoTime();
            diff = endTime - startTime;
            // 1 second = 1_000_000_000 nano seconds
            elapsedTimeInSecond = (double) diff / 1_000_000_000;
            System.out.println("Time to compute Fragment " + fragNum++ + " on a sample size of " + size + " is " + elapsedTimeInSecond + " seconds.");
//    fragNum++;
            startTime = System.nanoTime();
            // Fragment 5
            sum = 0;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size * size; j++) {
                    sum++;
                    Thread.sleep(1);
                }

            endTime = System.nanoTime();
            diff = endTime - startTime;
            // 1 second = 1_000_000_000 nano seconds
            elapsedTimeInSecond = (double) diff / 1_000_000_000;
            System.out.println("Time to compute Fragment " + fragNum++ + " on a sample size of " + size + " is " + elapsedTimeInSecond + " seconds.");

            startTime = System.nanoTime();
            // Fragment 6
            sum = 0;
            for (int i = 0; i < size; i++)
                for (int j = 0; j < i; j++) {
                    sum++;
                    Thread.sleep(1);
                }

            endTime = System.nanoTime();
            diff = endTime - startTime;
            // 1 second = 1_000_000_000 nano seconds
            elapsedTimeInSecond = (double) diff / 1_000_000_000;
            System.out.println("Time to compute Fragment " + fragNum++ + " on a sample size of " + size + " is " + elapsedTimeInSecond + " seconds.");

            startTime = System.nanoTime();

            // Fragment 7
            sum = 0;
            for (int i = 1; i < size; i = i * 2) {
                sum++;
                Thread.sleep(1);
            }
            endTime = System.nanoTime();
            diff = endTime - startTime;
            // 1 second = 1_000_000_000 nano seconds
            elapsedTimeInSecond = (double) diff / 1_000_000_000;
            System.out.println("Time to compute Fragment " + fragNum++ + " on a sample size of " + size + " is " + elapsedTimeInSecond + " seconds.");
        }
        long overallEndTime = System.nanoTime();
        long totalDiff = overallEndTime - overallStartTime;
        double elapsedTimeInMin = (double) totalDiff / 1_000_000_000 / 60;
        System.out.println("Time to compute lab is " + elapsedTimeInMin + " minutes.");
    }
}
