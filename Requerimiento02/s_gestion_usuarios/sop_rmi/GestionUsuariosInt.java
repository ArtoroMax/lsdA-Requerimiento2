package s_gestion_usuarios.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.dto.PersonalDTO;

public interface GestionUsuariosInt extends Remote{
	public boolean registrarPersonal(PersonalDTO objUsuario) throws RemoteException;
	public PersonalDTO consultarPersonal(int id) throws RemoteException;
	public boolean abrirSesion(CredencialDTO objCredencial) throws RemoteException;
}