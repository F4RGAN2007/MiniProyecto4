import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class ManejoInventario {


    public static void IngresarProducto(Producto producto){
        try{
            File file = new File("files/inventario.txt");
    
           RandomAccessFile buffer = new RandomAccessFile(file, "rw"); 

           long fileLength = buffer.length();
           long pointer = fileLength;
           buffer.seek(pointer);
           String escritura = producto.getID() + "," + producto.getNombre() + "," + producto.getCantidad() + "\n";
           byte[] escrituraBt = escritura.getBytes("UTF-8");
           buffer.write(escrituraBt);

            
            buffer.close();
        }catch(FileNotFoundException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        


    }
}
