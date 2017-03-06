package cx.study.auction.action;

import com.google.gson.Gson;
import cx.study.auction.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * Created by AMOBBS on 2017/2/18.
 */
@Controller
public class UploadAction {

    @RequestMapping("/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response,@RequestParam("files[]")MultipartFile upFile) throws Exception{
        String path = request.getSession().getServletContext().getRealPath("file");
        String filename = upFile.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID().toString().replace("-","") + type;
        File file = new File(path,filename);
        if(!file.exists()){
            boolean mkdirs = file.mkdirs();
        }
        upFile.transferTo(file);
        Map<String,String> map = new HashMap<>();

        map.put("result","file/" + filename);
        ResponseUtil.writeJson(response,new Gson().toJson(map));
    }
}
