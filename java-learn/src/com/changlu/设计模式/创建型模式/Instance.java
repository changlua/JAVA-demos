package com.changlu.设计模式.创建型模式;

/**
 * @ClassName Instance
 * @Author ChangLu
 * @Date 4/24/2022 11:39 PM
 * @Description 内部静态类模式
 */
public class Instance {

    private Instance(){}

    public static Instance getInstance() {
        return Holder.instance;
    }

    public static class Holder {
        private static Instance instance = new Instance();
        //可以放开继续测试
//        static {
//            System.out.println("666");
//        }
    }

    public static void main(String[] args) {
        //懒加载：只有当真正去获取实例时才会去触发Holder初始化
        System.out.println(Instance.getInstance());
    }

}
