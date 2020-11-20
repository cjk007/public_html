package com.jk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import com.jk.sys.service.AccountService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", accountService);/*加注释，测试jenkins+github*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();//new BCryptPasswordEncoder();//  ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(true)
//                .inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("ADMIN")
//            .eraseCredentials(true)

            .userDetailsService(accountService)
            .passwordEncoder(passwordEncoder());
//        auth.authenticationProvider();
      }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
            .csrf().disable()
            .authorizeRequests()
               .antMatchers("/", "/favicon.ico", "/resources/**", "/signup", "/test/**", "/signin").permitAll()
               .antMatchers("/sys/user/**").hasAnyRole("USER","ADMIN")
               .anyRequest().authenticated()
                .and()
           .formLogin()
               .loginPage("/signin")
               .permitAll()
               .failureUrl("/signin?error=1")
               .loginProcessingUrl("/authenticate")
               .successForwardUrl("/index")
              .and()
               .headers().frameOptions().sameOrigin()
               .and()
           .logout()
               .logoutUrl("/logout")
                .permitAll()
               .logoutSuccessUrl("/signin?logout")
               .and()
           .rememberMe()
              .rememberMeServices(rememberMeServices())
               .key("remember-me-key")
        ;
    }


    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}