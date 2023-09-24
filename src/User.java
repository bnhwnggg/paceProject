
// User class to create an user object for the betting game


public class User{
    private int idNumber;
    private String firstName;
    private String lastName;
    private String loginUserName;
    private String loginPassword;
    private Status status;

    public User(int idNumber, String firstName, String lastName, String loginUserName, String loginPassword, Status status) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginUserName = loginUserName;
        this.loginPassword = loginPassword; // Remember: hashing in real-world scenarios!
        this.status = status;
    }

enum Status {
    FREE,
    PREMIUM;
}
} 
