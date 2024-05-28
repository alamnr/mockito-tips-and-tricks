package org.alamnr.boottest.mockito_tips_and_tricks.then;

public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long saveNewUser(String userName){
        if(userName.length() <= 3){
            throw new IllegalArgumentException("User name is too short");
        }

        User user = new User();
        user.setName(userName);

        user = userRepository.save(user);

        return user.getId();
    }
}
