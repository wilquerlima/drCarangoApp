package doutor.carangoapp.controller;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by wilqu on 09/07/2018.
 */

public class Mask {

    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll(" ", "")
                .replaceAll("[)]", "");
    }

    public static TextWatcher insert(final String mask, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";
            String newMask = "";


            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String str = Mask.unmask(s.toString());
                String mascara = "";

                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                if (s.length() < 15 && mask.equalsIgnoreCase("##.###.###/####-##")){

                    newMask = "###.###.###-#####";
                }else{
                    newMask = mask;
                }

                if (mask.equalsIgnoreCase("(##) #####-####")){
                    if (str.length()>10){
                        newMask = "(##) #####-####";
                    }else{
                        newMask = "(##) ####-####";
                    }
                }

                int i = 0;
                for (char m : newMask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {

            }
        };
    }
}
