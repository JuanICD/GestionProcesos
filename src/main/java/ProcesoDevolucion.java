import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcesoDevolucion {

    public static void main(String[] args) {
        try (BufferedReader bt = new BufferedReader(new InputStreamReader(System.in))) {
            String linea = bt.readLine();
            while ((linea = bt.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
