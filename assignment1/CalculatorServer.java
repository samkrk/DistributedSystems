import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the implementation class
            Calculator calculator = new CalculatorImplementation();

            // Create the registry on port 2099
            Registry registry = LocateRegistry.createRegistry(2099);

            // Bind the remote object in the registry with the name "serverName"
            registry.rebind("serverName", calculator);

            System.out.println("Calculator Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
