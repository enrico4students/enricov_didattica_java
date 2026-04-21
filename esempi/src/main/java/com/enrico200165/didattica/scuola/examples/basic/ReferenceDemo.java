class MiaClasse {
  public int x = 5;
 }
  
public class ReferenceDemo {

  public static void main(String[] args) {

      MiaClasse a = new MiaClasse();
      System.out.println(a.x);
      
      MiaClasse b = a;
      b.x = 11;
      
      System.out.println(a.x);
  }
}
