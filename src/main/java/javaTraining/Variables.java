package javaTraining;

public class Variables {
    String myName = "emil";
    static String familyName = "Ghitta";

    public static void main(String[] args){
        primitiveDataTypes();
        nonPrimitiveDataTypes();
        Variables myVariablesObject = new Variables();
        System.out.println(myVariablesObject.myName);
        System.out.println(Variables.familyName);
    }

    public static void primitiveDataTypes(){
        // All variables below are local variables
        byte myByte = 1;
        short myShort = 23;
        int myInt = 2141;
        long myLong = 23322L;
        double myDouble = 3.2;
        float myFloat = 3.4f;
        char myChar = 'c';
        boolean isTrue = true;

        System.out.println("Primitive data types are: byte, short, int , long, double, float ,char and boolean");
    }

    public static void nonPrimitiveDataTypes(){
        //This string is also a local variable
        String h ="haha";
        System.out.println("Non-primitive data types are: Arrays, Class, Object, Interface, String");
    }
}
