package io.xmacedo.utils.enums;

public enum ERequestCallerType {
    SISTEMA("SISTEMA");

    private String callerType;

    ERequestCallerType(String callerType) {
        this.callerType = callerType;
    }

    public String getValue() {
        return this.callerType;
    }
}
