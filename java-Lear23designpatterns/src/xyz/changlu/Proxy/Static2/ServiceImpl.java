package xyz.changlu.Proxy.Static2;

/**
 * @ClassName ServiceImpl
 * @Author ChangLu
 * @Date 2021/3/17 23:48
 * @Description TODO
 */
public class ServiceImpl implements Service{

    @Override
    public void query() {
        System.out.println("查询操作");
    }

    @Override
    public void update() {
        System.out.println("修改操作");
    }

    @Override
    public void delete() {
        System.out.println("删除操作");
    }
}
