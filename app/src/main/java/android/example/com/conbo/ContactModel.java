package android.example.com.conbo;

/**
 * Created by Salinaattori on 9.5.2018.
 */

public class ContactModel {

    private String mName;
    private String mPhone;
    private int mId;
    private int mPhoto;


    public ContactModel() {

    }

    public ContactModel(String name, String phone, int id, int photo) {
        mName = name;
        mPhone = phone;
        mId = id;
        mPhoto = photo;
    }

    // Getters
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

    public void setmPhoto(int mPhoto) {
        this.mPhoto = mPhoto;
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
}
