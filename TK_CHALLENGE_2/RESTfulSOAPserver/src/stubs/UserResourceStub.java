
package stubs;

import repositories.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import models.User;

/**
 *
 * @author adam
 */
  @XmlRootElement
public class UserResourceStub implements UsersRepository {

    private static List<User> users;
    private User tempUser = null;
    private String tempString = null;
    public static UserResourceStub staicInstance =
           new  UserResourceStub();

    public static UserResourceStub getStaicInstance() {
        return staicInstance;
    }
    
    
    static {
        users = new ArrayList<>();
        users.add(new User("Adam Fuzum Tewelde", "fuzumoe", "1987"));
        users.add(new User("Ahmed Mamdoh", "ahmed", "1998"));
        users.add(new User("Sushma ", "sush", "2015"));
        users.add(new User("Janvi ", "janvi", "2015"));
    }
    /**
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return users;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<String> getAllUsersDefinition() {
        List<String> usersDefinition = new ArrayList<>();
          tempString = null;
        for(int i=0; i < getAllUsers().size(); i++){
             tempString = getAllUsers().get(i).getFullName();
             usersDefinition.add(tempString);
        }
        return usersDefinition;
     }
    


    /**
     *
     */
    public UserResourceStub() {
        
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public String getUserFullName(String username){
           tempUser = null;
           tempString = null;
        for (int i = 0; i < users.size(); i++) {
            tempUser = users.get(i);
            if (tempUser.getUserName().equals(username)) {
              tempString = tempUser.getFullName();
            }  
        }

         return tempString;
     }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public int getUserId(String username) {
        tempUser = null;
        int tempInt = -1;
        for (int i = 0; i < getAllUsers().size(); i++) {
            tempUser = users.get(i);
            if (tempUser.getUserName().equals(username)) {
              tempInt = i;
            }  
        }

         return tempInt;
        
     }
    /**
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        tempUser = null;
        for (int i = 0; i < getAllUsers().size(); i++) {
            tempUser = getAllUsers().get(i);
            if (tempUser.getUserName().equals(username)) {
                tempUser = users.get(i);
            } else {
                tempUser = null;
            }
        }

        return tempUser;
    }

    /**
     *
     * @param ID
     * @return
     */
    @Override
    public User getUserByUserID(int ID) {

        return getAllUsers().get(ID);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean userAuthenticate(String username, String password) {
        boolean isValid = false;

        for (int i = 0; i < users.size(); i++) {
            tempUser = users.get(i);
            if (tempUser.getUserName().equals(username)
                    && tempUser.getUserPassword().equals(password)) {
                isValid = true;
            }
        }

        return isValid;
    }

    /**
     *
     * @param user
     */
    @Override
    public void AddUser(User user) {
        users.add(user);
    }

    /**
     *
     * @param user
     */
    @Override
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     *
     * @param userId
     */
    @Override
    public void removeUser(int userId) {
        users.remove(users.get(userId));
    }

    /**
     *
     * @param oldUsername
     * @param newUsername
     */
    @Override
    public void updateUsername(String oldUsername, String newUsername) {

        for (int i = 0; i < getAllUsers().size(); i++) {
            tempUser = users.get(i);
            if (tempUser.getUserName().equals(oldUsername)) {
                users.get(i).setUserName(newUsername);
            }
        }
    }

    /**
     *
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void updatePassowrd(String oldPassword, String newPassword) {

        for (int i = 0; i < getAllUsers().size(); i++) {
            tempUser = users.get(i);
            if (tempUser.getUserPassword().equals(oldPassword)) {
                users.get(i).setUserPassword(newPassword);
            }
        }
    }

    /**
     *
     * @param username
     * @param newFullName
     */
    @Override
    public void updateFullname(String username, String newFullName) {

        for (int i = 0; i < getAllUsers().size(); i++) {
            tempUser = users.get(i);
            if (tempUser.getUserName().equals(username)) {
                users.get(i).setFullName(newFullName);
            }
        }
    }


}
