package builder.rest.logic_layers.services.impl.impl;

import builder.rest.logic_layers.services.ValidationService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isValidEmail(String email) {
        if(email == null || email.isEmpty())
            return false;

        Pattern p = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    private static final String EMAIL_REGEX_PATTERN = "([\\w.-]+([\\w-\\+]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{1,6})$";

}
