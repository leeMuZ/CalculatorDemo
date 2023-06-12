package com.jd.nelson.service.impl;

import com.jd.nelson.Calculator;
import com.jd.nelson.service.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AddOperation implements Operation {
    private final Calculator calculator;

    private final BigDecimal operand;

    public AddOperation(Calculator calculator, BigDecimal operand) {
        this.operand = operand;
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        BigDecimal previousResult = calculator.getResult();
        BigDecimal nextResult = previousResult.add(operand).setScale(calculator.getScale(), RoundingMode.HALF_UP);
        calculator.setResult(nextResult);
        calculator.addOperation(this);
    }

    @Override
    public void undo() {
        BigDecimal previousResult = calculator.getResult();
        BigDecimal nextResult = previousResult.subtract(operand).setScale(calculator.getScale(), RoundingMode.HALF_UP);
        calculator.setResult(nextResult);

    }
}
