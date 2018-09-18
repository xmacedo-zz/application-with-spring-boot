package io.xmacedo.client.context;

import lombok.experimental.Delegate;

public class ContextHolder extends Context {

    public static final Context DELEGATE = new ContextHolder();

    private static final InheritableThreadLocal<Context> CONTEXT_HOLDER = new InheritableThreadLocal<Context>() {

        @Override
        protected Context initialValue() {
            return new Context();
        }

    };

    public static Context get() {
        return CONTEXT_HOLDER.get();
    }

    public static void set(Context context) {
        CONTEXT_HOLDER.set(context);
    }

    public static void remove() {
        CONTEXT_HOLDER.remove();
    }

    // Deletate is used to override Context getters and setters
    @Delegate
    private Context delegate() {
        return ContextHolder.get();
    }

    @Override
    public int hashCode() {
        return this.delegate().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this.delegate().equals(o);
    }

    @Override
    public String toString() {
        return this.delegate().toString();
    }

    private ContextHolder() {

    }

}
