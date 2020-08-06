import com.sun.org.apache.xpath.internal.operations.Bool;

public class ClassForTest {

    @BeforeSuite
    public void Initialize(){
        System.out.println("Initialized");
    }

//    @BeforeSuite
//    public void getProblem(){ System.out.println("Problem"); }

    @AfterSuite
    public void Clear(){
        System.out.println("Cleaned");
    }

//    @AfterSuite
//    public void anotherProblem(){
//        System.out.println("Problem");
//    }

    @Test(priority = 2)
    public int sum(int a,int b){
        return a + b;
    }

    @Test(priority = 2)
    public int distr(int a,int b){
        return a - b;
    }

    @Test(priority = 4)
    public int mul(int a,int b){
        return a * b;
    }

    @Test(priority = 1)
    public double div(double a,double b){
        return a / b;
    }

}
