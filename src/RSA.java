/**
 * Created by Mitchell Parekh on 11/2/2016.
 * This class implements the RSA-algorithm in terms of creating a public
 * and private key along with encryption and decryption of messages
 */
public class RSA {
    private int[] values;
    private int p;
    private int q;
    private int n;
    private int z;
    private int k;

    //constructor that user will pass p and q to
    //note, p and q must both be prime
    public RSA(int p, int q) {
        this.p = p;
        this.q = q;
        this.n = this.p*this.q;
        this.z = (this.p - 1) * (this.q - 1);
        this.k = pickK();
    }

    //default constructor that will populate
    //p with the value 3 and q with the value 11
    public RSA() {
        //calls constructor with params 3 and 11
        this(3, 11);
    }

    //pickK() will pick a prime number k, such that
    //k is co-prime to z, i.e, z is not divisible by k
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
                return k;
        }
        return k;
    }

    //isValidPrime() checks to see if the guess, "n" is a valid
    //guess for k in that it is both prime and no other value contains this number
    private boolean isValidPrime(int n) {
        //prime checking algorithm (quite efficient)
        if (n % 2 == 0) return false;

        int i;
        for(i = 3; i*i <= n; i += 2) {
            if(n % i == 0)
                return false;
        }

        //checks to see if val for k has not been used yet
        if(n == p || n == q || n == this.n || n == z)
            return false;

        return true;
    }

}
