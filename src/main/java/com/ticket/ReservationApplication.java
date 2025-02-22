package com.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
//http://localhost:8080/admin/login

@Slf4j
@ServletComponentScan
@SpringBootApplication
public class ReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
        log.info("项目启动成功...");
    }
}
