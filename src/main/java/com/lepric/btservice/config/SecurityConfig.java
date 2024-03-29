package com.lepric.btservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lepric.btservice.filter.JwtFilter;
import com.lepric.btservice.model.User;
import com.lepric.btservice.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    public User GetAuthenticatedUser() {
        return userDetailsService.GetUser(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(
                "/auth/authenticate",
                "/users/register",
                "/version",
                "/api-docs/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/busses/**/setActive",
                "/busses/**/getActive",
                "/users/payment/**/bus/**",
                "/location/bus/**"
                
                )
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        ;
    }
    /*
     * @Bean
     * public RoleHierarchy roleHierarchy() {
     * RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
     * String hierarchy = "ROLE_ADMIN > ROLE_MOD \n ROLE_DEALER > ROLE_USER";
     * roleHierarchy.setHierarchy(hierarchy);
     * return roleHierarchy;
     * }
     * 
     * @Bean
     * public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
     * DefaultWebSecurityExpressionHandler expressionHandler = new
     * DefaultWebSecurityExpressionHandler();
     * expressionHandler.setRoleHierarchy(roleHierarchy());
     * return expressionHandler;
     * }
     */
}
