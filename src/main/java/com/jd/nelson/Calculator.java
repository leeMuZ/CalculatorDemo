package com.jd.nelson;

import com.jd.nelson.service.Operation;
import com.jd.nelson.service.OperationFactory;
import com.jd.nelson.utils.CammandConstants;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Calculator {
    @Setter
    @Getter
    private BigDecimal result = BigDecimal.ZERO;

    private List<Operation> operations = new ArrayList<>();

    private int currentOptionIndex = -1;

    @Setter
    @Getter
    // 默认精度2位小数
    private  int scale = 2;

    public Calculator() {}
    public Calculator(int scale) {
        this.scale = scale;
    }

    public void calculate(Operation operation) {
        operation.execute();
    }

    public void reset() {
        result = BigDecimal.ZERO;
        operations.clear();
    }

    public void addOperation(Operation operation) {
        if(currentOptionIndex == operations.size()-1){
            operations.add(operation);

        }
        currentOptionIndex ++;
    }

    public Optional<Operation> getNextOption() {
        if (currentOptionIndex > -1 && currentOptionIndex < operations.size() - 1) {
            return Optional.of(operations.get(currentOptionIndex +1));
        }

        return Optional.empty();
    }

    public Optional<Operation> getBackOption() {
        if (currentOptionIndex > 0 ) {
            return Optional.of(operations.get(currentOptionIndex--));
        }
        return Optional.empty();
    }


    public  List<Operation> parseCammandLine(String line) {

        line = line == null ? "" : line.trim();

        //非运算操作处理
        if (CammandConstants.isRedoOperation(line) || CammandConstants.isUndoOperation(line) || CammandConstants.isResetOperation(line)) {
            return Arrays.asList(OperationFactory.createOperation(this, line, null));
        }

        //等号处理
        if (StringUtils.endsWith(line, CammandConstants.EQUAL)) {
            line = line.substring(0, line.length() - 1);
        }

        //字符以空格进行拆分
        List<String> elements = Arrays.stream(StringUtils.split(line)).collect(Collectors.toList());

        if (!expressionValidate(elements)) {
            System.out.println("illegal expression");
            return Collections.emptyList();
        }

        String firstElement = elements.get(0);
        if (NumberUtils.isParsable(firstElement)) {
            this.result = new BigDecimal(firstElement);
            elements.remove(0);
        }

        int currentOpsSize = this.operations.size();

        //redo后运算，清理所在位置之后的option
        if(currentOptionIndex < currentOpsSize-1){
            operations = operations.subList(0,currentOptionIndex+1);
        }

        List<Operation> newOperations = new ArrayList<>();
        //运算指令生产
        while (CollectionUtils.isNotEmpty(elements)) {
            String operation = elements.remove(0);
            String operand = elements.remove(0);
            newOperations.add(OperationFactory.createOperation(this, operation, new BigDecimal(operand)));
        }

        return newOperations;
    }
    private  boolean expressionValidate(List<String> elements) {

        if (elements.size() < 2) {
            return false;
        }

        int index = 0;

        boolean valid = true;

        //第一个元素如果不是运算符或数字则为非法操作
        if (!CammandConstants.isCalculateSymbol(elements.get(0)) && !NumberUtils.isParsable(elements.get(index++))) {
            return false;
        }

        //累计操作元素数量为偶数，新运算操作元素数量为奇数
        if(elements.size() % 2 != index){
            return false;
        }


        while (index < elements.size()) {
            String operation = elements.get(index++);
            String operand = elements.get(index++);

            if(!NumberUtils.isParsable(operand) || !CammandConstants.isCalculateSymbol(operation)){
                return false;
            }

        }

        return valid;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator(5);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {

            calculator.parseCammandLine(scanner.nextLine()).forEach(Operation::execute);

            System.out.println("result: " + calculator.getResult().stripTrailingZeros().toPlainString());
        }

        scanner.close();
    }
}
