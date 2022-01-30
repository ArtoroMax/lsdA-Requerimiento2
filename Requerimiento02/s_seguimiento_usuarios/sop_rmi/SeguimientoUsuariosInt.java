package s_seguimiento_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import s_seguimiento_usuarios.dto.NotificacionDTO;

public interface SeguimientoUsuariosInt extends Remote{
    public void enviarNotificacion(NotificacionDTO objNotificacion) throws RemoteException;
    
}
