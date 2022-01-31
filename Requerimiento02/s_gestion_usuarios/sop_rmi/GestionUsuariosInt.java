package s_gestion_usuarios.sop_rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.UsuarioDTO;
import s_gestion_usuarios.dto.ValoracionDTO;
import s_gestion_usuarios.dto.CredencialDTO;
import cliente.sop_rmi.AdminCllbckInt;
//Hereda de la clase Remote, lo cual la convierte en interfaz remota

public interface GestionUsuariosInt extends Remote {

    public boolean registrarPersonal(PersonalDTO objPersonal) throws RemoteException;

    public PersonalDTO consultarPersonal(int id) throws RemoteException;

    public PersonalDTO editarPersonal(PersonalDTO objPersonal) throws RemoteException;

    public void registrarCallback(AdminCllbckInt objAdmin) throws RemoteException;

    public PersonalDTO abrirSesion(CredencialDTO objCredencial) throws RemoteException;

    public boolean registrarUsuario(UsuarioDTO objUsuario) throws RemoteException;

    public boolean registrarValoracion(ValoracionDTO objValoracion, int id) throws RemoteException;

    public UsuarioDTO consultarUsuario(int id) throws RemoteException;
}
