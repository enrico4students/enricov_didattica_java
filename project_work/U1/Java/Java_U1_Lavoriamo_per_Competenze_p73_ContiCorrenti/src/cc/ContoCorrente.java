
package cc;


/**
 *
 * @author
 */
public class ContoCorrente 
{

    private String correntista;
    private int nc;
    private float saldo;

    public ContoCorrente(String correntista, int nc) 
    {
        this.correntista = correntista;
        this.nc = nc;
        this.saldo = 0;
    }

    public String getCorrentista() 
    {
        return correntista;
    }

    public int getNc() 
    {
        return nc;
    }

    public float getSaldo() 
    {
        return saldo;
    }

    public boolean prelievo(float s) 
    {
        boolean eseguito = true;
        if (saldo >= s) 
        {
            saldo = saldo - s;
        } 
        else 
        {
            eseguito = false;
        }
        return eseguito;
    }

    public void versamento(float s) 
    {
        saldo = saldo + s;
    }

    @Override
    public String toString() {
        String s = "";
        s = s + "Nome: " + getCorrentista()
                + " Numero: " + getNc();
        s = s + " Saldo :" + getSaldo();
        return s;
    }


}
