package s_seguimiento_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import s_gestion_usuarios.dto.ValoracionDTO;
import s_seguimiento_usuarios.dto.AsistenciaDTO;
import s_seguimiento_usuarios.dto.PlanEntrenamientoDTO;


public interface SeguimientoUsuariosInt extends Remote{
  public ValoracionDTO registrarValoracion(ValoracionDTO valoracion) throws RemoteException;
  public ValoracionDTO consultarValoracion(String identificacion) throws RemoteException;
  public AsistenciaDTO registrarAsistencia(AsistenciaDTO asistencia) throws RemoteException;
  public AsistenciaDTO consultarAsistencia(String identificacion) throws RemoteException;
  public PlanEntrenamientoDTO registrarEntrenamiento(PlanEntrenamientoDTO planEntrenamiento) throws RemoteException;
  public PlanEntrenamientoDTO consultarEntrenamiento(String identificacion) throws RemoteException;
}
