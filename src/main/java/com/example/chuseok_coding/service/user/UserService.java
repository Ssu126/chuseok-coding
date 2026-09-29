package com.example.chuseok_coding.service.user;


import com.example.chuseok_coding.repository.user.User;
import com.example.chuseok_coding.repository.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Integer id) {
        Optional<User> wrappedUser = userRepository.findById(id);
        User         user = wrappedUser
            .orElseThrow(() -> new RuntimeException("찾으시는 유저가 존재하지 않습니다"));
        return user;
    }

    public Optional<User> findUser(Integer id) {
        return userRepository.findById(id);
    }
}
