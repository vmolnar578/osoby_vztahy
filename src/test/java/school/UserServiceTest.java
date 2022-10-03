package school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import school.dal.UserEntity;
import school.dal.UserRepository;
import school.service.users.UsersDto;
import school.service.users.UsersService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UsersService usersService;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<String> emailBodyCaptor;

    @Test
    public void userServiceSaveValid() {
        // --- setup
        Long generatedId = 1L;

        UserEntity fakeEntity = new UserEntity();
        fakeEntity.setUsername("Meno Jozef");
        fakeEntity.setPasswordHash("fkjasbfkajsbfaksjf");


        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(fakeEntity);

        // --- execution
        UsersDto userToSave = new UsersDto().setUsername("Meno Jozef")
                .setPasswordHash("akjfssakjfnaskjfnajskfn");

        Long id = usersService.createUser(userToSave);

        // verification
        assertEquals(generatedId, id);
        verify(userRepository, times(1)).save(any());
        assertEquals(fakeEntity.toString() , emailBodyCaptor.getValue());
    }

    @Test
    public void userServiceSaveFail() {
        // --- execution
        UsersDto userToSave = new UsersDto().setUsername("Meno Jozef").setPasswordHash("sfasfsafasf");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> usersService.createUser(userToSave));
        assertEquals("Author or Password are empty fields", exception.getMessage());

        verify(userRepository, times(0)).save(any());
    }

    @Test
    public void userServiceDbFailedWrite() {
        when(userRepository.save(any(UserEntity.class)))
                .thenThrow(RuntimeException.class);

        // --- execution
        UsersDto userToSave = new UsersDto().setUsername("Meno Jozef").setPasswordHash("fasfasfas");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usersService.createUser(userToSave));

        // verification
        verify(userRepository, times(1)).save(any());
    }
}
