package cn.edu.cdu.yhy;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@MapperScan("cn.cdu.edu.yhy.mapper")
public class PetApplication{

    public static void main(String[] args) {
        SpringApplication.run(PetApplication.class, args);
    }


}
