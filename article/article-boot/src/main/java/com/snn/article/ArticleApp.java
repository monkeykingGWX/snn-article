package com.snn.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Gwx@704835519@qq.com
 * @create 2022-06-27 11:41
 */

@MapperScan("com.snn.article.dao")
@SpringBootApplication
public class ArticleApp {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApp.class, args);
    }
}
