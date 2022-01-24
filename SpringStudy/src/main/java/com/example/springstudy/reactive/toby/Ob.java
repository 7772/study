package com.example.springstudy.reactive.toby;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Ob {

    public static void main(String[] args) {
        // Pull 방식
        Iterable<Integer> iter = () -> new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < 10;
            }

            @Override
            public Integer next() {
                return i++;
            }
        };

        for (int i : iter) {
            System.out.println("------------ Push -------------");
            System.out.println(i);
        }

//        for (Iterator<Integer> it = iter.iterator(); it.hasNext(); ) {
//            System.out.println(it.next());
//        }

        // Push 방식
        Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("------------ Pull -------------");
                System.out.println(arg);
            }
        };

        IntObservable observable = new IntObservable();
        observable.addObserver(observer);
        observable.run();
    }

    static class IntObservable extends Observable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                setChanged();
                notifyObservers(i);
            }
        }
    }
}
