package javaTraining.Interface;

public class Cat implements iAnimal{
    @Override
    public void sleep() {
        System.out.println("Cat sleeps");
    }

    @Override
    public void walk() {
        System.out.println("Cat walks");
    }

    @Override
    public void eat() {
        System.out.println("Cat eats");
    }

    @Override
    public void makeNoise() {
        System.out.println("Cat meows");
    }

    public void chasesMouse(){
        System.out.println("Cat chases mouse");
    }
}
