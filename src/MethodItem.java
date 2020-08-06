import java.lang.reflect.Method;

public class MethodItem implements Comparable<MethodItem>{

    private Method method;

    private int priority;
    MethodItem(Method method)
    {
        this.method = method;
        this.priority = method.getAnnotation(Test.class).priority();
    }

    public Method getMethod() {
        return method;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(MethodItem item) {
        return (this.getPriority() < item.getPriority() ? -1 :
                (this.getPriority() == item.getPriority() ? 0 : 1));
    }
}
