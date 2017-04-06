
package modelo;

import java.util.ArrayList;

public interface InterfazModelo {
    public void insertar(Object objeto)throws Exception;
    public void editar(Object objeto)throws Exception;
    public void eliminar(int id)throws Exception;
    public Object buscar(String campo,String valor)throws Exception;
    public Object buscar(int id)throws Exception;
    public ArrayList<Object[]> listar(String campo,String valor)throws Exception;
    public boolean existe(Object objeto)throws Exception;
    
}
