package android.example.com.conbo;

import java.util.List;

/**
 * Created by Sallamaarit Jaako 1601459 on 12.5.2018.
 */
// Validator class used to check that no empty strings are inserted into database
public class Validator {

    public Validator() {
    }

    public boolean validate(List<String> values) {
        for (String s:values) {
            if (s.length()<=0||s==null){
                return false;
            }
        }
        return true;
    }
}
