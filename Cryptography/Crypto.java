
//This program encrypts a message via a Caesar cipher. The program will take an input message and a shift value, normalise the message by removing punctuation and
//spaces, then convert the message to upper case. The characters within the message are shifted down the alphabet by a fixed amount determined by the shift key.
//The shifted message is then split into groups of two characters (i.e "ABCDEF" --> "AB CD EF")

import java.util.*;                //Import java.util which includes the Scanner class, allowing for user input.

public class Crypto {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);                //Create scanner object.
        System.out.println("\n#############################################################################################################\n");
        System.out.println("Welcome to CRYPTO\n");
        System.out.println("This program will take any message and encrypt it via Caesar's Cipher\n");
        System.out.println("Be sure to download the DECRYPTO partner program to decrypt any messages encrypted by this program\n");
        System.out.println("#############################################################################################################\n");
        System.out.print("\nPlease enter the message to be encrypted: ");
        String startMessage = input.nextLine();                      //User enters the message they wish to encrypt.
        String normalisedMessage = normaliseText(startMessage);      //The user's message is normalised via the normaliseText() method.
        System.out.print("\nPlease enter an integer between 1-25; this is your unique encryption key: ");
        int shift = input.nextInt();                                      //User enters their shift key value.
        String caesarifiedMessage = caesarify(normalisedMessage, shift);  //The normalised message is shifted down the alphabet by a value determined by the shift key.
        String finalMessage = groupify(caesarifiedMessage);               //The shifted message is split into groups of two characters.
        System.out.println("\n#############################################################################################################\n");
        System.out.println("Your encrypted message is: " + finalMessage);
        System.out.println("\nEnter this message, along with your unique encryption key into DECRYPTO in order to decrypt the message\n");
        System.out.println("#############################################################################################################\n");
        System.out.print("\nThank you for using CRYPTO, enter any key to exit the  program: ");
        int endMessage = input.nextInt();
        System.out.println(endMessage);

    }

    //This method takes the input message and removes all punctuation and spaces, it then  converts the message to uppercase and returns the result as a string.
    public static String normaliseText(String message) {
        message = message.replaceAll("\\p{Punct}", "");
        message = message.replace(" ", "");
        message = message.toUpperCase();
        return message;
    }

    //This method takes the alphabet and shifts each character by an amount determined by the 'shift' input argument. The new 'shifted' alphabet is returned as a string.
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift;        //If the shift value is negative, convert character 'Z' to its ASCII value and add the shift value.
        } else {
            start = (int) 'A' + shift;        //If the shift value is positive, convert character 'A' to its ASCII value and add the shift value.
        }                                     //E.g if the shift value is '4', the resulting ASCII value wil be 065 + 4 = 069.

        String result = "";                   //Initialisation of the shifted alphabet string.
        char currChar = (char) start;          //Convert the shifted ASCII value back to a character. This denotes the shifted start point of the new alphabet string.
                                              //E.g a shifted ASCII value of 069 will correspond to the character 'E'.

        for(; currChar <= 'Z'; ++currChar) {  //This loop concatenates the 'result' string with the shifted start point as the first character. The result will be a shifted
                                             //alphabet up to the character 'Z'. E.g. if 'E' was the shifted starting character ('currChar') the 'result' string would be
            result = result + currChar; }      //"EFGHIJKLMNOPQRSTUVWXYZ"

        if(result.length() < 26) {                                     //If statement to check if the 'result' string contains 26 characters of the alphabet.
            for(currChar = 'A'; result.length() < 26; ++currChar) {    //If less than 26 characters are present set the starting character to 'A', and concatenate the
                result = result + currChar;                            //'result' string until it contains 26 characters.
            }                                                          //Following from the initial example of a shift value of '4' and a starting value of 'E'
        }                                                              //the final result string will be "EFGHIJKLMNOPQRSTUVWXYZABCD", i.e. the alphabet shifted by 4 characters.
        return result;
    }


    //This method takes the normalised message and shifts it using the shiftAlphabet() method.
    public static String caesarify(String message, int shift) {
        String oldAlphabet = shiftAlphabet(0);                 //Use shiftAlphabet(0) to store the alphabet prior to any shifting into a string.
        String newAlphabet = shiftAlphabet(shift);             //Store the shifted alphabet determined by the shift key into a string.
        int lengthMessage = message.length();                  //Store the length of the normalised message.
        String newString = "";                                 //Initialise the String where the shifted message will be stored.

        for (int n=0; n < lengthMessage; n++){
            char letter = message.charAt(n);                         //Each iteration of this loop will store each successive character of the normalised message into the 'letter' variable.
            int oldIndex = oldAlphabet.indexOf(letter);              //The character in 'letter' is compared to the unshifted alphabet to determine its index within the alphabet. E.g 'G' has an index of '6'
                                                                     //within the unshifted alphabet.
            newString = newString + newAlphabet.charAt(oldIndex); }  //The index of the character in the unshifted alphabet is compared to the character which occurs at the same index in the
        return newString; }                                          //shifted alphabet (E.g the 6th index in an alphabet shifted by '4' is the character 'K'). This character is concatenated to 'newString'
                                                                     //and the loop continues for all successive characters in the normalised message up to lengthMessage. The result is the normalised
                                                                     //shifted down the alphabet by a number determined via the shift key.

    //This method takes the shifted message and splits it into groups of two characters, e.g. "ABCDEF" --> "AB CD EF"
    public static String groupify(String message){
        int lengthMessage = message.length();                 //Store the length of the shifted message
        if (lengthMessage % 2 != 0) {
            message = message + "x";}                         //If the shifted message contains an odd number of characters, concatenate an 'x' character onto the end so as to make the length even.
        String newMessage = "";
        int i = 0;
        int group = 2;

        for (int n=1; n <= (message.length()/2); n++){
            newMessage = newMessage + message.substring(i,group) + " ";    //This loop splits the shifted message into groups of two characters. E.g. if 'message' = "ABCDEF"
            i = i + 2;                                                     //message.substring(0,2) = "AB", message.substring(2,4) = "CD", message.substring(4,6) = "EF".
            group = group + 2; }
        return newMessage; }
}
















