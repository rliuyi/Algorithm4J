
public class GCD {

    public static void main(String[] args) {
        int m = 2525;
        int n = 125;
        System.out.println("The gcd for " + m + " and " + n + " is " + gcd3(m, n));
    }
    
//    private static int gcd0(int m, int n) {
//        int r = 1;
//        int min = Math.min(m, n);
//        
//        for(int i = 2; i <= min; i++) {
//            boolean r0 = (m % i == 0);
//            boolean r1 = (n % i == 0);
//            
//            if( r0 && r1) {
//                r = i;
//            }
//        }
//        
//        return r;
//    }
//    
//    private static int gcd1(int m, int n) {
//        int min = Math.min(m, n);
//        int r = 1;
//        
//        for(int i = min; i > 1; i--) {
//            if(m % i == 0 && n % i == 0) {
//                r = i;
//                break;
//            }
//        }
//        
//        return r;
//    }
//    
//    private static int gcd2(int m, int n) {
//        if(m % n == 0) {
//            return n;
//        }
//        
//        if(n % m == 0) {
//            return m;
//        }
//        
//        int min = Math.min(m, n);
//        int r = 1;
//        
//        for(int i = min / 2; i > 1; i--) {
//            if(m % i == 0 && n % i == 0) {
//                r = i;
//                break;
//            }
//        }
//        
//        return r;
//    }
    
    private static int gcd3(int m, int n) {
        if(m % n == 0) {
            return n;
        }
        
        return gcd3(n, m % n);
    }

}
