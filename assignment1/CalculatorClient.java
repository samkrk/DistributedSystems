import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Get the registry from the server's host (localhost in this case)
            Registry registry = LocateRegistry.getRegistry("localhost", 2099);
            // Lookup the remote object by name
            Calculator calculator = (Calculator) registry.lookup("serverName");

            // Test cases for the Calculator methods

            // Test pushValue and pop
            calculator.pushValue(10);
            calculator.pushValue(20);
            System.out.println("Pop after pushing 10 and 20: " + calculator.pop()); // Should return 20
            System.out.println("Pop again: " + calculator.pop()); // Should return 10

            // Test isEmpty
            System.out.println("Is stack empty? " + calculator.isEmpty()); // Should return true

            // Test pushOperation with "min" and "max"
            calculator.pushValue(15);
            calculator.pushValue(3);
            calculator.pushValue(7);
            calculator.pushOperation("min");
            System.out.println("Pop after min operation: " + calculator.pop()); // Should return 3

            calculator.pushValue(15);
            calculator.pushValue(3);
            calculator.pushValue(7);
            calculator.pushOperation("max");
            System.out.println("Pop after max operation: " + calculator.pop()); // Should return 15

            // Test pushOperation with "gcd" and "lcm"
            calculator.pushValue(12);
            calculator.pushValue(15);
            calculator.pushValue(18);
            calculator.pushOperation("gcd");
            System.out.println("Pop after gcd operation: " + calculator.pop()); // Should return 3

            calculator.pushValue(12);
            calculator.pushValue(15);
            calculator.pushValue(18);
            calculator.pushOperation("lcm");
            System.out.println("Pop after lcm operation: " + calculator.pop()); // Should return 180

            // Test delayPop
            calculator.pushValue(100);
            System.out.println("DelayPop (2 seconds): " + calculator.delayPop(2000)); // Should wait 2 seconds and return 100

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
