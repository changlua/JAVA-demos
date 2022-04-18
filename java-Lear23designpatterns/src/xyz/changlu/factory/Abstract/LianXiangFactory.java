package xyz.changlu.factory.Abstract;

/**
 * @ClassName HuaWeiFactory
 * @Author ChangLu
 * @Date 2021/3/16 0:07
 * @Description TODO
 */
public class LianXiangFactory implements Brand{
    @Override
    public Iphone getPhone() {
        return new LianXiangPhone();
    }

    @Override
    public Router getRouter() {
        return new LianXiangRouter();
    }
}
