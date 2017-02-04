package cx.study.auction.pojo;

/**
 * Created by AMOBBS on 2017/2/4.
 */
public class ResponseMessage<T> {
    int resultCode;
    T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
