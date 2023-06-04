package demo;


public class NomalCaculator extends AbstractCalculator{

    String lastResult = "0";

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
