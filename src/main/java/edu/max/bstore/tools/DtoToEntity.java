package edu.max.bstore.tools;

import edu.max.bstore.dto.User;
import edu.max.bstore.entity.RoleEntity;
import edu.max.bstore.entity.UserEntity;
import edu.max.bstore.enumeration.Status;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DtoToEntity {

    public static UserEntity userDtoToEntity(User user, RoleEntity roleEntity) {
        String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        List<RoleEntity> userRoles = new ArrayList<>();
        userRoles.add(roleEntity);

        return UserEntity.builder()
                .id(user.getUsername())
                .password(hash)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .status(Status.ACTIVE)
                .roles(userRoles)
                .created(new Date())
                .build();
    }
}
