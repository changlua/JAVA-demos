package xyz.changlu.strategy.demo2;

/**
 * @ClassName BiteAttach
 * @Author ChangLu
 * @Date 2021/3/20 23:34
 * @Description TODO
 */
//咬攻击
public class BiteAttach implements Attachable{
    @Override
    public void attach() {
        System.out.println("咬攻击方式！");
    }
}
