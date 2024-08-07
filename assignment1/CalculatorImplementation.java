import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Stack;


public class CalculatorImplementation extends UnicastRemoteObject implements Calculator{
    // initialise stack  
    Stack<Integer> stack = new Stack<>();

    // define constructor 
    protected CalculatorImplementation() throws RemoteException {
        super();
        this.stack = new Stack<>(); // each client now has its own stack 
    }

    // Function to find GCD 
    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Function to find LCM using the formula LCM(a, b) = (a * b) / GCD(a, b)
    public static int findLCM(int a, int b) {
        return Math.abs(a * b) / findGCD(a, b);
    }
    

    // implement the methods defined in the interface 
    public synchronized void pushValue(int val){
        stack.push(val);
    }

    public synchronized void pushOperation(String operator){
        if (stack.empty() == true){ // need data to operate on 
            System.out.println("Stack is empty. Cannot perform operation.");
            return;
        } 

        int[] array = new int[stack.size()]; // initialise array for stack values

        int i = 0; 
        while (isEmpty() == false){
            array[i] = stack.pop();
            i++;
        }

        switch (operator) {
            case "min":
                int min = array[0];
                for (int j = 1; j < array.length ; j++){
                    if (array[j] < min){
                        min = array[j];
                    }
                }
                stack.push(min);
                break;
            case "max":
                int max = array[0];
                for (int j = 1; j < array.length ; j++){
                    if (array[j] > max){
                        max = array[j];
                    }
                }
                stack.push(max);
                break;
            case "lcm":
                int lcm = array[0];
                for (int j = 1; j < array.length; j++) {
                    lcm = findLCM(lcm, array[j]);
                }
                stack.push(lcm);
                break;
            case "gcd":
                int gcd = array[0];
                for (int j = 1; j < array.length; j++) {
                    gcd = findGCD(gcd, array[j]);
                }
                stack.push(gcd);
                break;
        
            default:
                break;
        }
        return;
    }
    public synchronized int pop(){
        if (isEmpty() == true){return 0;}
        return stack.pop();
    }

    public synchronized boolean isEmpty(){
        return stack.empty();
    }
    
    public synchronized int delayPop(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stack.pop();
    }
}
