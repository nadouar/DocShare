package Interface;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    
    
    public User(String firstname,String lastname,String email)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    
    
    public String getfirstname()
    {
        return firstname;
    }
    
    public String getlastname()
    {
        return lastname;
    }
    
    public String getemail()
    {
        return email;
    }
}