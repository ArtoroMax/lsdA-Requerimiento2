package s_seguimiento_usuarios.sop_rmi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import s_gestion_usuarios.dto.ValoracionDTO;
import s_seguimiento_usuarios.dto.AsistenciaDTO;
import s_seguimiento_usuarios.dto.PlanEntrenamientoDTO;


public class SeguimientoUsuariosImpl extends UnicastRemoteObject implements SeguimientoUsuariosInt {

    protected SeguimientoUsuariosImpl() throws RemoteException {
        super();
    }

    @Override
    public ValoracionDTO registrarValoracion(ValoracionDTO valoracion) throws RemoteException {
        
        return null;
    }

    @Override
    public ValoracionDTO consultarValoracion(String identificacion) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AsistenciaDTO registrarAsistencia(AsistenciaDTO asistencia) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AsistenciaDTO consultarAsistencia(String identificacion) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlanEntrenamientoDTO registrarEntrenamiento(PlanEntrenamientoDTO planEntrenamiento) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlanEntrenamientoDTO consultarEntrenamiento(String identificacion) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }


}