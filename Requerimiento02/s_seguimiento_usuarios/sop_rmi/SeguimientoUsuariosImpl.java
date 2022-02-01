package s_seguimiento_usuarios.sop_rmi;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import s_gestion_usuarios.dto.UsuarioDTO;
import s_gestion_usuarios.dto.ValoracionDTO;
import s_gestion_usuarios.sop_rmi.GestionUsuariosInt;
import s_seguimiento_usuarios.dto.AsistenciaDTO;
import s_seguimiento_usuarios.dto.PlanEntrenamientoDTO;
import s_seguimiento_usuarios.dto.ProgramaDTO;


public class SeguimientoUsuariosImpl extends UnicastRemoteObject implements SeguimientoUsuariosInt {
    private GestionUsuariosInt objRemotoGestion;
    private static String URL_LOCATION_FILE = "Requerimiento02/s_gestion_usuarios/historialUsuarios.txt";

    public SeguimientoUsuariosImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean registrarValoracion(ValoracionDTO valoracion, int id) throws RemoteException {
        System.out.println("Registrar valoración ---- ENTRANDO");
        UsuarioDTO usuario = objRemotoGestion.consultarUsuario(id);
        usuario.setValoracionDTO(valoracion);
        serializar(valoracion);
        System.out.println("Registrar valoración ---- SALIENDO");
        return false;
    }

    @Override
    public ValoracionDTO consultarValoracion(int identificacion) throws RemoteException {
        System.out.println("Consultar valoración ---- ENTRANDO");
        UsuarioDTO usuario = objRemotoGestion.consultarUsuario(identificacion);
        usuario.getValoracionDTO();
        System.out.println("Consultar valoración ---- SALIENDO");
        return null;
    }

    @Override
    public boolean registrarAsistencia(String asistencia, String observacion, int id, int plan, int programa) throws RemoteException {
        System.out.println("Registrar Asistencia ---- ENTRANDO");
        UsuarioDTO usuario = objRemotoGestion.consultarUsuario(id);
        usuario.getPlanEntrenamientoDTO().get(plan).getPrograma().get(programa).getFechaAsistencia().add(asistencia);
        usuario.getPlanEntrenamientoDTO().get(plan).getPrograma().get(programa).getObservaciones().add(observacion);
        System.out.println("Registrar Asistencia ---- SALIENDO");
        return true;
    }

    @Override
    public AsistenciaDTO consultarAsistencia(String identificacion) throws RemoteException {

        return null;
    }

    @Override
    public boolean registrarEntrenamiento(String fecha, ProgramaDTO programa, int id) throws RemoteException {
        System.out.println("Registrar Plan de Entrenamiento ---- ENTRANDO");
        UsuarioDTO usuario = objRemotoGestion.consultarUsuario(id);
        PlanEntrenamientoDTO entrenamiento = new PlanEntrenamientoDTO(fecha, programa);
        usuario.setPlanEntrenamientoDTO(entrenamiento);
        return true;
    }

    @Override
    public PlanEntrenamientoDTO consultarEntrenamiento(String identificacion) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }


    private void serializar(ValoracionDTO arrayUsuariosRegistrados) {
        try {
            FileOutputStream fos = new FileOutputStream(URL_LOCATION_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayUsuariosRegistrados);
            oos.close();
            fos.close();
          } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private ArrayList<UsuarioDTO> deserializar() {
        ArrayList<UsuarioDTO> biblioteca = new ArrayList<UsuarioDTO>();
        try {
            FileInputStream fis = new FileInputStream(URL_LOCATION_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            biblioteca = (ArrayList<UsuarioDTO>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return biblioteca;
    }
}