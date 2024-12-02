package utility;

import java.io.IOException;

public interface UserFunctions {
    /*
     *   Method: 'mainPage'
     *   - Contains all user-related functions
     *   @param String:welcomeName to be used as a welcome header upon entry
     *   @return none
     */
    abstract void mainPage(String welcomeName) throws IOException;

    /*
     *   Method: 'loginPage'
     *   - Takes user details using nested ifs
     *   - If username is valid: proceed to password
     *   - If invalid: ask again/cancel log-in
     *   - Same goes for password
     *   @param none
     *   @return none
     */
    abstract void loginPage() throws IOException;

    /*
     *   Method: 'checkUsername'
     *   - Persistent validation on whether username is valid or not
     *   - Allows to repeat the process on user-cue
     *   @param none
     *   @return boolean to signal whether username is valid or not
     */
    abstract boolean checkUsername() throws IOException;

    /*
     *   Method: 'checkPassword'
     *   - Persistent validation on whether password is valid or not
     *   - Allows to repeat the process on user-cue
     *   @param none
     *   @return boolean to signal whether password is valid or not
     */
    abstract boolean checkPassword() throws IOException;
}
