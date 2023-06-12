package com.jd.nelson.service.impl;

import com.jd.nelson.Calculator;
import com.jd.nelson.service.Operation;

public class RedoOperation implements Operation {
    private final Calculator calculator;

    public RedoOperation(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.getNextOption().ifPresent(calculator::calculate);

    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
