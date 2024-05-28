package org.alamnr.boottest.mockito_tips_and_tricks.then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldStoreUserWhenUsernameIsLongerThanThreeCharacter(){

        User saved = new User();
        saved.setId(1L);
        saved.setName("duke");

        when(userRepository.save(any(User.class))).thenReturn(saved);

        Long result = userService.saveNewUser("duke");
        assertEquals(1, result);
    }

    @Test
    void shouldStoreUserWhenUserNameIsLongerThanThreeNew(){
        when(userRepository.save(any(User.class))).then(invocation -> {
            User saveUser = invocation.getArgument(0);
            saveUser.setId(1L);
            return saveUser;
        });

        Long result = userService.saveNewUser("duke");
        assertEquals(1, result);
    }
    @Test
    void shouldThrowWhenUserNameIsLessThanOrEqualThree(){
        
        assertThrows(IllegalArgumentException.class, () -> userService.saveNewUser("duk") );
        
    }
    
}
