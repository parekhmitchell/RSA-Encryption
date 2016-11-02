/**
 * Created by Mitchell Parekh on 11/2/2016.
 * This class implements the RSA-algorithm in terms of creating a public
 * and private key along with encryption and decryption of messages
 */
public class RSA {
    private int[] values;
    private int p;
    private int q;
    private int z;

    //server's public keys
    private int n;
    private int k;

    //private key
    private int j;

    /**
     * constructor that user will pass p and q to
     * note, p and q must both be prime
     */
    public RSA(int p, int q) { //throws IllegalArgumentException {
        this.p = p;
        this.q = q;
        this.n = this.p*this.q;
        this.z = (this.p - 1) * (this.q - 1);
        this.k = pickK();
        this.j = pickJ();

        //error checking
        //values = {this.p, this.q, n, z, k, j};
        //for(int i : values)
        //    if(i == -1)
        //        throw new IllegalAccessException("Initial parameters incorrect");
    }

    /**
     * default constructor that will populate
     * p with the value 3 and q with the value 11
     */
    public RSA() {
        //calls constructor with params 3 and 11
        this(3, 11);
    }

    /**
     * encrypt(int input) returns the original message encrypted
     * overloaded method with int input
     *
     * @param P
     * @return
     */
    public int encrypt(int P) {
        //var's are named as aptly as can be
        //the following is just the encryption algorithm
        double Praisek = Math.pow(P, k);
        double divn = Praisek / n;

        long intPart = (long)divn;
        long findquo = intPart * n;

        long E = (long)Praisek - findquo;

        return (int)E;
    }

    /**
     * encrypt(String input) returns the original message encrypted
     * overloaded method with String input
     *
     * returns an array of numbers.. when converting from charsequence
     * to string, the encrypted char might not be an ASCII character that
     * we can see
     *
     * @param input
     */
    public int[] encrypt(String input) {
        int[] output = new int[input.length()];

        for(int i = 0; i < input.length(); i++) {
            output[i] = encrypt(input.charAt(i));
        }
        return output;
    }

    /**
     *
     * @param input
     * @return
     */
    public int decrypt(int input) {
        double Eraisej = Math.pow(input, j);
        double divn = Eraisej / n;

        long intPart = (long) divn;
        long findquo = intPart * n;

        long P = (long)Eraisej - findquo;
        return (int)P;
    }

    /**
     *
     * @param input
     * @return
     */
    public String decrypt(int[] input) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length; i++) {
            char c = (char)decrypt(input[i]);
            sb.append("" + c);
        }

        return sb.toString();
    }

    /**
     * toString() will print out the encrypted String
     * as an array of integers
     *
     * @param input
     * @return
     */
    public String toString(long[] input) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length; i++) {
            sb.append("" + (int)input[i] + "|");
        }

        return sb.toString();
    }

    /**
     * pickK() will pick a prime number k, such that
     * k is co-prime to z, i.e, z is not divisible by k
     */
    private int pickK() {
        int k = 1;
        //boolean flag that will stop loop when we find adequate k
        boolean flag = true;
        while(flag) {
            k++;
            //check to see that z is not div by k and it is a valid prime number
            if(z % k != 0)
                if(isValidPrime(k))
                    flag = false;

            if(k == z)
                return -1;
        }
        return k;
    }

    /**
     * isValidPrime() checks to see if the guess, "n" is a valid
     * guess for k in that it is both prime and no other value contains this number
     */
    private boolean isValidPrime(int n) {
        //prime checking algorithm (quite efficient)
        if (n % 2 == 0) return false;

        int i;
        for(i = 3; i * i <= n; i += 2) {
            if(n % i == 0)
                return false;
        }

        //checks to see if val for k has not been used yet
        if(n == p || n == q || n == this.n || n == z)
            return false;

        return true;
    }

    /**
     * pickJ() will pick a number j such that
     * (k * j) mod z = 1
     */
    private int pickJ() {
        //loop z times, as we are sure that j will be less than z
        for(int j = 1; j < z + 1; j++) {
            if((k * j) % z == 1)
                return j;
        }
        //if we reach this we have failed
        return -1;
    }

}
