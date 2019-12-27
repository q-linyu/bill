package priv.linyu.bill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: App
 * @author: QiuShangLin
 * @description: 程序入口
 * @date: 2019/12/15 11:20
 * @version: 1.0
 **/
@SpringBootApplication
@MapperScan(basePackages = "priv.linyu.bill.core.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
