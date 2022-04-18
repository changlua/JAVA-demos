package xyz.changlu.chain;

/**
 * @ClassName Handler
 * @Author ChangLu
 * @Date 2021/3/20 19:13
 * @Description TODO
 */
//抽象处理者
public abstract class Handler {

    private Handler next;

    public Handler(Handler handler){
        this.next = handler;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    abstract boolean process(Request request);

}
