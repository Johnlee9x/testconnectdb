package data;

public class Student {
    private int id;
    private String name;
    private int age;
    public Student(){
    }
    public Student(int id, String name, int age){
        this.name = name;
        this.id = id;
        this.age = age;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public int getAge(){
        return this.age;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String toString(){
        return "ID: " + this.id + ", " + "Ten: " + this.name + ", " + "Tuoi: " + this.age;
    }
}
