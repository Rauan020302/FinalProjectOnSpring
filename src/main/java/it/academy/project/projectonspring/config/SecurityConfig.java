package it.academy.project.projectonspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/api/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/users/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/users/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/users/**").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/api/user-role/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/user-role/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/user-role/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/user-role/**").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/api/garden/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/garden/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/garden/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/garden/**").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/api/image/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/image/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/image/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/image/**").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/api/group/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/group/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/group/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/group/**").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/api/course_group/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/course_group/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/course_group/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/course_group/**").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/api/course/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/course/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/course/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/course/**").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/api/child/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/child/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/child/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/child/**").permitAll()
//
//                .antMatchers(HttpMethod.POST,"/api/calendar/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/api/calendar/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/api/calendar/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/calendar/**").permitAll()
                .and().httpBasic().and().logout().and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login, user_password, status from users where login = ?")
                .authoritiesByUsernameQuery("select u.login,ur.role_name from user_role ur join users u on ur.user_id = u.id where u.login = ? and status = 1");
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }




    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
