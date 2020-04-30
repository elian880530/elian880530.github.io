package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeValue<T> extends RuntimeEntity {

    private T value;

    public T Value() {
        return this.value;
    }

    public RuntimeValue(T value) {
        super();
        this.value = value;
    }

    public String toString() {
        return value.toString();
    }

    public Object getValue() {
        return value;
    }
}

