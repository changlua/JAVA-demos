package xyz.changlu.factory.Abstract;

/**
 * @ClassName HuaWeiFactory
 * @Author ChangLu
 * @Date 2021/3/16 0:07
 * @Description TODO
 */
public class HuaWeiFactory implements Brand{
    @Override
    public Iphone getPhone() {
        return new HuaWeiPhone();
    }

    @Override
    public Router getRouter() {
        return new HuaWeiRouter();
    }
}
