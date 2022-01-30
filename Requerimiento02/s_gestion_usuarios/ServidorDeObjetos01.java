package s_gestion_usuarios;

import java.rmi.RemoteException;
import s_gestion_usuarios.sop_rmi.GestionUsuariosImpl;
import s_gestion_usuarios.utilidades.UtilidadesRegistroS;

public class ServidorDeObjetos01 {
    public static void main(String args[]) throws RemoteException
    {
    
        int numPuertoRMIRegistry = 1;
        String direccionIpRMIRegistry = "localhost";
        
               
        //System.out.println("Cual es el la direcci�n ip donde se encuentra  el rmiregistry ");
        //direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        //System.out.println("Cual es el n�mero de puerto por el cual escucha el rmiregistry ");
        //numPuertoRMIRegistry = UtilidadesConsola.leerEntero(); 
     
        GestionUsuariosImpl objRemoto = new GestionUsuariosImpl();
        
        try
        {
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoPersonal");            
           
        } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
        
        
    }
}