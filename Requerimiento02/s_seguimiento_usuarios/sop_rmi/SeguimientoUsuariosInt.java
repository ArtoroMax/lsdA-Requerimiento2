package s_seguimiento_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import s_gestion_usuarios.dto.ValoracionDTO;
import s_seguimiento_usuarios.dto.AsistenciaDTO;
import s_seguimiento_usuarios.dto.PlanEntrenamientoDTO;
import s_seguimiento_usuarios.dto.ProgramaDTO;


public interface SeguimientoUsuariosInt extends Remote{
  public boolean registrarValoracion(ValoracionDTO valoracion, int id) throws RemoteException;
  public ValoracionDTO consultarValoracion(int identificacion) throws RemoteException;
  public boolean registrarAsistencia(String asistencia,String observacion, int id, int plan, int programa) throws RemoteException;
  public AsistenciaDTO consultarAsistencia(String identificacion) throws RemoteException;
  public boolean registrarEntrenamiento(String fecha, ProgramaDTO programa, int id) throws RemoteException;
  public PlanEntrenamientoDTO consultarEntrenamiento(String identificacion) throws RemoteException;
}
