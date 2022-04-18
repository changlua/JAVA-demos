package xyz.changlu.factory.method;

/**
 * @ClassName PorscheFactory
 * @Author ChangLu
 * @Date 2021/3/16 21:13
 * @Description TODO
 */
public class PorscheFactory implements Factory{
    @Override
    public Car getCar() {
        return new Porsche();
    }
}
