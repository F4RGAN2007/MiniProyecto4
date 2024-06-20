import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

import excepciones.IDNotWrite;

import java.io.RandomAccessFile;

public class App {

    public static void EditarCantidad(){
        ManejoInventario manejoInventario = new ManejoInventario();

        System.out.println("Ingrese ID del producto a editar: ");
        Scanner scanner = new Scanner(System.in);
        manejoInventario.EditarProductoCantidad(scanner.nextLine());
    }

    public static void busquedaID(){
        try{
            ManejoInventario manejo = new ManejoInventario();
            System.out.println("Ingrese el ID a buscar: ");
            Scanner scanner = new Scanner(System.in);
            String ID = scanner.nextLine();
            String producto = manejo.BuscarProducto(ID);
            if(producto.equals("")){
                throw new IDNotWrite("No existe un producto con ese ID");
            }
            StringTokenizer tokenizer = new StringTokenizer(producto,",");
            System.out.println("ID: " + tokenizer.nextToken());
            System.out.println("Nombre: " + tokenizer.nextToken());
            System.out.println("Cantidad en inventario: " + tokenizer.nextToken());


        }catch(InputMismatchException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IDNotWrite e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static void ingresar(){
        try{
            Scanner scanner = new Scanner(System.in);
        Producto producto = new Producto();
        System.out.println("Ingresar ID del producto");
        String ID = scanner.nextLine();

        if(ID.equals("")){
            throw new IDNotWrite("No deje la ID vacia");
        }

        boolean repedida = comprobarIDdisponible("punto2/files/inventario.txt", ID);
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
        }catch(InputMismatchException e){
            System.out.println("Ingrese un valor valido");
        } catch (IDNotWrite e) {
            System.out.println(e.getMessage());
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

    public static void borrarProducto() {
        ManejoInventario manejoInventario = new ManejoInventario();
        System.out.println("Ingrese ID del producto a borrar: ");
        Scanner scanner = new Scanner(System.in);
        manejoInventario.BorrarProducto(scanner.nextLine());
    }

    public static void menu(){
        byte opc = 1;
        while(opc != 0){
            System.out.println("1. Ingresar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Editar cantidad de un producto");
            System.out.println("4. Borrar producto");
            System.out.println("0. Salir");
            Scanner scanner = new Scanner(System.in);
            opc = scanner.nextByte();
            switch (opc) {
                case 1: ingresar(); break;
                case 2: busquedaID(); break;
                case 3: EditarCantidad(); break;
                case 4: borrarProducto(); break;
                default:
                    break;
            }
        }
        
    }


    public static void main(String[] args) throws Exception {
        menu();

    }
}
