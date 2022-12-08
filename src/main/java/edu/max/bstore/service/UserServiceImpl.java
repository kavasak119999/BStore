package edu.max.bstore.service;

import edu.max.bstore.dto.User;
import edu.max.bstore.entity.RoleEntity;
import edu.max.bstore.entity.UserEntity;
import edu.max.bstore.exception.RegistrationException;
import edu.max.bstore.repository.RoleRepository;
import edu.max.bstore.repository.UserRepository;
import edu.max.bstore.tools.DtoToEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(User user) {
        if (userRepository.findById(user.getUsername()).isPresent())
            throw new RegistrationException(
                    "Client with username: " + user.getUsername() + " already registered");

        RoleEntity roleUser = roleRepository.findByName("ROLE_USER");
        System.out.println(roleUser.getName());
        userRepository.save(DtoToEntity.userDtoToEntity(user, roleUser));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository
                .findById(username);
        if (optionalUserEntity.isEmpty()) {
                throw new UsernameNotFoundException(
                        "User with username: " + username + " not found");
        } else {
            return optionalUserEntity.get();
        }
    }
}
