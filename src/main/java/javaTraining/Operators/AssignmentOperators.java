package javaTraining.Operators;

public class AssignmentOperators {
    public static void main(String[] args){
        int a = 10; // Simple assignment using the =
        System.out.println(addAndAssign(2,2));
        System.out.println(subtractAndAssign(2,2));
        System.out.println(multiplyAndAssign(2,2));
        System.out.println(divideAndAssign(4,2));
        System.out.println(moduloAndAssign(5,2));
    }

    public static int addAndAssign(int x, int y){
        x += y;
        return x;
    }

    public static int subtractAndAssign(int x, int y){
        x -= y;
        return x;
    }

    public static int multiplyAndAssign(int x, int y){
        x *= y;
        return x;
    }
    public static int divideAndAssign(int x, int y){
        x /= y;
        return x;
    }
    public static int moduloAndAssign(int x, int y){
        x %= y;
        return x;
    }

}
