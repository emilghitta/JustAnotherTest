package javaTraining.Operators;

public class ArithmeticOperators {
    public static void main(String[] args) {
        System.out.println(addTwonumbers(2, 2));
        System.out.println(subtractTwNumbers(2,2));
        System.out.println(multiplyTwoNumbers(2,2));
        System.out.println(divideTwoNumbers(8,2));
        System.out.println(moduloTwoNumbers(5,2));
    }


    public static int addTwonumbers(int x, int y){
        return x + y;
    }

    public static int subtractTwNumbers(int x, int y){
        return x - y;
    }

    public static int multiplyTwoNumbers(int x, int y){
        return x * y;
    }

    public static int divideTwoNumbers(int x, int y){
        return x / y;
    }

    public static int moduloTwoNumbers(int x, int y){
        return x % y;
    }
}
