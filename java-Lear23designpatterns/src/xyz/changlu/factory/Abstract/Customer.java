package xyz.changlu.factory.Abstract;

/**
 * @ClassName Customer
 * @Author ChangLu
 * @Date 2021/3/16 0:09
 * @Description TODO
 */
public class Customer {

    public static void main(String[] args) {
        //获取华为工厂
        Brand huawei = new HuaWeiFactory();
        Iphone phone = huawei.getPhone();//华为工厂获取华为手机
        phone.start();
        phone.music();
        phone.ring();
        phone.stop();
        Router router = huawei.getRouter();//华为工厂获取华为路由器
        router.start();
        router.work();
        router.stop();
        //----------------------
        System.out.println();
        Brand lianxiang = new LianXiangFactory();
        Iphone phone1 = lianxiang.getPhone();//华为工厂获取华为手机
        phone1.start();
        phone1.music();
        phone1.ring();
        phone1.stop();
        Router router1 = lianxiang.getRouter();//华为工厂获取华为路由器
        router1.start();
        router1.work();
        router1.stop();
    }
}
