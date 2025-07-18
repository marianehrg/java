package mns.hrg.m2i2.security;

import mns.hrg.m2i2.dao.entity.User;
import mns.hrg.m2i2.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    protected UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String psoeudo) throws UsernameNotFoundException {

        Optional<User> optionalUtilisateur = userRepository.findByPsoeudo(psoeudo);

        if(optionalUtilisateur.isEmpty()) {
            throw new UsernameNotFoundException("Psoeudo introuvable : " + psoeudo);
        }

        return new AppUserDetails(optionalUtilisateur.get());
    }
}
