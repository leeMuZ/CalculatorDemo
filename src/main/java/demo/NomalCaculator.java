package demo;

/**
 *计算器类（Calculator），可以实现两个数的加、减、乘、除运算，并可以进行undo和redo操作
 * 链式调用版
 */

public class NomalCaculator extends AbstractCalculator{

    private String lastResult = "0";

    public NomalCaculator(){}

    public NomalCaculator(int scale) {
        super.scale = scale;
    }

    public NomalCaculator add(String x) {
        if(!super.getUndo().isEmpty()){
            lastResult =  super.getUndo().peekFirst().getResult();
        }

        lastResult = super.add(lastResult, x);
        return this;
    }

    public NomalCaculator subtract(String x) {
        if(!super.getUndo().isEmpty()){
            lastResult =  super.getUndo().peekFirst().getResult();
        }
        lastResult = super.subtract(lastResult, x);
        return this;
    }

    public NomalCaculator multiply(String x) {

        if(!super.getUndo().isEmpty()){
            lastResult =  super.getUndo().peekFirst().getResult();
        }

        lastResult = super.multiply(lastResult, x);

        return this;
    }

    public NomalCaculator divide(String x) {

        if(!super.getUndo().isEmpty()){
            lastResult =  super.getUndo().peekFirst().getResult();
        }

        lastResult = super.divide(lastResult, x);

        return this;
    }

    public String getResult() {

        if(!super.getUndo().isEmpty()){
            lastResult =  super.getUndo().peekFirst().getResult();
        }

        return lastResult;
    }

    public static void main(String[] args) {
        NomalCaculator caculator = new NomalCaculator(10);
//        String num1 = caculator.divide("12", "11");
//        String num2 = caculator.add(num1, "10.12123");
//        String num3 = caculator.multiply(num2, "2");
//        String num4 = caculator.subtract(num3, "10");
//        Operation undo = caculator.undo();
//        Operation undo1 = caculator.undo();
//        Operation undo2 = caculator.undo();
//        Operation redo = caculator.redo();
//        caculator.multiply(num2,"4");
//        Operation redo2 = caculator.redo();

        caculator.add("-10.124").multiply("2").add("30");
        caculator.subtract("12.112");
        caculator.undo();
        caculator.undo();
        caculator.redo();
        caculator.divide("2");
        System.out.println(caculator.getResult());
        caculator.redo();
    }


}
