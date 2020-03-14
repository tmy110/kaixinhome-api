package com.kaixin8848.home.configurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.kaixin8848.home.core.ServiceException;
import com.kaixin8848.home.utility.Log4J2.LogHelper;
import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Spring MVC 配置
 */

/**
 * 知识补充:
 *
 * @Value
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件

    //转换器
    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //消息转换对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullListAsEmpty,//List字段如果为null,输出为[],而非null
                SerializerFeature.WriteMapNullValue,//保留map空的字段
                SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullBooleanAsFalse,// 将Boolean类型的NULL转化为false
                SerializerFeature.WriteNullNumberAsZero,// 将Number类型的NULL转化为0
                SerializerFeature.DisableCircularReferenceDetect//消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环
        );
        //设置日期格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //long转String
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.ALL); // 全部格式
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(fastConverter);

        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setDefaultCharset(Charset.forName("UTF-8"));
        fastConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        converters.add(fastConverter);
    }

    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                    LogHelper.info(e.getMessage(), "", "", "", "");
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    LogHelper.error(e, "", "", "", "");
                }
                responseResult(response, result);
                return new ModelAndView();
            }
        });
    }

    /**
     * 解决跨域问题
     * 跨域概念：客户端和服务端只要“协议”、“域名”、“端口”任何一个不一样，都表示跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (!"dev".equals(env)) { //开发环境忽略
                    return loginAuthentication(request, response, handler);//Session登录身份验证
                }
                return true;
            }
        }).addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/swagger-ui.html")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/swagger-ui.html/**")
                .excludePathPatterns("/qiyuesuo/**")//不拦截契约相关接口
                .excludePathPatterns("/static/asserts/**");
    }

    /**
     * Session登录身份验证
     * 知识普及
     * 第一:基于Session登录(服务器端使用Session技术，浏览器端使用Cookie技术。)
     * 1.服务端session是用户第一次访问应用时，服务器就会创建的对象，代表用户的一次会话过程，可以用来存放数据。服务器为每一个session都分配一个唯一的sessionid，以保证每个用户都有一个不同的session对象。
     * 2.服务器在创建完session后，会把sessionid通过cookie返回给用户所在的浏览器，这样当用户第二次及以后向服务器发送请求的时候，就会通过cookie把sessionid传回给服务器，以便服务器能够根据sessionid找到与该用户对应的session对象。
     * 3.session通常有失效时间的设定，比如2个小时。当失效时间到，服务器会销毁之前的session，并创建新的session返回给用户。但是只要用户在失效时间内，有发送新的请求给服务器，通常服务器都会把他对应的session的失效时间根据当前的请求时间再延长2个小时。
     * 4.session在一开始并不具备会话管理的作用。它只有在用户登录认证成功之后，并且往sesssion对象里面放入了用户登录成功的凭证，才能用来管理会话。管理会话的逻辑也很简单，只要拿到用户的session对象，看它里面有没有登录成功的凭证，就能判断这个用户是否已经登录。当用户主动退出的时候，会把它的session对象里的登录凭证清掉。所以在用户登录前或退出后或者session对象失效时，肯定都是拿不到需要的登录凭证的。
     * 第二:基于Token登录
     * 1.用户在浏览器中输入用户和密码，后台服务器通过加密或者其他逻辑，生成一个Token。
     * 2.前端获取到Token，存储到cookie或者localStorage中，在接下来的请求中，将token通过url参数或者HTTP Header头部传入到服务器
     * 3.服务器获取token值，通过查找数据库判断当前token是否有效
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    private boolean loginAuthentication(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Result result = new Result();

        //在Header中获取session
        String token = request.getHeader("token");
        //在url路径中获取session
        if (request.getParameter("sid") != null) {
            token = request.getParameter("sid");
        }

        //在Cookie中获取session
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            setResultVO(result);
            responseResult(response, result);
            return false;
        }

        for (int i = 0; i < cookies.length; i++) {
            if (("JSESSIONID").equalsIgnoreCase(cookies[i].getName())) {
                token = cookies[i].getValue();//URLDecoder.decode(cookies[i].getValue(), "UTF-8");
            }
        }

        if (token == null) {
            setResultVO(result);
            responseResult(response, result);
            return false;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            setResultVO(result);
            responseResult(response, result);
            return false;
        }

        Object sessionToken = session.getId();
        if (sessionToken == null) {
            setResultVO(result);
            responseResult(response, result);
            return false;
        } else {
            if (!sessionToken.equals(token)) {
                setResultVO(result);
                responseResult(response, result);
                return false;
            }
        }

        return true;
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            LogHelper.error(ex, "", "", "", "");
        }
    }

    private Result setResultVO(Result result) {
        result.setCode(ResultCode.UNAUTHORIZED).setMessage("请先登录");
        return result;
    }

    /**
     * 重写addResourceHandlers解决resources下面静态资源无法访问
     * <p>
     * 第一:webjars:以jar包的方式引入静态资源，参考:https://www.webjars.org/
     * 第二:所有的/webjars/**，都去classpath:/META-INF/resources/webjars/找资源
     * 例如:
     * （1）引入jquery-webjars
     * <dependency>
     * <groupId>org.webjars</groupId>
     * <artifactId>jquery</artifactId>
     * <version>3.4.1</version>
     * </dependency>
     * （2）http://localhost:8080/kaixinhome-api/webjars/jquery/3.4.1/jquery.js
     * 第三:访问项目资源 http://localhost:8080/kaixinhome-api/static/asserts/img/bootstrap-solid.svg
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问swagger-ui资源
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        //访问项目资源 例如http://localhost:8080/kaixinhome-api/static/asserts/img/bootstrap-solid.svg
        registry.addResourceHandler("/static/asserts/**")
                .addResourceLocations("classpath:/static/asserts/");
        //访问webjar静态资源 例如http://localhost:8080/kaixinhome-api/webjars/jquery/3.4.1/jquery.js
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
//待研究 视图解析器，Formatter格式化器
//    @Override
//    protected void addViewControllers(ViewControllerRegistry registry) {
//        //浏览器发送/getBase请求来到base页面
//        registry.addViewController("/getBase").setViewName("base");
//    }

}
