
package repositories;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 import models.User;

/**
 *
 * @author adam
 */
 @XmlRootElement
public interface UsersRepository {

    /**
     *
     * @return
     */
    @XmlElement
    List<String> getAllUsersDefinition();
    /**
     *
     * @return
     */
    @XmlElement
    List<User> getAllUsers();

    /**
     *
     * @param username
     * @return
     */
    
    int getUserId(String username);

    /**
     *
     * @param username
     * @return
     */
    String getUserFullName(String username);

    /**
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     *
     * @param id
     * @return
     */
    User getUserByUserID(int id);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    boolean userAuthenticate(String username,String password);

    /**
     *
     * @param user
     */
    void AddUser(User user);

    /**
     *
     * @param user
     */
    void removeUser(User user);

    /**
     *
     * @param userId
     */
    void removeUser(int userId);

    /**
     *
     * @param oldUsername
     * @param newUsername
     */
    void updateUsername(String oldUsername,String newUsername);

    /**
     *
     * @param oldPassword
     * @param newPassword
     */
    void updatePassowrd(String oldPassword,String newPassword);

    /**
     *
     * @param username
     * @param newFullName
     */
    void updateFullname(String username,String newFullName);
}
