import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import excepciones.IDNotWrite;

public class ManejoInventario {

    public static void EditarCantidad(String opc, int contador){
        try{

            System.out.println("Ingresa cantidad: ");
            Scanner scanner = new Scanner(System.in);
            int cantidad = scanner.nextInt();

            File fileinventario = new File("punto2/files/inventario.txt");
            RandomAccessFile buffer = new RandomAccessFile(fileinventario, "rw");

            for(int i = 0; i < contador; i++){
                buffer.readLine();
            }
            String linea = buffer.readLine();
            
            File file = new File("punto2/files/inventario.txt");
            RandomAccessFile buffer2 = new RandomAccessFile(file, "rw");

            for(int i = 0; i < contador; i++){
                buffer2.readLine();
            }

            StringTokenizer tokenizer = new StringTokenizer(linea,",");
                for(int i = 0; i < 2; i++){
                    tokenizer.nextToken();
                }
            int cantidadinventario = Integer.parseInt(tokenizer.nextToken());
            int save = cantidadinventario;

            if(opc.equals("+")){
                cantidadinventario += cantidad;
            }else{
                cantidadinventario -= cantidad;
            }
            String nuevaLinea = linea.replace(save+"", cantidadinventario+"") + "\n";
            byte[] escrituraBt = nuevaLinea.getBytes("UTF-8");
            buffer2.write(escrituraBt);
            buffer.close();
            buffer2.close();
            System.out.println("Cantidad editada con exito");
           
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }

    public static void EditarProductoCantidad(String IDbuscar){
        try {
            String producto = BuscarProducto(IDbuscar);

            if(producto.equals("")){
                throw new IDNotWrite("No existe ese ID");
            }

            

            System.out.println("Quiere agregar o retirar cantidad del inventario (+ / -)");
            Scanner scanner = new Scanner(System.in);
            String opc = scanner.nextLine();
            if(opc.equals("+") || opc.equals("-")){

                StringTokenizer tokenizer = new StringTokenizer(producto,",");
                for(int i = 0; i < 3; i++){
                    tokenizer.nextToken();
                }
                int contador = Integer.parseInt(tokenizer.nextToken());
                EditarCantidad(opc, contador);

            }else{
                System.out.println("Ingreso valido");
            }

        } catch (IDNotWrite e) {
            System.out.println(e.getMessage());
        }
    }
    

    public static String BuscarProducto(String IDbuscar){
        try{
            File fileinventarioID = new File("punto2/files/inventarioID.txt");
            RandomAccessFile buffer1 = new RandomAccessFile(fileinventarioID, "rw"); 

            String linea = buffer1.readLine();
            if(linea == null){
                throw new IDNotWrite("No existe el ID");
            }

            File fileinventario = new File("punto2/files/inventario.txt");
            RandomAccessFile buffer2 = new RandomAccessFile(fileinventario, "rw");

            StringTokenizer tokenizer = new StringTokenizer(linea,",");

            String producto = "";
            int contador = 0;
            while(tokenizer.hasMoreTokens()){

                String id =  tokenizer.nextToken();
                if(id.equals(IDbuscar)){
                    producto =  buffer2.readLine() + "," + contador;
                    break;
                }
                buffer2.readLine();
                contador++;
            }
            buffer1.close();
            buffer2.close();
            return producto;


        }catch(FileNotFoundException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(IDNotWrite e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static void IngresarProducto(Producto producto){
        try{
            File fileinventario = new File("punto2/files/inventario.txt");
    
           RandomAccessFile buffer1 = new RandomAccessFile(fileinventario, "rw"); 

           long fileLength = buffer1.length();
           long pointer = fileLength;
           buffer1.seek(pointer);
           String escritura = producto.getID() + "," + producto.getNombre() + "," + producto.getCantidad() + "\n";
           byte[] escrituraBt = escritura.getBytes("UTF-8");
           buffer1.write(escrituraBt);

           File fileinventarioID = new File("punto2/files/inventarioID.txt");
           RandomAccessFile buffer2 = new RandomAccessFile(fileinventarioID, "rw");

           pointer = buffer2.length();
           buffer2.seek(pointer);
           String escrituraID = producto.getID() + ",";

           byte[] escrituraBtID = escrituraID.getBytes("UTF-8");
           buffer2.write(escrituraBtID);

            
            buffer1.close();
            buffer2.close();
        }catch(FileNotFoundException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void BorrarProducto(String ID) {
        try {
            File fileinventario = new File("punto2/files/inventario.txt");
            File fileinventarioID = new File("punto2/files/inventarioID.txt");

            RandomAccessFile bufferInventario = new RandomAccessFile(fileinventario, "rw");
            RandomAccessFile bufferInventarioID = new RandomAccessFile(fileinventarioID, "rw");

            List<String> inventarioLineas = new ArrayList<>();
            String linea;

            while ((linea = bufferInventario.readLine()) != null) {
                inventarioLineas.add(linea);
            }

            List<String> inventarioIDLineas = new ArrayList<>();
            StringBuffer idBuffer = new StringBuffer();

            while ((linea = bufferInventarioID.readLine()) != null) {
                idBuffer.append(linea);
            }
            String[] ids = idBuffer.toString().split(",");
            inventarioIDLineas.addAll(Arrays.asList(ids));

            int indiceEliminar = inventarioIDLineas.indexOf(ID);

            if (indiceEliminar != -1) {
                inventarioLineas.remove(indiceEliminar);
                inventarioIDLineas.remove(ID);
            }

            bufferInventario.setLength(0);
            for (String line : inventarioLineas) {
                bufferInventario.writeBytes(line + "\n");
            }

            bufferInventarioID.setLength(0);
            String newIDData = String.join(",", inventarioIDLineas);
            bufferInventarioID.writeBytes(newIDData + ",");

            bufferInventario.close();
            bufferInventarioID.close();
            System.out.println("Producto borrado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
