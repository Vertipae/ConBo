package android.example.com.conbo;

/**
 * Created by Sallamaarit Jaako 1601459 on 9.5.2018.
 */
// Model for a Contact
public class ContactModel {

    private String mName;
    private String mPhone;
    private int mId;
    private int mPhoto;
    private String mEmail;
    private String mAddress;


    public ContactModel() {

    }

    public ContactModel(String name, String phone, int id, int photo, String email, String address) {
        mName = name;
        mPhone = phone;
        mId = id;
        mPhoto = photo;
        mEmail = email;
        mAddress = address;
    }

    // Getters

    public String getmEmail() {
        return mEmail;
    }


    public String getmAddress() {
        return mAddress;
    }


    public String getmName() {
        return mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public int getmId() {
        return mId;
    }

    public int getmPhoto() {
        return mPhoto;
    }


    // Setters
    public void setmName(String name) {
        this.mName = name;
    }

    public void setmPhone(String phone) {
        this.mPhone = phone;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmPhoto(int mPhoto) {
        this.mPhoto = mPhoto;
    }
}
