package Repositorios;




public abstract class Repositorio {

    String nombreArchivo;
   
    public String getJsonFile() { //Separe este metodo para poder mockearlo al momento de testear
        return getClass().getClassLoader().getResource(nombreArchivo).getFile();
    }
}
