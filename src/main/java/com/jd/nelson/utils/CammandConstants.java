package com.jd.nelson.utils;

public class CammandConstants {
    public static final String ADD = "+";

    public static final String SUBTRACT = "-";

    public static final String MULTIPLY = "*";

    public static final String DIVIDE = "/";

    public static final String EQUAL = "=";

    public static final String REDO = "redo";

    public static final String UNDO = "undo";

    public static final String RESET = "reset";

    public static boolean isCalculateSymbol(String operation) {
        return  ADD.equals(operation)
                || SUBTRACT.equals(operation)
                || MULTIPLY.equals(operation)
                || DIVIDE.equals(operation);
    }

    public static boolean isRedoOperation(String line) {
        return REDO.equalsIgnoreCase(line);
    }

    public static boolean isUndoOperation(String line) {
        return UNDO.equalsIgnoreCase(line);
    }

    public static boolean isResetOperation(String line) {
        return RESET.equalsIgnoreCase(line);
    }
}
