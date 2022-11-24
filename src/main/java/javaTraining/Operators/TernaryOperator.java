package javaTraining.Operators;

public class TernaryOperator {
    public static void main(String[] args){
        int n1 = 2;
        int n2 = 3;

        int max = (n1 > n2) ? 5 :4;
        System.out.println(max);

        int max2 = (n1 < n2) ? 5 :4;
        System.out.println(max2);
    }
}
