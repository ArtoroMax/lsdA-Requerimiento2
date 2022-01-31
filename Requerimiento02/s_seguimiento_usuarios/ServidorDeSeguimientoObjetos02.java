package s_seguimiento_usuarios;
import java.rmi.RemoteException;
import s_seguimiento_usuarios.sop_rmi.SeguimientoUsuariosImpl;
import s_seguimiento_usuarios.utilidades.UtilidadesRegistroS;

public class ServidorDeSeguimientoObjetos02 {
    private static int NUM_PUERTO_NS = 1;
  private static String DIRECCION_IP_NS = "localhost";

  public static void main(String args[]) throws RemoteException {
    SeguimientoUsuariosImpl objRemoto = new SeguimientoUsuariosImpl();

    try {
      UtilidadesRegistroS.arrancarNS(DIRECCION_IP_NS, NUM_PUERTO_NS);
      UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, DIRECCION_IP_NS, NUM_PUERTO_NS,
          "ObjetoRemotoNotificaciones");
      System.out.println("Objeto remoto registrado");
      System.out.println("[ RUNNING ]..]");
      System.out.println("");
    } catch (Exception e) {
      System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
    }
  }
}
