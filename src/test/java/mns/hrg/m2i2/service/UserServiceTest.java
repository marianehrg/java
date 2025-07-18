package mns.hrg.m2i2.service;

import mns.hrg.m2i2.dao.entity.User;
import mns.hrg.m2i2.dao.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void shouldCreateUserWithEncodedPasswordAndSaveIt() {
        String rawPassword = "1234";
        String encodedPassword = "encoded_1234";
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        userService.createUser("testuser", rawPassword, true);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertThat(savedUser.getPsoeudo()).isEqualTo("testuser");
        assertThat(savedUser.getPassword()).isEqualTo(encodedPassword);
        assertThat(savedUser.isAdmin()).isTrue();
        assertThat(savedUser.getRole()).isNotNull();
        assertThat(savedUser.getRole().getId()).isEqualTo(1);
    }

    @Test
    void shouldReturnAllUsers() {
        User user1 = new User();
        User user2 = new User();

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> result = userService.getAll();

        assertThat(result).hasSize(2);
        verify(userRepository).findAll();
    }
}
