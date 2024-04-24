package miniProject;

import java.util.List;

interface UserRepository {
    User getUserByUserId(String userId);
    void saveUser(User user);
    void updateUserScore(User user, int score);
    List<User> getAllUsers();
}