package com.ufsm.csi.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

   /* public DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }*/

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("***** WebSecurityConfig");
        auth.authenticationProvider(this.authProvider());
    }*/

    @Autowired
    public void configureAutenticacao(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public FiltroAutenticacao filtroAutenticacao() throws Exception{
        return new FiltroAutenticacao();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()

                //UsuarioController
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/cadastrar").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario").permitAll()
                .antMatchers(HttpMethod.PUT, "/desativarconta").permitAll()

                //EspacoController
                .antMatchers(HttpMethod.GET, "/visualizar-espacos").permitAll()
                .antMatchers(HttpMethod.GET, "/visualizar-espaco").permitAll()
                .antMatchers(HttpMethod.DELETE, "/excluir-espaco").hasAnyAuthority("A","F")
                .antMatchers(HttpMethod.POST, "/cadastrar-espaco").hasAnyAuthority("A","F")

                //FuncionarioController
                .antMatchers(HttpMethod.GET,"/visualizar-funcionarios").hasAuthority("A")
                .antMatchers(HttpMethod.POST,"/cadastrar-funcionario").hasAuthority("A")

                //QuadraController
                .antMatchers(HttpMethod.GET,"/visualizar-quadra").hasAuthority("A")
                .antMatchers(HttpMethod.GET,"/visualizar-quadras").permitAll()
                .antMatchers(HttpMethod.PUT,"/salvar-quadra").hasAnyAuthority("A")


                //ReservaController
                .antMatchers(HttpMethod.GET,"/visualizar-reserva").permitAll()
                .antMatchers(HttpMethod.GET,"/visualizar-reservas").hasAuthority("C")
                .antMatchers(HttpMethod.GET, "/visualizar-reservas-quadra").hasAnyAuthority("A","F")
                .antMatchers(HttpMethod.POST,"/cadastrar-reserva").hasAuthority("C")
                .antMatchers(HttpMethod.PUT,"/excluir-reserva").hasAuthority("C")

                //TabAuxiliarContoller
                .antMatchers(HttpMethod.GET, "/visualizar-tabaux").hasAnyAuthority("A","F")
                .antMatchers(HttpMethod.POST, "/cadastrar-tabaux").hasAnyAuthority("A","F")
                .antMatchers(HttpMethod.DELETE, "/deletar-tabaux").hasAnyAuthority("A","F")
                .antMatchers(HttpMethod.GET,"/visualizar-horarios").permitAll();





//                .antMatchers(HttpMethod.GET, "/api/quadras/getQuadras").hasAnyAuthority("A","U") //hasAuthority("U")
//                .antMatchers(HttpMethod.GET, "/api/reservas/getReservas").hasAuthority("A")
//                .antMatchers(HttpMethod.GET, "/api/reservas/getMinhasReservas").hasAuthority("U")
//                .antMatchers(HttpMethod.POST, "/api/reservas/novaReserva").hasAuthority("U")
//                .antMatchers(HttpMethod.POST, "/api/reservas/deletarReserva").hasAuthority("U")

        //  .and().formLogin();

        http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);

    }
}
