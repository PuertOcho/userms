package com.userms.server.infrastructure.adapters.output;

import com.userms.server.application.ports.output.UserRepository;
import com.userms.server.domain.model.User;
import com.userms.server.infrastructure.entities.UserEntity;
import com.userms.server.infrastructure.mapper.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;

import java.util.Optional;
interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

@Repository
public class UserJpaRepositoryAdapter implements UserRepository {
    private final UserJpaRepository repository;
    private final UserMapper mapper;

    public UserJpaRepositoryAdapter(UserJpaRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username)
            .map(mapper::toModel);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
            .map(mapper::toModel);
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
} 