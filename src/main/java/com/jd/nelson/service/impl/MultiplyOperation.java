package com.jd.nelson.service.impl;

import com.jd.nelson.Calculator;
import com.jd.nelson.service.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MultiplyOperation implements Operation {
    private final Calculator calculator;

    private final BigDecimal operand;

    public MultiplyOperation(Calculator calculator, BigDecimal operand) {
        this.calculator = calculator;
        this.operand = operand;
    }

    @Override
    public void execute() {
        BigDecimal previousResult = calculator.getResult();
        BigDecimal nextResult = previousResult.multiply(operand).setScale(calculator.getScale(), RoundingMode.HALF_UP);
        calculator.setResult(nextResult);
        calculator.addOperation(this);
    }

    @Override
    public void undo() {
        BigDecimal previousResult = calculator.getResult();
        BigDecimal nextResult = previousResult.divide(operand, calculator.getScale(), RoundingMode.HALF_UP);
        calculator.setResult(nextResult);
    }
}
