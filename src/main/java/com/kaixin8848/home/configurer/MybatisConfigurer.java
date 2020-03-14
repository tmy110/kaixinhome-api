package com.kaixin8848.home.configurer;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

import static com.kaixin8848.home.core.ProjectConstant.*;

/**
 * Mybatis & Mapper & PageHelper 配置
 */

/**
 * 知识补充:
 *
 * @Bean:将方法的返回值添加到容器中；容器中的这个组件默认的id就是方法名
 * @ConfigurationProperties:获取application.properties 或 application.yml 文件中参数值，前缀定义了哪些外部属性将绑定到类的字段上
 */
@Configuration
public class MybatisConfigurer {

    //访问Druid数据源http://127.0.0.1:8080/druid/index.html
    @Primary
    @Bean(name = "myDataSource")//默认情况下bean的名称和方法名称相同，你也可以使用name属性来指定
    @Qualifier("myDataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 第一:@Primary和@Qualifier用法 我们自动注入的时候，如果有多个候选实现类的bean，spring boot启动的时候就不知道该选择哪个bean进行注入，因此会报错。
     * 使用@Primary可以指定一个首选Bean注入；使用@Qualifier可以在自动注入的地方通过传入一个限定名（也就是类的别名）来选取指定的实现类，只不过必须与类的别名一致（如果不传限定名，就使用类的默认别名）
     * 如果既不用@Primary也不用@Qualifier，那就必须在自动注入的时候直接以类的默认别名来命名。
     * 给（spring ioc容器管理的）类设置别名的方式是：@Service("别名")、@Component("别名") 、@Bean("别名") 等等。
     * 第二:SqlSession:与数据库的一次会话
     */
    @Primary
    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory mySqlSessionFactory(@Qualifier("myDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
//        factory.setTypeAliasesPackage(MODEL_PACKAGE);
        factory.setTypeAliasesPackage("com.kaixin8848.home.web.*.model");
        //配置分页插件，详情请查阅官方文档
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("reasonable", "true");//页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
        pageHelper.setProperties(properties);

        //添加插件
        factory.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:mapper/*/*.xml"));
        return factory.getObject();
    }

    /**
     * 数据源事务管理器
     * @param dataSource
     * @return
     */
    @Primary
    @Bean(name = "myTransactionManager")
    public DataSourceTransactionManager myTransactionManager(@Qualifier("myDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean
    public MapperScannerConfigurer myMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("mySqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage(MAPPER_PACKAGE);
        mapperScannerConfigurer.setBasePackage("com.kaixin8848.home.web.*.dao");
        //配置通用Mapper，详情请查阅官方文档
        Properties properties = new Properties();
        properties.setProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        properties.setProperty("notEmpty", "false");//insert、update是否判断字符串类型!='' 即 test="str != null"表达式内是否追加 and str != ''
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}

