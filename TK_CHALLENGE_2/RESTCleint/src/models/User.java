package models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adam
 */
@XmlRootElement
public class User {

    private String fullName; // user full name
    private String userName; // user name
    private String userPassword; // user passowrd

    /**
     *
     * @param fullName
     * @param userName
     * @param userPassword
     */
    public User(/*@JsonProperty("Full Neam")*/String fullName,
            /*@JsonProperty("username") */ String userName,
            /*@JsonProperty("password")*/ String userPassword) {
        this.fullName = fullName;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     *
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     *
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "User{" + "fullName=" + fullName + ", userName=" + userName + ", userPassword=" + userPassword + '}';
    }
    
    

}
