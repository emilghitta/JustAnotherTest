package javaTraining.Interface;

public class EntryPoint {
    public static void main(String[] args){
        Dog dog1 = new Dog();
        dog1.hateCats();

        Cat cat1 = new Cat();
        cat1.chasesMouse();

        iAnimal dog2 = new Dog();
        dog2.sleep();

        iAnimal cat2 = new Cat();
        cat2.sleep();
    }
}
