package xyz.changlu.factory.simple;

/**
 * @ClassName CarFactory
 * @Author ChangLu
 * @Date 2021/3/16 21:19
 * @Description TODO
 */
//工厂类
public class CarFactory {

    //根据汽车名称来获取指定车型实例
    public static Car getCar(String carName){
        if(carName.equals("宝马")){
            return new BMW();
        }else if(carName.equals("保时捷")){
            return new Porsche();
        }
        return null;
    }
}
