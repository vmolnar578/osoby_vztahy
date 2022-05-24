package school.service.users;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import school.dal.UserEntity;
import school.dal.UserRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Resource
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    public UsersService() {
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long createUser(UsersDto usersDto) {
        UserEntity user = new UserEntity();
        usersDto.setPasswordHash(this.passwordEncoder.encode(usersDto.getPasswordHash()));
        userRepository.save(convertToEntity(user, usersDto));
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