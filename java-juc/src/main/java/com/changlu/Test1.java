package com.changlu;

/**
 * @ClassName Test1
 * @Author ChangLu
 * @Date 5/18/2022 10:42 PM
 * @Description TODO
 */

interface Shape{
    void draw();
}

abstract class CircleBB{
    abstract void draw();
}

class Circle extends cir{
    public void draw() {
        //System.out.println("circle");
    }
    public static void main(String[] args) {
        final Circle circle = new Circle();
        circle.draw();
    }
}



public class Test1 {

}
