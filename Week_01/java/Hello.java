import sun.tools.tree.ByteExpression;

import javax.jws.Oneway;

/**
 * @author linyunrui
 */
public class Hello {

    public static void main(String[] args) {
        int one = 1;
        long two = 2;
        int three = 3;
        for (int i = 0; i < three; i++) {
            if (i == two){
                return;
            }
        }

    }
}
