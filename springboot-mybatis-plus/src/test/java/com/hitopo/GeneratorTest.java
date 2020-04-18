package com.hitopo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hitopo
 * @version v1.0
 * @classname GeneratorTest
 * @time 2020/4/18 17:32
 * @description 自动生成测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GeneratorTest {

    @Test
    public void testGenerator() {
        // 创建代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(true) // 支持AR模式
                .setAuthor("hitopo") // 设置作者
                .setOutputDir("C:\\Users\\Administrator\\Desktop\\springboot-learn\\springboot-mybatis-plus\\src\\main\\java") // 输出主目录
                .setFileOverride(true) // 覆盖文件
                .setIdType(IdType.AUTO) //  主键类型
                .setServiceName("%sService") // 生成的service名称，默认是IEmployeeService，这样改成EmployeeService
                .setBaseResultMap(true) // 设置XML文件中生成基本的ResultMap
                .setBaseColumnList(true); //  设置生成sql片段
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("root")
                .setUrl("jdbc:mysql://127.0.0.1:3306/springboot-mybatis?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        generator.setDataSource(dataSourceConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true) // 全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setTablePrefix("mp_") // 表的前缀
                .setInclude("mp_employee"); // 设置需要生成的表
        generator.setStrategy(strategyConfig);

        // 包名的生成配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.hitopo.mpg") // 父包名
                .setMapper("mapper") // Dao层包名
                .setService("service") // service层包名
                .setController("controller") // controller层包名
                .setEntity("beans") // 实体类包名
                .setXml("mapper"); // 映射文件位置
        generator.setPackageInfo(packageConfig);

        // 执行生成器
        generator.execute();
    }
}
