package demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;

public abstract class AbstractCalculator{

     public int scale = 15;
     // 默认精度
     private ArrayDeque<Operation> redo = new ArrayDeque();
     private ArrayDeque<Operation> undo = new ArrayDeque();

    public ArrayDeque<Operation> getRedo() {
        return redo;
    }

    public ArrayDeque<Operation> getUndo() {
        return undo;
    }

    public  String add(String x, String y){
        BigDecimal result = convert(x) .add(convert(y));
        String s = result.toString();
        undo.offerFirst(new Operation(OperationType.ADD, x, y , s));
        redo.clear();
        return s;
    }


    public String subtract(String x, String y) {
        BigDecimal result = convert(x).subtract(convert(y)).setScale(scale, RoundingMode.HALF_UP);
        String s = result.toString();
        undo.offerFirst(new Operation(OperationType.SUBTRACT, x, y , s));
        redo.clear();
        return s;
    }


    public String multiply(String x, String y) {
        BigDecimal result = convert(x).multiply(convert(y)).setScale(scale, RoundingMode.HALF_UP);
        String s = result.toString();
        undo.offerFirst(new Operation(OperationType.MULTIPLY, x, y , s));
        redo.clear();
        return s;
    }


    public String divide(String x, String y) {
        BigDecimal divisor = convert(y);
        if (divisor.equals(0)) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        BigDecimal result = convert(x).divide(convert(y),scale,RoundingMode.HALF_UP);
        String s = result.toString();
        undo.offerFirst(new Operation(OperationType.DIVIDE, x, y , s));
        redo.clear();
        return s;
    }

    public Operation undo() {

        if (undo.size() == 0) {
            throw new IllegalStateException("Cannot undo past the beginning");
        }
        redo.offerFirst(undo.peekFirst());
        return undo.pollFirst();
    }

    public Operation redo() {
        if (redo.size() == 0) {
            throw new IllegalStateException("Cannot redo past the end");
        }
        undo.offerFirst(redo.peekFirst());
        return redo.pollFirst();
    }

    public void reset() {
        redo.clear();
        undo.clear();
    }

    private BigDecimal convert(String num) {
        try {
            return new BigDecimal(num);
        }catch (Exception e){
            throw new IllegalArgumentException("Illegal Argument");
        }

    }

    class Operation {
        private OperationType type;
        private String x;
        private String y;
        private String result;

        public Operation(OperationType type, String x, String y , String result) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.result = result;
        }

        public OperationType getType() {
            return type;
        }

        public String getX() {
            return x;
        }

        public String getY() {
            return y;
        }

        public String getResult() {
            return result;
        }
    }

    enum OperationType {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}
