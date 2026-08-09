package amreshmaurya.com.payflow.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import amreshmaurya.com.payflow.dto.user.CreateUser;
import amreshmaurya.com.payflow.dto.user.UpdateUser;
import amreshmaurya.com.payflow.dto.user.UserResponse;
import amreshmaurya.com.payflow.entity.User;
import amreshmaurya.com.payflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(CreateUser createUser) {
        String password = passwordEncoder.encode(createUser.getPassword());
        User user = new User();
        user.setName(createUser.getName());
        user.setEmail(createUser.getEmail());
        user.setPhone(createUser.getPhone());
        user.setPassword(password);

        userRepository.save(user);
        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
                .phone(user.getPhone()).build();

    }

    public UserResponse getUserById(UUID id) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));

        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
                .phone(user.getPhone()).build();
    }

    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not found"));

        return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
                .phone(user.getPhone()).build();
    }



    public UserResponse updateUser(UUID id, UpdateUser updateUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id)
                );

        if (updateUser.getEmail() != null) {
            user.setEmail(updateUser.getEmail());
        }

        if (updateUser.getPhone() != null) {
            user.setPhone(updateUser.getPhone());
        }

        if (updateUser.getName() != null) {
            user.setName(updateUser.getName());
        }

        if (updateUser.getPassword() != null) {
            user.setPassword(
                    passwordEncoder.encode(updateUser.getPassword())
            );
        }

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .build();
    }


    public void deleteUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id)
                );

        userRepository.delete(user);
    }

}
