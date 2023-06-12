package com.jd.nelson.service.impl;

import com.jd.nelson.Calculator;
import com.jd.nelson.service.Operation;

public class ResetOperation implements Operation {
    private final Calculator calculator;

    public ResetOperation(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.reset();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
