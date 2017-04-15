package sample.model;
import java.io.Serializable;

/**
 * Created by Alex Thien An Le on 2/20/2017. Edited by John Ho 2/22/2017
 * Standard user class with all getters and setters available
 */

public class User implements Serializable {


    private String username;
    private String password;
    private String address;
    private String email;
    private Title title;


    /**
     * constructor for user
     * @param Username username
     * @param password password
     * @param email email
     */
    public User(String Username, String password, String email) {
        this.username = Username;
        this.password = password;
        this.email = email;
    }
    /**
     *
     * Constructor for User including everything
     * @param username the user's username
     * @param password the user's pass
     * @param address the user's address
     * @param email the user's email
     * @param title the user's title
     */
    public User(String username, String password, String address, String email, Title title) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.title = title;
    }

    /**
     *
     * Constructor for User including everything except address
     * @param username the user's username
     * @param password the user's pass
     * @param email the user's email
     * @param title the user's title
     */
    User(String username, String password, String email, Title title) {
        this(username, password, null, email, title);
    }

    /**
     * constructor for user
     * @param password password
     * @param email email
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    /**
     * get email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    /**
     * get title
     * @return title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * set title
     * @param title title
     */
    public void setTitle(Title title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return password.equals(user.password) && username.equals(user.username);

    }

    @Override
    public int hashCode() {
        int result = password.hashCode();
        result = (31 * result) + username.hashCode();
        return result;
    }

}