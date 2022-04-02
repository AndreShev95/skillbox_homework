public class CheckFormat {
    public static boolean checkFormat(String email){
        boolean isValidFormat;
        if (email.matches(".*@.*(\\.).*")){
            isValidFormat = true;
        }
        else {
            isValidFormat = false;
        }
        return isValidFormat;
    }
}
