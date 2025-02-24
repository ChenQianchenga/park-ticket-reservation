package com.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//http://localhost:8080/admin/login

@Slf4j
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement // 开启事务管理
public class ReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
        log.info("项目启动成功...");
    }
}
