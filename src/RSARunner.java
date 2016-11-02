/**
 * Created by Mitchell Parekh on 11/2/2016.
 * RSA-Encryption Runner class that will create RSA object and
 * run various tests to see if algorithm is functional
 */
public class RSARunner {
    public static void main(String[] args) {
        RSA rsa = new RSA();
        int enc1 = rsa.encrypt(6);
        int[] enc2 = rsa.encrypt("input");
        System.out.println(enc1);
        System.out.println(rsa.decrypt(enc1));
    }

}
