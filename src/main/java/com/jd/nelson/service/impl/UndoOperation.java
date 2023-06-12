package com.jd.nelson.service.impl;

import com.jd.nelson.Calculator;
import com.jd.nelson.service.Operation;

public class UndoOperation implements Operation {
    private final Calculator calculator;

    public UndoOperation(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void execute() {
        calculator.getBackOption().ifPresent(Operation::undo);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
