package cz.codecamp.intro;

public class ObjectTypes {

    public static class Person {

        String name;
        int age;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Karel";
        person.age = 23;
    }

}
