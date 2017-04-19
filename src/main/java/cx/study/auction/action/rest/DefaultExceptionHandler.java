package cx.study.auction.action.rest;

import com.google.gson.Gson;
import cx.study.auction.pojo.Result;
import cx.study.auction.util.ResponseUtil;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * rest接口统一异常处理
 * Created by cheng.xiao on 2017/4/19.
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        Result result = new Result<String>(-22,"请求失败",null);
        try {
            ResponseUtil.writeJson(response,new Gson().toJson(result));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mv;
    }
}
