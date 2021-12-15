package com.pm.pmapi.common.utils;

import com.pm.pmapi.dto.CommodityParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.Timestamp;
import java.security.cert.CertPath;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: pmApi
 * @description: 解析带文件和多字段的form-data
 * @author: Shen Zhengyu
 * @create: 2021-12-15 16:11
 **/
public class FormDataUtil {
    UploadUtil uploadUtil = new UploadUtil();

    public Map<String, Object> handleOneFileUpload(HttpServletRequest request, String fileName) {
        HashMap<String, Object> result = new HashMap<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles(fileName);
        CommodityParam commodityParam = resolveCommodity(request);
        for (MultipartFile file : files) {
            String url = uploadUtil.uploadFile(file, String.valueOf(commodityParam.getId()));
            result.put("fileName", url);
        }
        result.put("commodityParam", commodityParam);
        return result;
    }

    public static <T> T getSingleRequest(HttpServletRequest request, Class<T> obj) {
        //创建实例
        T instance = null;
        try {
//获取类中声明的所有字段
            Field[] fields = obj.getDeclaredFields();
//创建新的实例对象
            instance = obj.newInstance();
//利用循环
            for (int i = 0; i < fields.length; i++) {
//获取字段的名称
                String name = fields[i].getName();
//把序列化id筛选掉
                if (name.equals("serialVersionUID")) {
                    continue;
                }
//获取字段的类型
                Class<?> type = obj.getDeclaredField(name).getType();

                // 首字母大写
                String replace = name.substring(0, 1).toUpperCase()
                        + name.substring(1);
//获得setter方法
                Method setMethod = obj.getMethod("set" + replace, type);
//获取到form表单的所有值
                String str = request.getParameter(replace);
                if (str == null || "".equals(str)) {
                    // 首字母小写
                    String small = name.substring(0, 1).toLowerCase()
                            + name.substring(1);
                    str = request.getParameter(small);
                }
//通过setter方法赋值给对应的成员变量
                if (str != null && !"".equals(str)) {
                    // ---判断读取数据的类型
                    if (type.isAssignableFrom(String.class)) {
                        setMethod.invoke(instance, str);
                    } else if (type.isAssignableFrom(int.class)
                            || type.isAssignableFrom(Integer.class)) {
                        setMethod.invoke(instance, Integer.parseInt(str));
                    } else if (type.isAssignableFrom(Double.class)
                            || type.isAssignableFrom(double.class)) {
                        setMethod.invoke(instance, Double.parseDouble(str));
                    } else if (type.isAssignableFrom(Boolean.class)
                            || type.isAssignableFrom(boolean.class)) {
                        setMethod.invoke(instance, Boolean.parseBoolean(str));
                    } else if (type.isAssignableFrom(Date.class)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        setMethod.invoke(instance, dateFormat.parse(str));
                    } else if (type.isAssignableFrom(Timestamp.class)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
//返回实体类对象
        return instance;
    }

    public CommodityParam resolveCommodity(HttpServletRequest request) {
        CommodityParam commodityParam = new CommodityParam();
        commodityParam = getSingleRequest(request,commodityParam.getClass());
        return commodityParam;
    }
}
