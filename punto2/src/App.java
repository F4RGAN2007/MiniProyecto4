import java.util.Scanner;

public class App {

    public static void busquedaID(){
        
    }

    public static void ingresar(){
        Scanner scanner = new Scanner(System.in);
        Producto producto = new Producto();
        System.out.println("Ingresar ID del producto");
        producto.setID(scanner.nextLine());
        System.out.println("Ingresar nombre del producto");
        producto.setNombre(scanner.nextLine());
        System.out.println("Ingresar cantidad del producto");
        producto.setCantidad(scanner.nextInt());
        
        ManejoInventario inventario = new ManejoInventario();
        inventario.IngresarProducto(producto);
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
