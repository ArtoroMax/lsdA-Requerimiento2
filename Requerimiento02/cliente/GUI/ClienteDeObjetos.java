package cliente.GUI;

import java.rmi.RemoteException;
import java.util.ArrayList;

import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import s_gestion_usuarios.dto.CredencialDTO;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.dto.UsuarioDTO;
import s_gestion_usuarios.dto.ValoracionDTO;
import s_gestion_usuarios.sop_rmi.GestionUsuariosInt;
import s_seguimiento_usuarios.dto.Ejercicio;
import s_seguimiento_usuarios.dto.ProgramaDTO;
import s_seguimiento_usuarios.sop_rmi.SeguimientoUsuariosInt;

/**
 *
 * @author Yahir Garcés
 */
public class ClienteDeObjetos {

    private static GestionUsuariosInt objRemoto;
    private static SeguimientoUsuariosInt objRemotoSeguimiento;

    public static void main(String[] args) throws RemoteException {
        int numPuertoRMIRegistry = 1;
        String direccionIpRMIRegistry = "localhost";

        // System.out.println("Cual es el la dirección ip donde se encuentra el
        // rmiregistry ");
        // direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        // System.out.println("Cual es el número de puerto por el cual escucha el
        // rmiregistry ");
        // numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
        objRemoto = (GestionUsuariosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,
                numPuertoRMIRegistry, "ObjetoRemotoPersonal");
        objRemotoSeguimiento = objRemoto.consultarReferenciaRemota(direccionIpRMIRegistry, numPuertoRMIRegistry);
        MenuPrincipal();

    }

    private static void MenuPrincipal() throws RemoteException {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Abrir sesion");
            System.out.println("2. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese usuario :");
                    String usuario = UtilidadesConsola.leerCadena();
                    System.out.println("Ingrese clave :");
                    String clave = UtilidadesConsola.leerCadena();

                    CredencialDTO credencial = new CredencialDTO(usuario, clave);
                    PersonalDTO Usuario;
                    Usuario = objRemoto.abrirSesion(credencial);

                    if (Usuario.getOcupacion().equals("Administrador")) {
                        MenuAdmin();
                    } else if (Usuario.getOcupacion().equals("Secretaria")) {
                        MenuSecretaria();
                    } else if (Usuario.getOcupacion().equals("Profesional")) {
                        MenuProfesional();
                    } else {
                        System.out.println("Credenciales no válidas");
                    }
                    break;
                case 2:
                    System.out.println("Consultar Plan");
                    consultarPlan();
                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 2);
    }

    private static PersonalDTO extracted() {
        return new PersonalDTO();
    }

    private static void consultarPlan() {
        try {
            System.out.println("Ingrese identificacion de usuario: ");
            int id = UtilidadesConsola.leerEntero();
            UsuarioDTO usuario = new UsuarioDTO();
            usuario = objRemoto.consultarUsuario(id);
            if (usuario != null) {
                System.out.println("==Plan del cliente==");
                for (int i = 0; i < usuario.getPlanEntrenamientoDTO().size(); i++) {
                    System.out.println("Fecha inicio: " + usuario.getPlanEntrenamientoDTO().get(i).getFechaInicio());
                    for (int j = 0; j < usuario.getPlanEntrenamientoDTO().get(i).getPrograma().size(); j++) {
                        System.out.println(
                                "Dia: " + usuario.getPlanEntrenamientoDTO().get(i).getPrograma().get(j).getDia());
                        for (int k = 0; k < usuario.getPlanEntrenamientoDTO().get(i).getPrograma().get(k).getEjercicio()
                                .size(); k++) {
                            System.out.println("==Ejercicio " + k + "==");
                            System.out.println("Nombre ejercicio: " + usuario.getPlanEntrenamientoDTO().get(i)
                                    .getPrograma().get(k).getEjercicio().get(k).getNombreEjercicio());
                            System.out.println("Repeticiones: " + usuario.getPlanEntrenamientoDTO().get(i).getPrograma()
                                    .get(k).getEjercicio().get(k).getRepeticiones());
                            System.out.println("Peso: " + usuario.getPlanEntrenamientoDTO().get(i).getPrograma().get(k)
                                    .getEjercicio().get(k).getPeso());
                        }
                        System.out.println(
                                "Faltas: " + usuario.getPlanEntrenamientoDTO().get(i).getPrograma().get(j).getFaltas());
                        for (int k = 0; k < usuario.getPlanEntrenamientoDTO().get(i).getPrograma().get(k)
                                .getObservaciones().size(); k++) {
                            System.out.println("==Observacion " + k + "==");
                            System.out.println(usuario.getPlanEntrenamientoDTO().get(i).getPrograma().get(k)
                                    .getObservaciones().get(k));
                        }
                    }
                }
            } else
                System.out.println("El plan de usuario con id: " + id + " no fue encontrado");
        } catch (Exception e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }

    }

    private static void MenuAdmin() throws RemoteException {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Registrar Personal");
            System.out.println("2. Buscar Personal");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    OpcionAdm1();
                    break;
                case 2:
                    OpcionAdm2();
                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 3);
    }

    private static void MenuSecretaria() {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Buscar Usuario");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    OpcionSec1();
                    break;
                case 2:

                    OpcionSec2();
                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    MenuSecretaria();
            }

        } while (opcion != 3);
    }

    private static void MenuProfesional() throws RemoteException {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Valorar Paf");
            System.out.println("2. Registrar plan de entrenamiento");
            System.out.println("2. Registrar asistencia");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    OpcionPro1();
                    break;
                case 2:
                    OpcionPro3();
                    break;
                case 3:
                    OpcionPro2();
                    break;
                case 4:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 4);
    }

    private static String tipoID() {
        System.out.println("Seleccione tipo de identificacion: ");
        System.out.println("1. cc ");
        System.out.println("2. ti");
        System.out.println("3: pp");
        int tipoId = UtilidadesConsola.leerEntero();
        String tipoID = null;
        switch (tipoId) {
            case 1:
                tipoID = "cc";
                break;
            case 2:
                tipoID = "ti";
                break;
            case 3:
                tipoID = "pp";
                break;
            default:
                System.out.println("Tipo no valido, ingrese una opcion del 1 al 3");
                tipoID = tipoID();
                break;
        }
        return tipoID;
    }

    private static String SelectFacultad() {
        System.out.println("Seleccione la facultad: ");
        System.out.println("1. Ingeniería Electrónica y Telecomunicaciones");
        System.out.println("2. Ingeniería Civil");
        System.out.println("3: Derecho, Ciencias Políticas y Sociales");
        System.out.println("4: Ciencias Naturales, Exactas y de la Educación");
        System.out.println("5: Ciencias Humanas y Sociales");
        System.out.println("6: Ciencias Contables, Económicas y Administrativas");
        System.out.println("7: Ciencias de la Salud");
        System.out.println("8: Ciencias Agrarias");
        System.out.println("9: Artes");
        int tipoFacultad = UtilidadesConsola.leerEntero();
        String tipoFac = null;
        switch (tipoFacultad) {
            case 1:
                tipoFac = "Ingeniería Electrónica y Telecomunicaciones";
                break;
            case 2:
                tipoFac = "Ingeniería Civil";
                break;
            case 3:
                tipoFac = "Derecho, Ciencias Políticas y Sociales";
                break;
            case 4:
                tipoFac = "Ciencias Naturales, Exactas y de la Educación";
                break;
            case 5:
                tipoFac = "Ciencias Humanas y Sociales";
                break;
            case 6:
                tipoFac = "Ciencias Contables, Económicas y Administrativas";
                break;
            case 7:
                tipoFac = "Ciencias de la Salud";
                break;
            case 8:
                tipoFac = "Ciencias Agrarias";
                break;
            case 9:
                tipoFac = "Artes";
                break;
            default:
                System.out.println("Tipo no valido, ingrese una opcion del 1 al 9");
                tipoFac = SelectFacultad();
                break;
        }
        return tipoFac;
    }

    private static String tipoUsuario() {
        System.out.println("Seleccione tipo de usuario: ");
        System.out.println("1. Docente ");
        System.out.println("2. Administrativo");
        int tipoUsuario = UtilidadesConsola.leerEntero();
        String tipoU = null;
        switch (tipoUsuario) {
            case 1:
                tipoU = "Docente";
                break;
            case 2:
                tipoU = "Administrativo";
                break;
            default:
                System.out.println("Tipo no valido, ingrese una opcion del 1 al 2");
                tipoU = tipoUsuario();
                break;
        }
        return tipoU;
    }

    private static boolean validarId(int id) {
        if (id <= 0 && id >= 999999) {
            System.out.println("Identificacion no valida");
            return false;
        } else {
            return true;
        }
    }

    private static boolean validarNombreCompleto(String nombreCompleto) {
        if (nombreCompleto.length() < 2 && nombreCompleto.length() > 64) {
            System.out.println("Nombre no valido, debe tener carateres mayores a 2 y menores a 64");
            return false;
        } else {
            return true;
        }
    }

    private static boolean validarCredencial(String credencial) {
        if (credencial.length() < 8) {
            System.out.println("Credencial no valida, debe tener minimo 8 caracteres");
            return false;
        } else {
            return true;
        }
    }

    private static void OpcionAdm1() {
        try {
            System.out.println("==Registro del Cliente==");
            String tipoId = tipoID();
            System.out.println("Ingrese la identificacion");
            int id = UtilidadesConsola.leerEntero();
            if (!validarId(id)) {
                OpcionAdm1();
            }
            System.out.println("Ingrese el nombre completo ");
            String nombres = UtilidadesConsola.leerCadena();
            if (!validarNombreCompleto(nombres)) {
                OpcionAdm1();
            }
            System.out.println("Seleccione ocupación: ");
            System.out.println("1. Secretaria ");
            System.out.println("2. Profesional de acondicionamiento físico");
            int ocupacion = UtilidadesConsola.leerEntero();
            String ocupacionn = null;
            switch (ocupacion) {
                case 1:
                    ocupacionn = "Secretaria";
                    break;
                case 2:
                    ocupacionn = "Profesional";
                    break;
                default:
                    System.out.println("Opcion no valida, ingrese 1 para secretaria o 2 para profesional");
                    OpcionAdm1();
                    break;
            }
            System.out.println("Ingrese usuario ");
            String usuario = UtilidadesConsola.leerCadena();
            if (!validarCredencial(usuario)) {
                OpcionAdm1();
            }
            System.out.println("Ingrese clave ");
            String clave = UtilidadesConsola.leerCadena();
            if (!validarCredencial(clave)) {
                OpcionAdm1();
            }

            PersonalDTO objPersonal = new PersonalDTO(tipoId, id, nombres, ocupacionn, usuario, clave);
            boolean valor = objRemoto.registrarPersonal(objPersonal);
            if (valor) {
                System.out.println("**Personal Registrado Exitosamente**");
            } else {
                System.out
                        .println("**Personal No Registrado, se alcanzó la cantidad máxim a de usuarios a registrar**");
            }
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

    private static void OpcionAdm2() {
        try {
            System.out.println("==Buscar Personal==");
            System.out.println("Ingrese identificacion a buscar");
            int id = UtilidadesConsola.leerEntero();
            PersonalDTO Usuario = extracted();
            Usuario = objRemoto.consultarPersonal(id);

            if (Usuario != null) {
                System.out.println("==Registro del Cliente==");
                System.out.println("Tipo de identificacion: " + Usuario.getTipo_id());
                System.out.println("Identificacion: " + Usuario.getId());
                System.out.println("Nombre completo: " + Usuario.getNombreCompleto());
                System.out.println("Ocupacion: " + Usuario.getOcupacion());
                System.out.println("Usuario: " + Usuario.getUsuario());
                System.out.println("Clave: " + Usuario.getClave());
            } else
                System.out.println("Usuario con id: " + id + " no encontrado");

        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }

    private static void OpcionSec1() {
        try {
            System.out.println("==Registro de Usuario==");
            System.out.println("Ingrese el nombre completo: ");
            String nombreCompleto = UtilidadesConsola.leerCadena();
            if (!validarNombreCompleto(nombreCompleto)) {
                OpcionSec1();
            }
            String tipoId = tipoID();
            System.out.println("Ingrese la identificacion: ");
            int id = UtilidadesConsola.leerEntero();
            if (!validarId(id)) {
                OpcionSec1();
            }
            String facultad = SelectFacultad();
            String tipoUsuario = tipoUsuario();
            System.out.println("Ingrese la fecha de ingreso: ");
            String fechaIngreso = UtilidadesConsola.leerCadena();
            System.out.println("Ingrese las patologias del usuario: ");
            String patologias = UtilidadesConsola.leerCadena();
            System.out.println("Ingrese el usuario con el que se va a registrar: ");
            String usuario = UtilidadesConsola.leerCadena();
            if (!validarCredencial(usuario)) {
                OpcionSec1();
            }
            System.out.println("Ingrese clave: ");
            String clave = UtilidadesConsola.leerCadena();
            if (!validarCredencial(clave)) {
                OpcionSec1();
            }
            UsuarioDTO objUsuario = new UsuarioDTO(tipoId, id, nombreCompleto, facultad, patologias, fechaIngreso,
                    tipoUsuario, usuario, clave);
            boolean valor = objRemoto.registrarUsuario(objUsuario);
            if (valor) {
                System.out.println("**Usuario Registrado Exitosamente**");
            } else {
                System.out.println("**Usuario No Registrado, se alcanzó la cantidad máxim a de usuarios a registrar**");
            }
        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }

    }

    private static void OpcionSec2() {
        try {
            System.out.println("==Buscar Usuario==");
            System.out.println("Ingrese identificacion a buscar");
            int id = UtilidadesConsola.leerEntero();
            UsuarioDTO Usuario = new UsuarioDTO();
            Usuario = objRemoto.consultarUsuario(id);

            if (Usuario != null) {
                System.out.println("==Registro del Usuario==");
                System.out.println("Nombre completo: " + Usuario.getNombreCompleto());
                System.out.println("Tipo de identificacion: " + Usuario.getTipo_id());
                System.out.println("Identificacion: " + Usuario.getId());
                System.out.println("Facultad: " + Usuario.getFacultad());
                System.out.println("Tipo de usuario: " + Usuario.getTipoUsuario());
                System.out.println("Fecha de ingreso: " + Usuario.getFechaIngreso());
                System.out.println("Patologias: " + Usuario.getPatologia());
                System.out.println("Usuario: " + Usuario.getUsuario());
                System.out.println("Clave: " + Usuario.getClave());
            } else
                System.out.println("Usuario con id: " + id + " no encontrado");

        } catch (RemoteException e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }

    private static void OpcionPro1() {
        try {
            System.out.println("==Valorar Usuario==");
            System.out.println("Ingrese la identificacion del paciente: ");
            int id = UtilidadesConsola.leerEntero();
            UsuarioDTO usuario = new UsuarioDTO();
            usuario = objRemoto.consultarUsuario(id);
            if (usuario == null) {
                System.out.println("Usuario con id: " + id + " no existe");
                OpcionPro1();
            }
            System.out.println("Ingrese la fecha de valoracion: ");
            String fechaValoracion = UtilidadesConsola.leerCadena();
            System.out.println("Ingrese la frecuencia cardiaca en reposo: ");
            double frecuenciaReposo = UtilidadesConsola.leerDouble();
            System.out.println("Ingrese la frecuencia cardiaca activa: ");
            double frecuenciaActiva = UtilidadesConsola.leerDouble();
            System.out.println("Ingrese la estatura: ");
            double estatura = UtilidadesConsola.leerDouble();
            System.out.println("Ingrese las medidas del brazo: ");
            double brazo = UtilidadesConsola.leerDouble();
            System.out.println("Ingrese las medidas de la pierna ");
            double pierna = UtilidadesConsola.leerDouble();
            System.out.println("Ingrese las medidas del pecho: ");
            double pecho = UtilidadesConsola.leerDouble();
            System.out.println("Ingrese las medidas de la cintura: ");
            double cintura = UtilidadesConsola.leerDouble();
            System.out.println("Ingrese el estado: ");
            String estado = UtilidadesConsola.leerCadena();

            ValoracionDTO objValoracion = new ValoracionDTO(fechaValoracion, frecuenciaReposo, frecuenciaActiva,
                    estatura, brazo, pierna, pecho, cintura, estado);
            boolean valor = objRemotoSeguimiento.registrarValoracion(objValoracion, id);
            if (valor) {
                System.out.println("**Valoracion Registrada Exitosamente**");
            } else {
                System.out.println("**Valoracion no registrada**");
            }
        } catch (Exception e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }

    private static void OpcionPro2() throws RemoteException {
        System.out.println("==Registrar Asistencia==");
        System.out.println("Ingrese la identificacion del paciente: ");
        int id = UtilidadesConsola.leerEntero();
        System.out.println("Ingrese numero del plan: ");
        int plan = UtilidadesConsola.leerEntero();
        System.out.println("Ingrese numero del programa: ");
        int programa = UtilidadesConsola.leerEntero();
        UsuarioDTO usuario;
        usuario = objRemoto.consultarUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario con id: " + id + " no existe");
            OpcionPro1();
        }
        System.out.println("Ingrese la fecha de la asistencia:");
        String fechaAsistencia = UtilidadesConsola.leerCadena();
        System.out.println("Ingrese las observaciones:");
        String observaciones = UtilidadesConsola.leerCadena();

        boolean valor = objRemotoSeguimiento.registrarAsistencia(fechaAsistencia, observaciones, id, plan, programa);
        if (valor) {
            System.out.println("**Programa Registrada Exitosamente**");
        } else {
            System.out.println("**Programa no registrada**");
        }

    }

    private static void OpcionPro3() {
        try {
            System.out.println("==Crear plann==");
            System.out.println("Ingrese identificacion de usuario: ");
            int id = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese la fecha de inicio: ");
            String fechaInicio = UtilidadesConsola.leerCadena();
            ArrayList<Ejercicio> ejercicios = new ArrayList<>();
            String dia = null;
            int opc = 0;
            do {
                System.out.println("1. Registrar Programa");
                System.out.println("2. Terminar");
                opc = UtilidadesConsola.leerEntero();
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese el dia:");
                        dia = UtilidadesConsola.leerCadena();
                        System.out.println("Ingrese el numero de ejercicios:");
                        int numEjercicios = UtilidadesConsola.leerEntero();

                        for (int i = 0; i < numEjercicios; i++) {
                            System.out.println("Ingrese el nombre del ejercicio:");
                            String nombreEjercicio = UtilidadesConsola.leerCadena();
                            System.out.println("Ingrese la cantidad de repeticiones: ");
                            int repeticiones = UtilidadesConsola.leerEntero();
                            System.out.println("Ingrese el peso: ");
                            String peso = UtilidadesConsola.leerCadena();
                            Ejercicio objEjercicio = new Ejercicio(nombreEjercicio, repeticiones, peso);
                            ejercicios.add(objEjercicio);
                        }
                        break;
                    case 2:
                        System.out.println("Plan terminado");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }

            } while (opc != 2);
            // Constructor corto ProgramaDTO objPrograma = new ProgramaDTO(dia,ejercicios);
            ProgramaDTO objPrograma = new ProgramaDTO(dia, ejercicios);
            boolean valor = objRemotoSeguimiento.registrarEntrenamiento(fechaInicio, objPrograma, id);
            if (valor) {
                System.out.println("**Programa Registrada Exitosamente**");
            } else {
                System.out.println("**Programa no registrada**");
            }

        } catch (Exception e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }
}
