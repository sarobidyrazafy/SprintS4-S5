package mg.itu.prom16;

import java.lang.reflect.Method;

public class Mapping {
    private String className;
    private Method methodName;

    public Mapping(String className, Method methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public Method getMethod() {
        return methodName;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+":{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName.getName() + '\'' +
                '}';
    }
}

