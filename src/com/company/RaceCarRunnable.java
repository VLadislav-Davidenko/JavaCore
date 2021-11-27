package com.company;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

public class RaceCarRunnable extends Car{
    int passed;
    int distance;
    boolean isFinish;
    CountDownLatch count;
    long finishTime;

    public RaceCarRunnable(int distance, String name, int maxSpeed, CountDownLatch count) {
        this.distance = distance;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.count = count;
    }
    public int getRandomSpeed(){
        Random random = new Random();
        return random.nextInt(maxSpeed/2);
    }

    @Override
    public String toString() {
        return "RaceCarRunnable{" +
                "name='" + name + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", passed=" + passed +
                ", distance=" + distance +
                ", isFinish=" + isFinish +
                '}';
    }

    public int getPassed() {
        return passed;
    }

    public int getDistance() {
        return distance;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public long getFinishTime() {
        return finishTime;
    }

    @Override
    public void run() {
      while(!isFinish){
          try {
              sleep(1000);
              passed += getRandomSpeed() / 3.6;
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println(getName() + " => speed: " + getRandomSpeed() + " progress " + getPassed() + "/" + getDistance() );
          if(passed >= distance){
              isFinish = true;
              count.countDown();
          }
      }
    }
}

