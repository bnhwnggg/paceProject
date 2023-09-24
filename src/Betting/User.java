package Betting;
import java.util.ArrayList;
import java.util.List;
// User class to create an user object for the betting game

public class User{
    private int idNumber;
    private String firstName;
    private String lastName;
    private String loginUserName;
    private String loginPassword;
    private Status status;
    private List<Coin> userBalance;

    public User(int idNumber, String firstName, String lastName, String loginUserName, String loginPassword) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginUserName = loginUserName;
        this.loginPassword = loginPassword; // Remember: hashing in real-world scenarios!
        this.userBalance = new ArrayList<>();
    }

    enum Status {
        FREE,
        PREMIUM;
    }

    public String getUsername(){
        return this.firstName + this.lastName;
    }

    public List<Coin> getBalanceValue(){
        return this.userBalance;
    }

    public void addCoin(Coin coin){
        this.userBalance.add(coin);
    }

    public void removeCoin(){
        if(this.userBalance.isEmpty()){
        return;
    } else {
        this.userBalance.remove(0);
    }
    }
    
    public int getUserTotalCoin(){
        return this.userBalance.size();
    }
}
