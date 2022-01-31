package cliente.utilidades;

import java.rmi.RemoteException;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import s_gestion_usuarios.dto.PersonalDTO;
import s_gestion_usuarios.sop_rmi.GestionUsuariosInt;

/**
 *
 * @author Yahir Garcés
 */
public class ClienteDeObjetos {

    private static GestionUsuariosInt objRemoto;
    public static String usuarioAdm = "clave";
    public static String contrasenaAdm = "clave";
    public static int IdAdm = 12;

    public static void main(String[] args) {
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
        objRemoto = (GestionUsuariosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry,
                numPuertoRMIRegistry, "ObjetoRemotoUsuarios");
        MenuPrincipal();

    }

    private static void MenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Abrir sesion");
            System.out.println("2. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la identificacion");
                    int id = UtilidadesConsola.leerEntero();
                    System.out.println("Ingrese usuario");
                    String usuario = UtilidadesConsola.leerCadena();
                    System.out.println("Ingrese clave ");
                    String clave = UtilidadesConsola.leerCadena();

                    if (id == IdAdm && usuario == usuarioAdm && clave == contrasenaAdm) {
                        MenuAdmin();
                    } else {
                        PersonalDTO Usuario = new PersonalDTO();
                        try {
                            Usuario = objRemoto.consultarPersonal(id);
                        } catch (RemoteException ex) {
                            Logger.getLogger(ClienteDeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (Usuario != null && Usuario.getClave() == clave && Usuario.getUsuario() == usuario)
                            if (Usuario.getOcupacion() == "Secretaria") {
                                MenuSecretaria();
                            } else if (Usuario.getOcupacion() == "Profesioinal") {
                                MenuProfesional();
                            }
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

        } while (opcion != 3);
    }

    private static void consultarPlan() {
        try {
            System.out.println("Ingrese identificacion de usuario: ");
            int id = UtilidadesConsola.leerEntero();
            UsuarioDTO usuario = new UsuarioDTO();
            usuario = objRemoto.consultarUsuario(id);
            if (Usuario != null) {
                System.out.println("==Plan del cliente==");
                for (int i = 0; i < usuario.planEntrenamiento.length(); i++) {
                    System.out.println("Fecha inicio: " + usuario.planEntrenamiento.get(i).getFechaInicio);
                    for (int j = 0; j < usuario.planEntrenamiento.get(i).Programa.length(); j++) {
                        System.out.println("Dia: " + usuario.planEntrenamiento.get(i).Programa.get(j).getDia());
                        for (int k = 0; k < usuario.planEntrenamiento.get(i).Programa.get(k).ejercicio.length(); k++) {
                            System.out.println("==Ejercicio " + k + "==");
                            System.out.println(
                                    "Nombre ejercicio: " + usuario.planEntrenamiento.get(i).Programa.get(k).ejercicio
                                            .get(k).getNombreEjercicio());
                            System.out.println(
                                    "Repeticiones: " + usuario.planEntrenamiento.get(i).Programa.get(k).ejercicio.get(k)
                                            .getRepericiones());
                            System.out.println("Peso: "
                                    + usuario.planEntrenamiento.get(i).Programa.get(k).ejercicio.get(k).getPeso());
                        }
                        System.out.println("Faltas: " + usuario.planEntrenamiento.get(i).Programa.get(j).getFaltas());
                        for (int k = 0; k < usuario.planEntrenamiento.get(i).Programa.get(k).observaciones
                                .length(); k++) {
                            System.out.println("==Observacion " + k + "==");
                            System.out.println(usuario.planEntrenamiento.get(i).Programa.get(k).observaciones.get(k));
                        }
                    }
                }
            } else
                System.out.println("El plan de usuario con id: " + id + " no fue encontrado");
        } catch (Exception e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }

    }

    private static void MenuAdmin() {
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

    private static void MenuProfesional() {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Valorar Paf");
            System.out.println("2. Registrar asistencia");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    OpcionPro1();
                    break;
                case 2:
                    OpcionPro2();
                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 3);
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
                tipoFac = facultad();
                break;
        }
        return tipoFac;
    }

    private static String tipoUsuario() {
        System.out.println("Seleccione tipo de usuario: ");
        System.out.println("1. Secretaria ");
        System.out.println("2. Entrenador");
        int tipoUsuario = UtilidadesConsola.leerEntero();
        String tipoU = null;
        switch (tipoUsuario) {
            case 1:
                tipoU = "Secretaria";
                break;
            case 2:
                tipoU = "Entrenador";
                break;
            default:
                System.out.println("Tipo no valido, ingrese una opcion del 1 al 2");
                tipoU = tipoUsuario();
                break;
        }
        return tipoU;
    }

    private static boolean validarId(int id) {
        if (id >= 0) {
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
                System.out.println("**Usuario Registrado Exitosamente**");
            } else {
                System.out.println("**Usuario No Registrado, se alcanzó la cantidad máxim a de usuarios a registrar**");
            }
        } catch (RemoteException e) {
            System.out.println("La operacion no se pudo completar, intente nuevamente...");
        }
    }

    private static void OpcionAdm2() {
        try {
            System.out.println("==Buscar Usuario==");
            System.out.println("Ingrese identificacion a buscar");
            int id = UtilidadesConsola.leerEntero();
            PersonalDTO Usuario = new PersonalDTO();
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
            String usuario = UtilidadesConsola.leerCadena();
            if (!validarCredencial(usuario)) {
                OpcionSec1();
            }
            System.out.println("Ingrese clave ");
            String clave = UtilidadesConsola.leerCadena();
            if (!validarCredencial(clave)) {
                OpcionSec1();
            }
            UsuarioDTO objUsuario = new UsuarioDTO(nombreCompleto, tipoId, id, facultad, tipoUsuario, fechaIngreso,
                    patologias, usuario, clave);
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
            UsuariolDTO Usuario = new UsuarioDTO();
            Usuario = objRemoto.consultarUsuario(id);

            if (Usuario != null) {
                System.out.println("==Registro del Usuario==");
                System.out.println("Nombre completo: " + Usuario.getNombreCompleto());
                System.out.println("Tipo de identificacion: " + Usuario.getTipo_id());
                System.out.println("Identificacion: " + Usuario.getId());
                System.out.println("Facultad: " + Usuario.getFacultad());
                System.out.println("Tipo de usuario: " + Usuario.getTipo_usuario());
                System.out.println("Fecha de ingreso: " + Usuario.getFechaIngreso());
                System.out.println("Patologias: " + Usuario.getPatologias());
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
            String fechaValoracion = UtilidadesConsola.leerCadena;
            System.out.println("Ingrese la frecuencia cardiaca en reposo: ");
            int frecuenciaReposo = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese la frecuencia cardiaca activa: ");
            int frecuenciaActiva = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese la estatura: ");
            int estatura = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese las medidas del brazo: ");
            int brazo = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese las medidas de la pierna ");
            int pierna = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese las medidas del pecho: ");
            int pecho = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese las medidas de la cintura: ");
            int cintura = UtilidadesConsola.leerEntero();
            System.out.println("Ingrese el estado: ");
            String estado = UtilidadesConsola.leerCadena();

            ValoracionDTO objValoracion = new ValoracionDTO(id, fechaValoracion, frecuenciaReposo, frecuenciaActiva,
                    estatura, brazo, pierna, pecho, cintura, estado);
            boolean valor = objRemoto.registrarValoracion(objValoracion);
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

    private static void OpcionPro2() {
        try {
            System.out.println("==Registrar Asistencia==");
            System.out.println("Ingrese la identificacion del paciente: ");
            int id = UtilidadesConsola.leerEntero();
            UsuarioDTO usuario = new UsuarioDTO();
            usuario = objRemoto.consultarUsuario(id);
            if (usuario == null) {
                System.out.println("Usuario con id: " + id + " no existe");
                OpcionPro1();
            }
            System.out.println("Ingrese la fecha de la asistencia:");
            String fechaAsistencia = UtilidadesConsola.leerCadena();
            System.out.println("Ingrese las observaciones:");
            String observaciones = UtilidadesConsola.leerCadena();
            AsistenciaDTO objAsistencia = new AsistenciaDTO(id, fechaAsistencia, observaciones);
            boolean valor = objRemoto.registrarAsistencia(objAsistencia);
            if (valor) {
                System.out.println("**Asistencia Registrada Exitosamente**");
            } else {
                System.out.println("**Asistencia no registrada**");
            }

        } catch (Exception e) {
            System.out.println("La operación no se pudo completar, intente nuevamente...");
            System.out.println("Excepcion generada: " + e.getMessage());
        }
    }

    private static void OpcionPro3() {
        try {
            System.out.println("==Crear plann==");
            System.out.println("Ingrese la fecha de inicio: ");
            String fechaInicio = UtilidadesConsola.leerCadena();
            int opc = 0;
            do {
                System.out.println("1. Registrar Programa");
                System.out.println("2. Terminar");
                opc = UtilidadesConsola.leerEntero();
                ArrayList<ProgramaDTO> programas = new ArrayList<>();
                switch (opc) {
                    case 1:
                        System.out.println("Ingrese el dia:");
                        String dia = UtilidadesConsola.leerCadena();
                        System.out.println("Ingrese el numero de ejercicios:");
                        int numEjercicios = UtilidadesConsola.leerEntero();
                        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
                        for (int i = 0; i < numEjercicios; i++) {
                            System.out.println("Ingrese el nombre del ejercicio:");
                            String nombreEjercicio = UtilidadesConsola.leerCadena();
                            System.out.println("Ingrese la cantidad de repeticiones: ");
                            int repeticiones = UtilidadesConsola.leerEntero();
                            System.out.println("Ingrese el peso: ");
                            int peso = UtilidadesConsola.leerEntero();
                            Ejercicio objEjercicio = new Ejercicio(nombreEjercicio, repeticiones, peso);
                            ejercicios.add(objEjercicio);
                        }
                        // Falta esto en el constructor, no se si se arreglen por otro lado
                        System.out.println("Ingrese las faltas: ");
                        int faltas = UtilidadesConsola.leerEntero();
                        ArrayList<String> observaciones = new ArrayList<>();
                        for (int i = 0; i < numEjercicios; i++) {
                            System.out.println("Ingrese la observacion correspondiente al ejercicio " + i + ": ");
                            String observacion = UtilidadesConsola.leerCadena();
                            observaciones.add(observacion);
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
            ProgramaDTO objPrograma = new ProgramaDTO(dia,ejercicios,faltas,observaciones);
            boolean valor = objRemoto.registrarPrograma(objPrograma);
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
