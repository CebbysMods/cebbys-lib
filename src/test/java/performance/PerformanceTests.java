package performance;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

public class PerformanceTests {

    @Test
    void lambdaVsIf() throws InterruptedException {
        int times = 1000000000;
        execute(times, () -> {
            if (true) someMethod();
        });
        execute(times, this::someMethod);

        long totalLambdaTime = 0;
        long totalIfTime = 0;
        boolean[] tests = {true, false};
        for (boolean test : tests) {
            long ifTime = execute(times, () -> {
                if (test) someMethod();
            });
            totalIfTime += ifTime;

            Runnable lambda;
            if (test) {
                lambda = this::someMethod;
            } else {
                lambda = () -> {
                };
            }
            long lambdaTime = execute(times, lambda);
            totalLambdaTime += lambdaTime;
            System.out.printf("Lambda time: %d%n", lambdaTime);
            System.out.printf("If time: %d%n", ifTime);
        }
        System.out.printf("Lambda time: %d%n", totalLambdaTime);
        System.out.printf("If time: %d%n", totalIfTime);
    }

    void someMethod() {
        int a = 3 * 3;
        int b = a * a;
        int c = b * b;
        int d = c * c;
    }

    private long execute(int times, Runnable runnable) {
        Instant start = Instant.now();
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toNanos();
    }
}
