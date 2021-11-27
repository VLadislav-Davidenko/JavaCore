package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Thread.sleep;

public class Race {
    public static AtomicLong startRaceTime = new AtomicLong(System.currentTimeMillis());
    public static void main(String[] args) {

        CountDownLatch count = new CountDownLatch(3);

        ArrayList<RaceCarRunnable> cars = new ArrayList<>();
        RaceCarRunnable car1 = new RaceCarRunnable(400, "Toyota", 230, count);
        cars.add(car1);
        RaceCarRunnable car2 = new RaceCarRunnable(580, "Honda", 210, count);
        cars.add(car2);
        RaceCarRunnable car3 = new RaceCarRunnable(390, "BMW", 280, count);
        cars.add(car3);


        ArrayList<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(car1));
        threads.add(new Thread(car2));
        threads.add(new Thread(car3));

        startRace(threads);
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (RaceCarRunnable car : cars) {
            if (car.isFinish) {
                System.out.println(car.getName() + " FINISHED!!!");
                System.out.println(startRaceTime);
                System.out.println(System.currentTimeMillis());
            }
        }
            if(car1.finishTime < car2.finishTime && car1.finishTime < car3.finishTime && car2.finishTime < car3.finishTime){
                System.out.println(car1.getName() + " - 1 place");
            } else if(car1.finishTime > car2.finishTime && car1.finishTime > car3.finishTime && car2.finishTime < car3.finishTime){
                System.out.println(car2.getName() + " - 1 place");
            } else{
                System.out.println(car3.getName() + " - 1 place");
            }


    }

    public static void startRace(List<Thread> threads){
        Thread ptk1 = new Thread(() -> {
                    try {
                        for (int i = 3; i > 0; i--) {
                            sleep(500);
                            System.out.println(i + "...");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                System.out.println("GO!!!");
                for (Thread thread : threads) {
                    startRaceTime.get();
                    thread.start();
                }
        });
        ptk1.start();
    }
}
