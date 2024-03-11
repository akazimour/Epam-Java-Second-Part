package com.epam.rd.autotasks.house;

import com.epam.rd.autotasks.residents.cats.Cat;

import com.epam.rd.autotasks.residents.cats.Kitten;
import com.epam.rd.autotasks.residents.dogs.Dog;
import com.epam.rd.autotasks.residents.dogs.Puppy;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class House <T>{
    T value;
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    private final List<T> residents = new ArrayList();
    public void enter(T resident) {
    setValue(resident);
        residents.add(resident);
        checkElements(residents);
    }


    private void checkElements(List<T> residents) {
        boolean isDog = false;
        for (Object o : residents){
            if (o instanceof Dog){
            isDog=true;
            }
        }
        if (isDog==true){
            Iterator iterator = residents.iterator();
            while (iterator.hasNext()){
                Object next = iterator.next();
                if (next instanceof Cat){
                    iterator.remove();
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("There are following residents in the house:\n");
        for (Object resident : residents) {
            builder.append(resident.toString()).append("\n");
        }
        return builder.toString();
    }

}
