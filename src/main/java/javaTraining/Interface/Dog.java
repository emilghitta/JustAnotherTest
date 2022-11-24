package javaTraining.Interface;

public class Dog implements iAnimal{

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping");
    }

    @Override
    public void walk() {
        System.out.println("Dog is walking");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating dog food");
    }

    @Override
    public void makeNoise() {
        System.out.println("Dog is barking");
    }

    public void hateCats(){
        System.out.println("Dog hates cats");
    }
}
