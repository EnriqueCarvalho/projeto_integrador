package com.ufsm.csi.backend.security;



import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UserDetailServiceCustomizado(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("username: "+login);

        Usuario usuario =usuarioRepository.findByLoginAndSenha(login);


        if(usuario == null){
            throw  new UsernameNotFoundException("Usuário ou senha inválidos");
        }else{
            UserDetails user = User.withUsername(usuario.getLogin())
                    .password(  new BCryptPasswordEncoder().encode(usuario.getSenha()))
                    .authorities(usuario.getTipo()).build();
            return user;
        }
    }
}
