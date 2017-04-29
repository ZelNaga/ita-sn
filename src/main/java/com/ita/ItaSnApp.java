package com.ita;

import com.ita.entities.Account;
import com.ita.entities.Post;
import com.ita.repositories.PostRepository;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.filter.GenericFilterBean;
import com.ita.repositories.AccountsRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by asv on 18.03.17.
 */

@SpringBootApplication
@EnableResourceServer
public class ItaSnApp {

    //entry point of application
    public static void main(String[] args) {

        SpringApplication.run(ItaSnApp.class);

    }


    //this method creates fake data just for application development and should be removed in production
    @Bean
    public CommandLineRunner loadData(AccountsRepository accountsRepository, PostRepository postRepository) {

        return (args) -> {

            /*Arrays.asList("andrew,dima".split(","))
                    .forEach(a -> accountsRepository.saveAndFlush(new Account(a, "password")));*/

            Account account = accountsRepository.saveAndFlush(new Account("l", "p", "Test name"));
            List<Post> posts = IntStream.range(1, 100).boxed()
                    .map(i -> new Post(account, "Post" + i))
                    .collect(Collectors.toList());

            postRepository.save(posts);
        };

    }

    //Logs all http client requests and server responses
    @Bean
    public FilterRegistrationBean requestDumperFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter requestDumperFilter = new RequestDumperFilter();
        registration.setFilter(requestDumperFilter);
        registration.addUrlPatterns("/*");
        return registration;

    }

    //method that returns  CORS filter to handle some browsers preflight OPTION request
    @Bean
    FilterRegistrationBean corsFilter() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new GenericFilterBean() {

            public void doFilter(ServletRequest req, ServletResponse res,
                                 FilterChain chain) throws IOException, ServletException {

                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;

                String method = request.getMethod();

                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
                response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Headers",
                        "X-Requested-With,Content-Type,Access-Control-Request-Method," +
                                "Origin,Accept,Access-Control-Request-Headers,Authorization");

                if ("OPTIONS".equals(method)) {

                    response.setStatus(HttpStatus.OK.value());

                } else {

                    chain.doFilter(req, res);

                }

            }

        });

        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return filterRegistrationBean;
    }

}
