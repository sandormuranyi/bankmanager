package hu.iit.uni.miskolc.infrend.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class UserModel implements Serializable {

    private String id;

    @Size(min = 3, max = 20, message = "A vezetéknévnek 3 és 20 karakter között kell lennie")
    @Pattern(regexp = "^[^0-9()]+$", message = "A vezetéknév nem tartalmazhat számot")
    private String firstname;

    @Size(min = 3, max = 20, message = "A keresztnévnek 3 és 20 karakter között kell lennie")
    @Pattern(regexp = "^[^0-9()]+$", message = "A keresztnév nem tartalmazhat számot")
    private String lastname;
    private String street;
    @Pattern(regexp = "^[^0-9()]+$", message = "A város nem tartalmazhat számot")
    private String city;
    @Pattern(regexp = "\\d{4}", message = "Az irányítószámnak 4 számból kell állnia")
    private String postalCode;
    @Pattern(regexp = "\\S{8}", message = "A személyigazolvány számnak 8karakternek kell lennie")
    private String personalID;
    @Pattern(regexp = "\\d{9}", message = "A telefonszámnak 9 számból kell állnia")
    private String phone;
    private boolean isActive = true;
    private String userCode;
    @NotNull


    public UserModel(String iD, String firstname, String lastname, String street, String city, String postalCode,
                     String personalID, String phone, boolean isActive, String userCode) {
        id = iD;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.personalID = personalID;
        this.phone = phone;
        this.isActive = isActive;
        this.userCode = userCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public UserModel() {
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "UserModel [ID=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", street=" + street
                + ", city=" + city + ", postalCode=" + postalCode + ", personalID=" + personalID + ", phone=" + phone
                + ", isActive=" + isActive + ", userCode=" + userCode + "]";
    }

}
