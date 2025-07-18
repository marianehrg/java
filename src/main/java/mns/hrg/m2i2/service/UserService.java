package mns.hrg.m2i2.service;

import lombok.AllArgsConstructor;
import mns.hrg.m2i2.dao.entity.Role;
import mns.hrg.m2i2.dao.entity.User;
import mns.hrg.m2i2.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String username, String rawPassword,boolean isAdmin) {
        String encoded = passwordEncoder.encode(rawPassword);
        Role roleClient = new Role();
        roleClient.setId(1);
        User user = new User();
        user.setPsoeudo(username);
        user.setPassword(encoded);
        user.setAdmin(isAdmin);
        user.setRole(roleClient);
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
