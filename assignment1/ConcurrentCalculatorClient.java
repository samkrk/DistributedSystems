import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ConcurrentCalculatorClient {

    public static void main(String[] args) {
        // Number of concurrent clients for testing
        final int NUM_CLIENTS = 3;

        Runnable clientTask = () -> {
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 2099);
                Calculator calculator = (Calculator) registry.lookup("serverName");

                // Test Case 1: Basic Push and Pop
                System.out.println(Thread.currentThread().getName() + " - Basic Push and Pop Test");
                calculator.pushValue(10);
                calculator.pushValue(20);
                System.out.println(Thread.currentThread().getName() + " - Pop: " + calculator.pop()); // Expected 20
                System.out.println(Thread.currentThread().getName() + " - Pop: " + calculator.pop()); // Expected 10
                System.out.println(Thread.currentThread().getName() + " - Is Empty: " + calculator.isEmpty()); // Expected true

                // Test Case 2: Push Operation
                System.out.println(Thread.currentThread().getName() + " - Push Operation Test");
                calculator.pushValue(5);
                calculator.pushValue(15);
                calculator.pushOperation("min");
                System.out.println(Thread.currentThread().getName() + " - Min: " + calculator.pop()); // Expected 5

                // Test Case 3: Delay Pop
                System.out.println(Thread.currentThread().getName() + " - Delay Pop Test");
                calculator.pushValue(100);
                System.out.println(Thread.currentThread().getName() + " - Delayed Pop (1000 ms): " + calculator.delayPop(1000)); // Expected 100

                // Test Case 4: LCM and GCD
                System.out.println(Thread.currentThread().getName() + " - LCM and GCD Test");
                calculator.pushValue(12);
                calculator.pushValue(18);
                calculator.pushOperation("gcd");
                System.out.println(Thread.currentThread().getName() + " - GCD: " + calculator.pop()); // Expected 6
                calculator.pushValue(12);
                calculator.pushValue(18);
                calculator.pushOperation("lcm");
                System.out.println(Thread.currentThread().getName() + " - LCM: " + calculator.pop()); // Expected 36

                // Edge Case: Pop from empty stack
                System.out.println(Thread.currentThread().getName() + " - Edge Case: Pop from empty stack");
                System.out.println(Thread.currentThread().getName() + " - Pop: " + calculator.pop()); // Expected 0

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Start multiple client threads
        for (int i = 0; i < NUM_CLIENTS; i++) {
            Thread clientThread = new Thread(clientTask, "Client-" + i);
            clientThread.start();
        }
    }
}
