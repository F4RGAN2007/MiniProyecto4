public class Producto {
    String nombre;
    String ID;
    int cantidad;
    
    public Producto(){
        nombre = "";
        ID = "";
        cantidad = 0;
    }

    public Producto(String nombre, String iD, int cantidad) {
        this.nombre = nombre;
        ID = iD;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
