package cx.study.auction.util;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by AMOBBS on 2017/2/4.
 */
public class RequestUtil {
    public static Integer getInteger(HttpServletRequest request,String parameter){
        String param = request.getParameter(parameter);
        return StringUtils.isBlank(param)? null :NumberUtils.createInteger(param);
    }
    public static Date getDate(HttpServletRequest request, String parameter) throws ParseException {
        String param = request.getParameter(parameter);
        String[] patterns = {"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss"};
        return StringUtils.isBlank(param)? null : DateUtils.parseDate(param,patterns);
    }
    public static String getString(HttpServletRequest request,String parameter){
        return request.getParameter(parameter);
    }

    public static float getFloat(HttpServletRequest request,String parameter){
        String param = request.getParameter(parameter);
        return StringUtils.isBlank(param)? 0f :NumberUtils.createFloat(param);
    }

    public static double getBigDecimal(HttpServletRequest request, String parameter){
        String param = request.getParameter(parameter);
        return StringUtils.isBlank(param)? 0d :NumberUtils.createDouble(param);
    }
}
