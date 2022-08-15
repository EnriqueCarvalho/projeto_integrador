package com.ufsm.csi.backend.decorator;

import com.ufsm.csi.backend.model.Usuario;
import com.ufsm.csi.backend.service.UsuarioService;
import com.ufsm.csi.backend.service.UsuarioServiceInterface;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceDecorator implements BaseDecorator, UsuarioServiceInterface {

    private UsuarioServiceInterface usuarioService;
    StringBuilder string = new StringBuilder();

    public UsuarioServiceDecorator(UsuarioServiceInterface usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Override
    public Usuario cadastrarUsuario(Usuario u) throws IOException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        this.string.append(date.format(LocalDateTime.now()));
        this.string.append(" - ");
        this.string.append("Cadastrando usuario: "+u.toString());
        this.insertLog(this.string);
        return this.usuarioService.cadastrarUsuario(u);
    }

    @Override
    public Usuario login(Usuario u) throws IOException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        this.string.append(date.format(LocalDateTime.now()));
        this.string.append(" - ");
        this.string.append("Tentativa de login para o usuario: "+u.toString());
        this.insertLog(this.string);
        return this.usuarioService.login(u);
    }

    @Override
    public Optional<Usuario> getUsuarioById(Integer id) throws IOException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        this.string.append(date.format(LocalDateTime.now()));
        this.string.append(" - ");
        this.string.append("buscando usuario com id: "+id);
        this.insertLog(this.string);
        return this.usuarioService.getUsuarioById(id);
    }

    @Override
    public Usuario getUsuarioByLogin(String login) throws IOException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        this.string.append(date.format(LocalDateTime.now()));
        this.string.append(" - ");
        this.string.append("Buscando usuario com login: "+login );
        this.insertLog(this.string);
        return this.usuarioService.getUsuarioByLogin(login);
    }

    @Override
    public Usuario getUsuarioByCpf(String cpf) throws IOException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        this.string.append(date.format(LocalDateTime.now()));
        this.string.append(" - ");
        this.string.append("Buscando usuario com cpf: "+cpf );
        this.insertLog(this.string);
        return this.usuarioService.getUsuarioByCpf(cpf);
    }

    @Override
    public Usuario desativarConta(Usuario u) throws IOException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        this.string.append(date.format(LocalDateTime.now()));
        this.string.append(" - ");
        this.string.append("Desativando conta do usuario: "+u.toString() );
        this.insertLog(this.string);
        return this.usuarioService.desativarConta(u);
    }

    @Override
    public void tornaFuncionario(Integer idUsuario) throws IOException {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm:ss");
        this.string.append(date.format(LocalDateTime.now()));
        this.string.append(" - ");
        this.string.append("Tornando funcionario o usuário com id: "+idUsuario );
        this.insertLog(this.string);

        this.usuarioService.tornaFuncionario(idUsuario);
    }

    @Override
    public void insertLog(StringBuilder str) throws IOException {

        synchronized (this) {
            File arquivo = new File("C:\\Users\\Eustacio\\Desktop\\log.txt");

            if( !arquivo.exists()){
                arquivo.createNewFile();
            }


            List<String> lista = new ArrayList<>();
            lista.add(String.valueOf(str));

            Files.write(Paths.get(arquivo.getPath()), lista, StandardOpenOption.APPEND);
        }
    }
}
