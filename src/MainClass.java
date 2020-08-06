import java.util.Arrays;

public class MainClass {
    public static void main(String[] args) {
        try {
            runTests();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void runTests() throws IllegalAccessException, InstantiationException {
        try {
            String[] r1 = new TestSuite().start(ClassForTest.class);
            System.out.println(Arrays.toString(r1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            String[] r2 = new TestSuite().start(Class.forName("ClassForTest"));
            System.out.println(Arrays.toString(r2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
