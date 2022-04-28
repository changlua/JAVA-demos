package xyz.changlu.adapter;

import org.junit.Test;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/17 21:20
 * @Description TODO
 */
public class Customer {
    //①测试继承方式的适配器
    public static void main(String[] args) {
        Computer computer = new Computer();//电脑
        Reticle reticle = new Reticle();//网线
        NetToUSB adapter = new Adapter();//适配器

        computer.net(adapter);
    }

    //②测试组合方式的适配器
    @Test
    public void test01(){
        Computer computer = new Computer();//电脑
        Reticle reticle = new Reticle();//网线
        //将网线传入到适配器中(组合方式)
        NetToUSB adapter = new Adapter2(reticle);
        computer.net(adapter);
    }

}
