package s_seguimiento_usuarios.sop_rmi;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import s_gestion_usuarios.dto.UsuarioDTO;
import s_gestion_usuarios.dto.ValoracionDTO;
import s_gestion_usuarios.sop_rmi.GestionUsuariosInt;
import s_seguimiento_usuarios.dto.AsistenciaDTO;
import s_seguimiento_usuarios.dto.PlanEntrenamientoDTO;
import s_gestion_usuarios.sop_rmi.GestionUsuariosInt;


public class SeguimientoUsuariosImpl extends UnicastRemoteObject implements SeguimientoUsuariosInt {
    private GestionUsuariosInt objRemotoGestion;

    protected SeguimientoUsuariosImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean registrarValoracion(ValoracionDTO valoracion, int id) throws RemoteException {
        UsuarioDTO usuario = objRemotoGestion.consultarUsuario(id);
        usuario.setValoracionDTO(valoracion);
        return false;
    }

    @Override
    public ValoracionDTO consultarValoracion(int identificacion) throws RemoteException {
        UsuarioDTO usuario = objRemotoGestion.consultarUsuario(identificacion);
        usuario.getValoracionDTO();
        return null;
    }

    @Override
    public AsistenciaDTO registrarAsistencia(AsistenciaDTO asistencia, int id, int plan) throws RemoteException {
        UsuarioDTO usuario = objRemotoGestion.consultarUsuario(id);
        usuario.getPlanEntrenamientoDTO().get(plan).getPrograma().
        return null;
    }

    @Override
    public AsistenciaDTO consultarAsistencia(String identificacion) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlanEntrenamientoDTO registrarEntrenamiento(PlanEntrenamientoDTO planEntrenamiento, int id) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlanEntrenamientoDTO consultarEntrenamiento(String identificacion) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }


}