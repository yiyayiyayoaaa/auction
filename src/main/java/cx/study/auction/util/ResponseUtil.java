package cx.study.auction.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by chengxiao on 2017/1/2
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
