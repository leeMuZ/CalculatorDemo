package com.jd.nelson.service;

import com.jd.nelson.Calculator;
import com.jd.nelson.service.impl.*;
import com.jd.nelson.utils.CammandConstants;

import java.math.BigDecimal;

public class OperationFactory {
    public static Operation createOperation(Calculator calculator, String operation, BigDecimal operand) {
        if (CammandConstants.ADD.equals(operation)) {
            return new AddOperation(calculator, operand);
        } else if (CammandConstants.SUBTRACT.equals(operation)) {
            return new SubtractOperation(calculator, operand);
        } else if (CammandConstants.MULTIPLY.equals(operation)) {
            return new MultiplyOperation(calculator, operand);
        } else if (CammandConstants.DIVIDE.equals(operation)) {
            return new DivideOperation(calculator, operand);
        } else if (CammandConstants.REDO.equalsIgnoreCase(operation)) {
            return new RedoOperation(calculator);
        } else if (CammandConstants.UNDO.equalsIgnoreCase(operation)) {
            return new UndoOperation(calculator);
        } else if (CammandConstants.RESET.equalsIgnoreCase(operation)) {
            return new ResetOperation(calculator);
        }

        throw new UnsupportedOperationException("illegal operation");
    }
}
