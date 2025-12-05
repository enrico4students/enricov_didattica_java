
package verifiche.c4q.lab01;

class InfoCoppiaValoriVettore {

    int pos = -1;
    int val1 = -1;
    int val2 = -1;
    int differenza = -1;
}

/**
 *
 * @author enric
 */
public class Esercizio03 {
    
    static InfoCoppiaValoriVettore differenzaMax(int[] v) {
        
        InfoCoppiaValoriVettore ret = new InfoCoppiaValoriVettore();
        
        int max_diff = 0;
        for (int i = 0; i < v.length-1; i++) {
            if (Math.abs(v[i]-v[i+1]) > max_diff) {
                max_diff = Math.abs(v[i]-v[i+1]);
                ret.pos = i;
            }
        }

        ret.differenza = max_diff;
        ret.val1 = v[ret.pos];
        ret.val2 = v[ret.pos+1];
        
        return ret;
    }
    
    public static void main(String[] argv) {
        
        int [] v = {1,2,3,4,-100, 5};
        
        System.out.print("vettore: ");
        for (int e : v) {
            System.out.print(""+e+", ");
        }
        System.out.println();
        
        InfoCoppiaValoriVettore ret = differenzaMax(v);
        System.out.println(" pos1: "+ret.pos + " pos2: "+(ret.pos+1) + " val1: "+ret.val1
                + " val2: "+ret.val2+ " diff: "+ret.differenza);
    }
}
