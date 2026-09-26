package com.example.chuseok_coding.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IRepository<Integer, User> {
    private static final Map<Integer, User> users;

    static {
        users = new HashMap<>();
        users.put(1, new User(1, "su1", 10, "Developer", "Backend"));
        users.put(2, new User(2, "su2", 20, "Developer", "Frontend"));
        users.put(3, new User(3, "su3", 30, "Engineer", "DevOps/SRE"));
    }

    public User findById(Integer id) {
        User retrieved = users.get(id);
        if (Objects.isNull(retrieved)) {
            throw new RuntimeException("유저가 존재하지 않습니다. id : " + id);
        }
        return retrieved;
    }

    public List<User> findAll() {
        return users.values().stream().toList();
    }

    public User save(User entity) {
        int generatedId = users.size() + 1;
        entity.setId(generatedId);
        users.put(generatedId, entity);
        return users.get(generatedId);
    }
}