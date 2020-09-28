package com.damin;

public class User {
    public int age;
    public String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        if (user.age == this.age && user.name.equals(this.name)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + age;
        result = 31 * result + name.hashCode();
        return result;
    }
}