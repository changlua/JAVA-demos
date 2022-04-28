package xyz.changlu.strategy.demo2;

/**
 * @ClassName HeadAttach
 * @Author ChangLu
 * @Date 2021/3/20 23:35
 * @Description TODO
 */
//头部撞击攻击
public class HeadAttach implements Attachable{
    @Override
    public void attach() {
        System.out.println("头撞攻击");
    }
}
