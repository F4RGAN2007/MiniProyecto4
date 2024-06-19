import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.RandomAccessFile;

public class App {

    public static void busquedaID(){
        
    }

    public static void ingresar(){
        Scanner scanner = new Scanner(System.in);
        Producto producto = new Producto();
        System.out.println("Ingresar ID del producto");
        String ID = scanner.nextLine();
        boolean repedida = comprobarIDdisponible("files/inventario.txt", ID);
        if(!repedida){
        producto.setID(ID);
        System.out.println("Ingresar nombre del producto");
        producto.setNombre(scanner.nextLine());
        System.out.println("Ingresar cantidad del producto");
        producto.setCantidad(scanner.nextInt());
        
        ManejoInventario inventario = new ManejoInventario();
        ManejoInventario.IngresarProducto(producto);
        }
        else{
            System.out.println("ID ya utilizada");
        }
    }

    public static boolean comprobarIDdisponible(String archivo, String IDacomprobar){
        File file = new File(archivo);
        try {
            RandomAccessFile buffer = new RandomAccessFile(file, "r");
            String linea;
            while ((linea = buffer.readLine()) != null) {
                
                StringTokenizer tokenizer = new StringTokenizer(linea,",");
                if(tokenizer.hasMoreTokens()){
                    String id = tokenizer.nextToken();
                    if(id.equals(IDacomprobar)){
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void menu(){
        byte opc = 1;
        while(opc != 0){
            System.out.println("1. Ingresar producto");
            System.out.println("2. Buscar producto");
            System.out.println("0. Salir");
            Scanner scanner = new Scanner(System.in);
            opc = scanner.nextByte();
            switch (opc) {
                case 1: ingresar(); break;
                case 2: busquedaID(); break;
                default:
                    break;
            }
        }
        
    }


    public static void main(String[] args) throws Exception {
        menu();

    }
}
