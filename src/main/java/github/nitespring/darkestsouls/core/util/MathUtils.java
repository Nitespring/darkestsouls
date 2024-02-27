package github.nitespring.darkestsouls.core.util;

public class MathUtils {


    public static String convertToRoman(int n) {

        StringBuilder s = new StringBuilder();
        while(n>0) {
            if(n>=1000) {
                int m = n / 1000;
                for (int k = 0; k < m; k++) {
                    s.append("M");
                    n = n - 1000;
                }
            }
            if(n>=500) {
                if(n<900) {
                    s.append("D");
                    n = n-500;
                }else{
                    s.append("CM");
                    n = n-900;
                }
            }
            if(n>=100) {
                int nC = 1;
                if(n<400) {
                    while (n / 100 >= 1 && nC <= 3) {
                        s.append("C");
                        n = n - 100;
                        nC++;
                    }
                }else{
                    s.append("CD");
                    n = n - 400;
                }
            }
            if(n>=50) {
                if(n<90) {
                    s.append("L");
                    n = n-50;

                }else{
                    s.append("XC");
                    n = n-90;
                }
            }
            if(n>=10) {
                int nC = 1;
                if(n<40) {
                    while (n / 10 >= 1 && nC <= 3) {
                        s.append("X");
                        n = n - 10;
                        nC++;
                    }
                }else{
                    s.append("XL");
                    n = n - 40;
                }
            }
            if(n>=5) {
                if(n<9) {
                    s.append("V");
                    n = n-5;
                }else{
                    s.append("IX");
                    n = n-9;
                }
            }
            if(n>=1){
                int nC = 1;
                if(n<4) {
                    while (n >= 1 && nC <= 3) {
                        s.append("I");
                        n = n - 1;
                        nC++;
                    }
                }else{
                    s.append("IV");
                    n = n - 4;
                }
            }

        }
        return s.toString();
    }



}
