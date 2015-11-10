package threadStatic;

public class StaticAction {
	public static int i = 0;
	 
    public static void print() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println("step " + i + " is running.");
            sum += i;
        }
        if (sum != 45) {
            System.out.println("Thread error!");
            System.exit(0);
        }
        System.out.println("sum is " + sum);
    }
}
