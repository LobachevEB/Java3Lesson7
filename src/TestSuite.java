import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite {
     public static String[] start(Class cl) throws InstantiationException, IllegalAccessException,RuntimeException {
        return testIt(cl);
     }

     public static String[] start(String clName) throws ClassNotFoundException, InstantiationException, IllegalAccessException,RuntimeException {
        Class cl = Class.forName(clName);
        return testIt(cl);
     }

     private static String[] testIt(Class cl) throws IllegalAccessException, InstantiationException,RuntimeException {
         Object instance = cl.newInstance();
         Method[] methods = cl.getMethods();
         int singleBefore = 0, singleAfter = 0;
         List<MethodItem>  tests = new ArrayList<MethodItem>();
         for (Method o : methods) {
             if(o.getAnnotation(BeforeSuite.class) != null) {
                 singleBefore ++;
                 if(singleBefore > 1){
                     throw new RuntimeException("Может быть только один метод с аннотацией @BeforeSuite!");
                 }
             }
             else if(o.getAnnotation(AfterSuite.class) != null) {
                 singleAfter ++;
                 if(singleAfter > 1){
                     throw new RuntimeException("Может быть только один метод с аннотацией @AfterSuite!");
                 }
             }
             else if(o.getAnnotation(Test.class) != null){
                 tests.add(new MethodItem(o));
             }
         }

         if(tests.size() == 0){
             return null;
         }

         Collections.sort(tests);
         String[] results = new String[tests.size()];
         int i = 0;
         for (MethodItem item: tests) {
             boolean passed = true;
             try {
                 switch (item.getMethod().getName()) {
                     case "sum":
                         passed = ((int) item.getMethod().invoke(instance, 100, 500) == 600);
                         break;
                     case "mul":
                         passed = ((int) item.getMethod().invoke(instance, 3, 4) == 12);
                         break;
                     case "div":
                         passed = ((double) item.getMethod().invoke(instance, 3, 0) == 0d);
                         break;
                     case "distr":
                         passed = ((int) item.getMethod().invoke(instance, 3, 7) == -4);
                         break;
                 }
             }
             catch (Exception e){
                 passed = false;
             }
             finally {
                 results[i] = String.format("Success of test %s: %s\n",item.getMethod().getName(),passed);
             }
             i++;
         }

         return results;
     }
}
