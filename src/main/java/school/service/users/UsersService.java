package school.service.users;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import school.dal.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;
    public UsersService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(10);

        RoleEntity role = new RoleEntity();
        Long[] roleId = {1L,2L,3L,4L,5L};
        String[] roleName = {"ROLE_ADMIN", "ROLE_USER", "ROLE_TEACHER", "ROLE_PARENT", "ROLE_STUDENT"};
        for(int i = 0; i < roleId.length; i++) {
            role.setId(roleId[i]);
            role.setRoleName(roleName[i]);
            this.roleRepository.save(role);
        }

        //--------------------------------------\\

        if (userRepository.findByUsername("Admin").isEmpty()) {
            UserEntity user = new UserEntity();
            user.setId(1L);
            user.setUsername("Admin");
            user.setPasswordHash("$2a$10$q82GgaHI5eXi2.wuL.iHCuXnWxOAQpslG3ItVhlgW5dBQSLp9i3j.");

            role.setId(1L);
            role.setRoleName("ROLE_ADMIN");
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(role);

            user.setRoles(roles);
            this.userRepository.save(user);
        }
    }

    @Transactional
    public Long createUser(UsersDto usersDto) {
        System.out.println(usersDto.getUsername());
        System.out.println(usersDto.getPasswordHash());
        UserEntity user = new UserEntity();
        usersDto.setPasswordHash(this.passwordEncoder.encode(usersDto.getPasswordHash()));
        convertToEntity(user, usersDto);

        RoleEntity role = new RoleEntity();
        role.setId(2L);
        role.setRoleName("ROLE_USER");
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UsersDto> getAllUsers() {
        return convertToDTOs(userRepository.findAll());
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UsersDto getUserById(Long id) {
        return convertToDTO(userRepository.findById(id).orElse(null));
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UsersDto editUserById(Long personId, UsersDto usersDto) {
        UserEntity user = userRepository.findById(personId).orElse(null);
        if (user == null) return null;

        return convertToDTO(userRepository.save(convertToEntity(user, usersDto)));
    }

    @Transactional
    public void removeUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    // -------------------------------- Convert Functions -----------------------------------

    private List<UsersDto> convertToDTOs(Iterable<UserEntity> users) {
        List<UsersDto> usersDto = new ArrayList<>();
        for (UserEntity user: users) {
            usersDto.add(convertToDTO(user));
        }
        return usersDto;
    }

    private UsersDto convertToDTO(UserEntity usersEntity) {
        if (usersEntity == null) return null;

        UsersDto user = new UsersDto();
        user.setId(usersEntity.getId());
        user.setUsername((usersEntity.getUsername()));
        user.setPasswordHash(usersEntity.getPasswordHash());

        return user;
    }

    private UserEntity convertToEntity(UserEntity user, UsersDto usersDto) {
        if (usersDto == null) return null;

        user.setUsername((usersDto.getUsername()));
        user.setPasswordHash(usersDto.getPasswordHash());
        return user;
    }

    // ------------------------------------------------------------------------------------
}