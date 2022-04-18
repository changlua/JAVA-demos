package xyz.changlu.factory.method;

/**
 * @ClassName BMDFactory
 * @Author ChangLu
 * @Date 2021/3/16 21:13
 * @Description TODO
 */
public class BMDFactory implements Factory{
    @Override
    public Car getCar() {
        return new BMD();
    }
}
