import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcesoAlmacenamiento {
    public static void main(String[] args) {

        try (BufferedReader bt = new BufferedReader(new InputStreamReader(System.in))) {

            String linea;
            while ((linea = bt.readLine()) != null) {
                System.out.println(linea);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
