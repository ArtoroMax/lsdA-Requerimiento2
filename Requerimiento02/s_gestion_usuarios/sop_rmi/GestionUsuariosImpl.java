package s_gestion_usuarios.sop_rmi;

import cliente.sop_rmi.AdminCllbckInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;
import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.UsuarioDTO;

public class GestionUsuariosImpl extends UnicastRemoteObject implements GestionUsuariosInt {

    private ArrayList<PersonalDTO> personal;
    private ArrayList<UsuarioDTO> usuarios;
    private Vector<AdminCllbckInt> callbacks;

    public GestionUsuariosImpl() throws RemoteException {
        super();
        personal = new ArrayList<PersonalDTO>();
        callbacks = new Vector<>();

        registrarPersonal(new PersonalDTO("CC", 1007226136, "Edwin Garces", "Administrador", "admin", "admin"));
    }

    @Override
    public PersonalDTO abrirSesion(CredencialDTO objCredencial) throws RemoteException {
        System.out.println("Iniciar Sesión ---- ENTRANDO");

        for (int i = 0; i < personal.size(); i++) {
            PersonalDTO personalBusqueda = personal.get(i);

            String usuarioBusqueda = personalBusqueda.getUsuario();
            String claveBusqueda = personalBusqueda.getClave();

            if (usuarioBusqueda.equals(objCredencial.getUsuario()) && claveBusqueda.equals(objCredencial.getClave())) {
                System.out.println("Iniciar Sesión ---- SALIENDO");
                return personalBusqueda;
            }
        }

        System.out.println("Iniciar Sesión ---- SALIENDO");
        return null;

    }

    public int buscarPersonal(int id) {
        int aux = 0;
        for (int i = 0; i < personal.size(); i++) {
            if (id == personal.get(i).getId()) {
                aux = i;
            }
        }
        return aux;
    }

    public void consultarReferenciaRemota(String direccionIpRMIRegistry, int numPuertoRMIRegistry) {
        System.out.println(" ");
        System.out.println("Desde consultarReferenciaRemota()...");
        // this.objReferenciaRemota = (GestionNotificacionesInt)
        // UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,
        // numPuertoRMIRegistry, "ObjetoRemotoNotificacion");
    }

    @Override
    public boolean registrarPersonal(PersonalDTO objPersonal) throws RemoteException {
        System.out.println("Registrar Personañ ---- ENTRANDO");
        boolean bandera = false;

        if (personal.size() < 10) {
            bandera = personal.add(objPersonal);
            System.out.println("Personal registrado exitosamente");
        }

        return bandera;
    }

    @Override
    public PersonalDTO consultarPersonal(int id) throws RemoteException {
        PersonalDTO resultado = null;
        System.out.println("*** Desde consultarPersonal()...");
        System.out.println("*** Consultar Personal con id: " + id);
        int bandera = buscarPersonal(id);
        if (bandera != -1) {
            resultado = personal.get(bandera);
        }
        return resultado;
    }

    @Override
    public void registrarCallback(AdminCllbckInt objAdmin) throws RemoteException {
        System.out.println("En regCallbck()");
        if (!(callbacks.contains(objAdmin))) {
            callbacks.addElement(objAdmin);
            System.out.println("Nuevo  Objeto adicionado");
            // hacerCallbck();
        }
    }

    public void hacerCallbck(CredencialDTO objCredencial) throws RemoteException {
        for (int i = 0; i < callbacks.size(); i++) {
            AdminCllbckInt obj = (AdminCllbckInt) callbacks.elementAt(i);
            obj.informarIngreso(objCredencial.getUsuario(), objCredencial.getClave());
        }
    }

    @Override
    public boolean registrarUsuario(UsuarioDTO objUsuario) throws RemoteException {
        System.out.println("Registrar Usuario ---- ENTRANDO");
        boolean bandera = false;

        if (personal.size() < 10) {
            bandera = usuarios.add(objUsuario);
            System.out.println("Usuario registrado exitosamente");
            System.out.println("Registra usuario ---- SALIENDo");
        }
        return bandera;
    }

    @Override
    public UsuarioDTO consultarUsuario(int id) throws RemoteException {
        UsuarioDTO resultado = null;
        System.out.println("*** Desde consultarUsuario()...");
        System.out.println("*** Consultar usuario con id: " + id);
        int bandera = buscarPersonal(id);
        if (bandera != -1) {
            resultado = usuarios.get(bandera);
        }
        return resultado;
    }
}
