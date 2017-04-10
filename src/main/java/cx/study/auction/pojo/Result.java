package cx.study.auction.pojo;

/**
 *
 * Created by cheng.xiao on 2017/4/10.
 */
public class Result<T> {
    private int code;
    private String msg;
    private T obj;

    public Result(int code,String msg,T obj){
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
