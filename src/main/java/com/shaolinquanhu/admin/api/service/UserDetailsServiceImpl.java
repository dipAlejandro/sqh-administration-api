package com.shaolinquanhu.admin.api.service;

import com.shaolinquanhu.admin.api.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author dahl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        var usuario = usuarioRepository.findByNombreUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario con nombre \"" + username + "\" no fue encontrado o no existe."));
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContrasenia())
                .roles(usuario.getRol())
                .build();
    }

}
