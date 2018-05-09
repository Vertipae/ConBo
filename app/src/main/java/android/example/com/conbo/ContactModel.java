package android.example.com.conbo;

/**
 * Created by Salinaattori on 9.5.2018.
 */

public class ContactModel {

    private String mName;
    private String mPhone;


    public ContactModel() {

    }

    public ContactModel(String name, String phone, int photo) {
        mName = name;
        mPhone = phone;

    }

    // Getters
    public String getmName() {
        return mName;
    }

    public String getmPhone() {
        return mPhone;
    }



    // Setters

    public void setmName(String name) {
        this.mName = name;
    }

    public void setmPhone(String phone) {
        this.mPhone = phone;
    }
}
