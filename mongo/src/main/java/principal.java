
import com.mongodb.BasicDBObject;
import com.mongodb.CursorType;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import com.mongodb.client.model.ReturnDocument;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.management.Query;
import javax.swing.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.AEADBadTagException;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Judá Aarón
 */
public class principal extends javax.swing.JFrame {

    MongoDatabase db;
    MongoCollection<Alumno> students;
    MongoCollection<clasesita> Clase;
    MongoCollection<Preguntas> Questions;
    MongoCollection<Examen> Exams;
    MongoCollection<Nota> notas;
    Query k;

    clasesita claseSeleccionada = null;

    ArrayList<String> coleccion = new ArrayList();
    ArrayList<Alumno> alumnoos = new ArrayList();
    ArrayList<clasesita> clasess = new ArrayList();
    ArrayList<Preguntas> questions = new ArrayList();
    ArrayList<Examen> examenes = new ArrayList();
    ArrayList<Nota> notes = new ArrayList();

    ArrayList<String> preguntasExamen = new ArrayList();
    ArrayList<Object> respuestasExamen = new ArrayList();

    ConnectionString connString = new ConnectionString("mongodb://localhost:27017");
    CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connString)
            .codecRegistry(codecRegistry)
            .retryWrites(true)
            .build();
    MongoClient mongoClient = MongoClients.create(settings);

    clasesita cla = new clasesita();
    Alumno al = new Alumno();
    Examen ex = new Examen();
    Preguntas pr = new Preguntas();
    Nota nt = new Nota();

    int flag = 0;
    int CantidadPreguntas = 0, controlExamen = 0, cp = 0, puntaje = 0, aux, cero = 0, puntosPosibles, idxx = 0, itemList;
    Object verdadero = true, falso = false;
    String acumP = "";

    /**
     * Creates new form principal
     */
    public principal() {
        db = mongoClient.getDatabase("Universidad");
        Clase = db.getCollection("Clase", clasesita.class);
        Questions = db.getCollection("Preguntas", Preguntas.class);
        students = db.getCollection("Alumno", Alumno.class);
        Exams = db.getCollection("Examenes", Examen.class);
        notas = db.getCollection("Notas", Nota.class);

        List<Alumno> ListAlumno = students.find().into(new ArrayList<>());
        List<clasesita> ListClase = Clase.find().into(new ArrayList<>());
        List<Preguntas> ListPreguntas = Questions.find().into(new ArrayList<>());
        List<Examen> ListExamenes = Exams.find().into(new ArrayList<>());
        List<Nota> ListNotas = notas.find().into(new ArrayList<>());
        //ListClase.forEach(al -> System.out.println(al));
        alumnoos = (ArrayList<Alumno>) ListAlumno;
        clasess = (ArrayList<clasesita>) ListClase;
        questions = (ArrayList<Preguntas>) ListPreguntas;
        examenes = (ArrayList<Examen>) ListExamenes;
        notes = (ArrayList<Nota>) ListNotas;
//        for (int i = 0; i < alumnoos.size(); i++) {
//            System.out.println(alumnoos.get(i).getNombreAlumno());
//        }
//        for (int i = 0; i < clasess.size(); i++) {
//            
//        }

        initComponents();
        btn_finalizar.setEnabled(false);
        setResizable(false);

        //this.setExtendedState(MAXIMIZED_BOTH);
        setTitle("Exámenes");
        this.setLocationRelativeTo(null);
        for (int i = 0; i < clasess.size(); i++) {
            cb_clases.addItem(clasess.get(i).getNombreClase());
            cb_examen.addItem(clasess.get(i).getNombreClase());
            cb_mostrarClases.addItem(clasess.get(i).getNombreClase());
            // cb_examenAlumno.addItem(clasess.get(i).getNombreClase());
            // System.out.println(clasess.get(i).getNombreClase());
        }
//        for (int i = 0; i < notes.size(); i++) {
//            System.out.println(notes.get(i).getIdAlumno() + " este es de notas");
//        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Admin = new javax.swing.JDialog();
        crear = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        j_nombreClase = new javax.swing.JTextField();
        j_idClase = new javax.swing.JTextField();
        btn_guardarClase = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        j_idPregunta = new javax.swing.JTextField();
        j_titulo = new javax.swing.JTextField();
        j_descripcion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rb_verd = new javax.swing.JRadioButton();
        rb_fal = new javax.swing.JRadioButton();
        cb_clases = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        j_idExamen = new javax.swing.JTextField();
        btn_crearExamen = new javax.swing.JButton();
        j_cantP = new javax.swing.JTextField();
        cb_examen = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jSeleccionFecha = new com.toedter.calendar.JDateChooser();
        btn_cerrarSesion = new javax.swing.JButton();
        btn_mostrardatosAdmiin = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        fondo_Admin = new javax.swing.JLabel();
        Estudiantes = new javax.swing.JDialog();
        jLabel23 = new javax.swing.JLabel();
        cb_examenAlumno = new javax.swing.JComboBox<>();
        btn_comenzar = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        j_examenes = new javax.swing.JList<>();
        btn_verExamenes = new javax.swing.JButton();
        btn_singoutA = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        registrar = new javax.swing.JDialog();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        j_idAlumno = new javax.swing.JTextField();
        j_nombreA = new javax.swing.JTextField();
        j_login = new javax.swing.JTextField();
        j_pass = new javax.swing.JTextField();
        btn_registrar = new javax.swing.JButton();
        btn_regresar1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Exameness = new javax.swing.JDialog();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ventana_examen = new javax.swing.JTextArea();
        btn_avanzar = new javax.swing.JButton();
        btn_finalizar = new javax.swing.JButton();
        v = new javax.swing.JRadioButton();
        f = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        f_ex = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        VerNotas = new javax.swing.JDialog();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        btn_regresar_ver = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        area3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        area4 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        area5 = new javax.swing.JTextArea();
        jl = new javax.swing.JLabel();
        datosAdmin = new javax.swing.JDialog();
        btnregresar_mostrar = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        cb_mostrarClases = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        area7 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        area8 = new javax.swing.JTextArea();
        fondo_ver = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        passLogin = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btn_ingresar = new javax.swing.JButton();
        userLogin = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btn_salir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        Admin.setUndecorated(true);
        Admin.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel2.setText("Nombre clase");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        j_nombreClase.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel1.add(j_nombreClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 80, -1));

        j_idClase.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel1.add(j_idClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 80, -1));

        btn_guardarClase.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_guardarClase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        btn_guardarClase.setText("Guardar Clase");
        btn_guardarClase.setContentAreaFilled(false);
        btn_guardarClase.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/save (1).png"))); // NOI18N
        btn_guardarClase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guardarClaseMouseClicked(evt);
            }
        });
        jPanel1.add(btn_guardarClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 160, 50));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel1.setText("Id Clase");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 50, 20));

        crear.addTab("CREAR CLASE", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diskette.png"))); // NOI18N
        jButton2.setText("Guardar pregunta");
        jButton2.setContentAreaFilled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/diskette (1).png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 180, 60));

        j_idPregunta.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel2.add(j_idPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 140, -1));

        j_titulo.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel2.add(j_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 140, -1));

        j_descripcion.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel2.add(j_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 140, -1));

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel6.setText("Tipo");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel4.setText("Titulo ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel3.setText("Seleccione una clase");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel11.setText("Descripcion");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, -1));

        buttonGroup1.add(rb_verd);
        rb_verd.setText("V");
        jPanel2.add(rb_verd, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        buttonGroup1.add(rb_fal);
        rb_fal.setText("F");
        jPanel2.add(rb_fal, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        cb_clases.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel2.add(cb_clases, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 150, 30));

        jLabel22.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel22.setText("Id Pregunta");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        crear.addTab("CREAR PREGUNTAS", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel20.setText("Cantidad preguntas");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        j_idExamen.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel3.add(j_idExamen, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 70, -1));

        btn_crearExamen.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_crearExamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pen.png"))); // NOI18N
        btn_crearExamen.setText("Crear examen");
        btn_crearExamen.setContentAreaFilled(false);
        btn_crearExamen.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/pen (1).png"))); // NOI18N
        btn_crearExamen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_crearExamenMouseClicked(evt);
            }
        });
        jPanel3.add(btn_crearExamen, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 190, 70));

        j_cantP.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel3.add(j_cantP, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 70, -1));

        cb_examen.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jPanel3.add(cb_examen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, 30));

        jLabel21.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel21.setText("Id Examen");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 50, 20));

        jLabel40.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel40.setText("Seleccione la fecha para el examen");
        jPanel3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 190, 20));

        jLabel44.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel44.setText("Seleccione una clase");
        jPanel3.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 20));
        jPanel3.add(jSeleccionFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 170, 30));

        crear.addTab("CREAR EXAMEN", jPanel3);

        Admin.getContentPane().add(crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 480, 390));

        btn_cerrarSesion.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_cerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow.png"))); // NOI18N
        btn_cerrarSesion.setText("Cerrar sesión");
        btn_cerrarSesion.setContentAreaFilled(false);
        btn_cerrarSesion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow (1).png"))); // NOI18N
        btn_cerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMouseClicked(evt);
            }
        });
        Admin.getContentPane().add(btn_cerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 420, 130, 50));

        btn_mostrardatosAdmiin.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_mostrardatosAdmiin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/documents.png"))); // NOI18N
        btn_mostrardatosAdmiin.setText("Mostrar datos");
        btn_mostrardatosAdmiin.setContentAreaFilled(false);
        btn_mostrardatosAdmiin.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/documents (1).png"))); // NOI18N
        btn_mostrardatosAdmiin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_mostrardatosAdmiinMouseClicked(evt);
            }
        });
        btn_mostrardatosAdmiin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostrardatosAdmiinActionPerformed(evt);
            }
        });
        Admin.getContentPane().add(btn_mostrardatosAdmiin, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 423, -1, 40));

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel5.setText("ADMINISTRADOR");
        Admin.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 20));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo7.gif"))); // NOI18N
        Admin.getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 430, 490));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo7.gif"))); // NOI18N
        Admin.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(-170, 0, 630, 490));

        Estudiantes.setBackground(new java.awt.Color(255, 255, 255));
        Estudiantes.setUndecorated(true);
        Estudiantes.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel23.setText("Seleccione el examen por el ID");
        Estudiantes.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 160, 30));

        cb_examenAlumno.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        cb_examenAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_examenAlumnoItemStateChanged(evt);
            }
        });
        Estudiantes.getContentPane().add(cb_examenAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 190, -1));

        btn_comenzar.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_comenzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test.png"))); // NOI18N
        btn_comenzar.setText("Iniciar cuestionario");
        btn_comenzar.setContentAreaFilled(false);
        btn_comenzar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/test (1).png"))); // NOI18N
        btn_comenzar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_comenzarMouseClicked(evt);
            }
        });
        Estudiantes.getContentPane().add(btn_comenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 170, 40));

        j_examenes.setModel(new DefaultListModel());
        jScrollPane8.setViewportView(j_examenes);

        Estudiantes.getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 160, 340));

        btn_verExamenes.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_verExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exam.png"))); // NOI18N
        btn_verExamenes.setText("Calificaciones");
        btn_verExamenes.setContentAreaFilled(false);
        btn_verExamenes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/exam (1).png"))); // NOI18N
        btn_verExamenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_verExamenesMouseClicked(evt);
            }
        });
        Estudiantes.getContentPane().add(btn_verExamenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 220, 40));

        btn_singoutA.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_singoutA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow.png"))); // NOI18N
        btn_singoutA.setText("Cerra sesión");
        btn_singoutA.setContentAreaFilled(false);
        btn_singoutA.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow (1).png"))); // NOI18N
        btn_singoutA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_singoutAMouseClicked(evt);
            }
        });
        Estudiantes.getContentPane().add(btn_singoutA, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 180, -1));

        jLabel45.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel45.setText("Seleccione la clase");
        Estudiantes.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 110, 30));

        jLabel24.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel24.setText("jLabel24");
        Estudiantes.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 20));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo 3.gif"))); // NOI18N
        Estudiantes.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 600));

        registrar.setUndecorated(true);
        registrar.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel13.setText("Registro");
        registrar.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel14.setText("Nombre completo");
        registrar.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLabel15.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel15.setText("Login");
        registrar.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 30, -1));

        jLabel16.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel16.setText("Contraseña");
        registrar.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        j_idAlumno.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        registrar.getContentPane().add(j_idAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 170, -1));

        j_nombreA.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        registrar.getContentPane().add(j_nombreA, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 170, -1));

        j_login.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        registrar.getContentPane().add(j_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 170, -1));

        j_pass.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        registrar.getContentPane().add(j_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 170, -1));

        btn_registrar.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png"))); // NOI18N
        btn_registrar.setText("Registrar");
        btn_registrar.setContentAreaFilled(false);
        btn_registrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/edit (1).png"))); // NOI18N
        btn_registrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_registrarMouseClicked(evt);
            }
        });
        registrar.getContentPane().add(btn_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 230, 110, 70));

        btn_regresar1.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_regresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/undo.png"))); // NOI18N
        btn_regresar1.setText("Regresar");
        btn_regresar1.setContentAreaFilled(false);
        btn_regresar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/undo (1).png"))); // NOI18N
        btn_regresar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_regresar1MouseClicked(evt);
            }
        });
        registrar.getContentPane().add(btn_regresar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 120, 50));

        jLabel17.setFont(new java.awt.Font("Agency FB", 3, 24)); // NOI18N
        jLabel17.setText("Sistema de exámenes");
        registrar.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 190, -1));

        jLabel18.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel18.setText("Id Alumno");
        registrar.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo8.gif"))); // NOI18N
        registrar.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 500));

        Exameness.setBackground(new java.awt.Color(255, 255, 255));
        Exameness.setUndecorated(true);
        Exameness.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel27.setText("jLabel27");
        Exameness.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 20));

        ventana_examen.setEditable(false);
        ventana_examen.setColumns(20);
        ventana_examen.setRows(5);
        ventana_examen.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 153, 255), new java.awt.Color(0, 153, 255)));
        jScrollPane1.setViewportView(ventana_examen);

        Exameness.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 780, 140));

        btn_avanzar.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_avanzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/right-arrow.png"))); // NOI18N
        btn_avanzar.setText("Avanzar");
        btn_avanzar.setContentAreaFilled(false);
        btn_avanzar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/right-arrow (1).png"))); // NOI18N
        btn_avanzar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_avanzarMouseClicked(evt);
            }
        });
        Exameness.getContentPane().add(btn_avanzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(679, 383, 140, 40));

        btn_finalizar.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_finalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exam3.png"))); // NOI18N
        btn_finalizar.setText("Finalizar exámen");
        btn_finalizar.setContentAreaFilled(false);
        btn_finalizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/exam4.png"))); // NOI18N
        btn_finalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_finalizarMouseClicked(evt);
            }
        });
        btn_finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_finalizarActionPerformed(evt);
            }
        });
        Exameness.getContentPane().add(btn_finalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(699, 443, 150, 40));

        buttonGroup2.add(v);
        v.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        v.setText("Verdadero");
        Exameness.getContentPane().add(v, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        buttonGroup2.add(f);
        f.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        f.setText("Falso");
        Exameness.getContentPane().add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, -1));

        jLabel25.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel25.setText("Este cuestionario no se puede repetir");
        Exameness.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel31.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel31.setText("Este cuestionario no tiene limite de tiempo");
        Exameness.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel32.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel32.setText("No se permite regresar a preguntas anteriores");
        Exameness.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel26.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel26.setText("Cada pregunta tiene un valor de 5%");
        Exameness.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/true-or-false.png"))); // NOI18N
        Exameness.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 30, 40));

        info.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        info.setText("jLabel36");
        Exameness.getContentPane().add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 180, 20));

        f_ex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo5.png"))); // NOI18N
        Exameness.getContentPane().add(f_ex, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 680, 350));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo5.png"))); // NOI18N
        Exameness.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 730, 340));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo5.png"))); // NOI18N
        Exameness.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 310));

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo4.jpg"))); // NOI18N
        Exameness.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 1030, 200));

        VerNotas.setUndecorated(true);
        VerNotas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        VerNotas.getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 290, -1));

        jLabel28.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel28.setText("ID Examen");
        VerNotas.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel37.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel37.setText("Puntos posibles");
        VerNotas.getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jLabel29.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel29.setText("Nota calculada");
        VerNotas.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        btn_regresar_ver.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_regresar_ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/undo.png"))); // NOI18N
        btn_regresar_ver.setText("Regresar");
        btn_regresar_ver.setContentAreaFilled(false);
        btn_regresar_ver.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/undo (1).png"))); // NOI18N
        btn_regresar_ver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_regresar_verMouseClicked(evt);
            }
        });
        VerNotas.getContentPane().add(btn_regresar_ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, -1, -1));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBar(null);

        area3.setEditable(false);
        area3.setColumns(20);
        area3.setRows(5);
        area3.setBorder(null);
        jScrollPane2.setViewportView(area3);

        VerNotas.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 90, 410));

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBar(null);

        area4.setEditable(false);
        area4.setColumns(20);
        area4.setRows(5);
        area4.setBorder(null);
        jScrollPane4.setViewportView(area4);

        VerNotas.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 90, 410));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBar(null);

        area5.setEditable(false);
        area5.setColumns(20);
        area5.setRows(5);
        area5.setBorder(null);
        jScrollPane3.setViewportView(area5);

        VerNotas.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 90, 410));

        jl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fond10.gif"))); // NOI18N
        VerNotas.getContentPane().add(jl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 490));

        datosAdmin.setUndecorated(true);
        datosAdmin.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnregresar_mostrar.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btnregresar_mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/undo.png"))); // NOI18N
        btnregresar_mostrar.setText("Regresar");
        btnregresar_mostrar.setContentAreaFilled(false);
        btnregresar_mostrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/undo (1).png"))); // NOI18N
        btnregresar_mostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregresar_mostrarMouseClicked(evt);
            }
        });
        datosAdmin.getContentPane().add(btnregresar_mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 130, 40));

        jLabel41.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel41.setText("Preguntas asociadas a la clase");
        datosAdmin.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 150, 20));

        cb_mostrarClases.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        cb_mostrarClases.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_mostrarClasesItemStateChanged(evt);
            }
        });
        datosAdmin.getContentPane().add(cb_mostrarClases, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 150, 30));

        jLabel42.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel42.setText("Exámen asociado a la clase");
        datosAdmin.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 150, 20));

        jLabel43.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel43.setText("Seleccione una clase");
        datosAdmin.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 20));

        area7.setEditable(false);
        area7.setColumns(20);
        area7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        area7.setRows(5);
        jScrollPane7.setViewportView(area7);

        datosAdmin.getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 420, 360));

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        area8.setEditable(false);
        area8.setColumns(20);
        area8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        area8.setRows(5);
        jScrollPane6.setViewportView(area8);

        datosAdmin.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 420, 50));

        fondo_ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo12.jpg"))); // NOI18N
        datosAdmin.getContentPane().add(fondo_ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 510));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator4.setBackground(new java.awt.Color(0, 153, 255));
        jSeparator4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 190, 10));

        jSeparator3.setBackground(new java.awt.Color(0, 153, 255));
        jSeparator3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 140, 10));

        passLogin.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        passLogin.setBorder(null);
        getContentPane().add(passLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 140, 20));

        jLabel7.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel7.setText("Password");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 100, 20));

        jLabel9.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel9.setText("Username");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 100, 20));

        jSeparator2.setBackground(new java.awt.Color(0, 153, 255));
        jSeparator2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 90, 10));

        btn_ingresar.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_ingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sign-in.png"))); // NOI18N
        btn_ingresar.setText("Entrar");
        btn_ingresar.setContentAreaFilled(false);
        btn_ingresar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/sign-in (1).png"))); // NOI18N
        btn_ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ingresarMouseClicked(evt);
            }
        });
        getContentPane().add(btn_ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 140, 40));

        userLogin.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        userLogin.setBorder(null);
        userLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userLoginActionPerformed(evt);
            }
        });
        getContentPane().add(userLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 140, 20));

        jSeparator1.setBackground(new java.awt.Color(0, 153, 255));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 140, 10));

        btn_salir.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit (2).png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setContentAreaFilled(false);
        btn_salir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/exit (3).png"))); // NOI18N
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 110, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Registrate");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 80, 30));

        jLabel10.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel10.setText("¿Eres estudiante nuevo?");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 37, -1, 30));

        jLabel12.setFont(new java.awt.Font("Agency FB", 3, 24)); // NOI18N
        jLabel12.setText("Sistema de exámenes");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 180, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fondo2.gif"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

        if (j_idPregunta.getText().equals("") || j_titulo.getText().equals("") || j_descripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos");
        } else {
            boolean correcto = false;
            int idQuestion = Integer.parseInt(j_idPregunta.getText());
            String Titutlo = j_titulo.getText();
            String Descripcion = j_descripcion.getText();
            int idClase = cb_clases.getSelectedIndex();
            boolean tipo = false, validar = false;

            for (int i = 0; i < questions.size(); i++) {
                if (questions.get(i).getIdQ() == idQuestion) {
                    validar = true;
                }
            }
            if (validar == false) {
                if (rb_verd.isSelected()) {
                    tipo = true;
                    Preguntas qs = new Preguntas(idQuestion, Titutlo, Descripcion, clasess.get(idClase).getIdClase(), tipo);
                    Questions.insertOne(qs);
                    JOptionPane.showMessageDialog(null, "Pregunta agregada con exito");
                    j_idPregunta.setText("");
                    j_titulo.setText("");
                    j_descripcion.setText("");
                    buttonGroup1.clearSelection();
                } else {
                    tipo = false;
                    Preguntas qs = new Preguntas(idQuestion, Titutlo, Descripcion, clasess.get(idClase).getIdClase(), tipo);
                    Questions.insertOne(qs);
                    JOptionPane.showMessageDialog(null, "Pregunta agregada con exito");
                    j_idPregunta.setText("");
                    j_titulo.setText("");
                    j_descripcion.setText("");
                    buttonGroup1.clearSelection();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Id pregunta ya existe, intente mas tarde");
                j_idPregunta.setText("");
                j_titulo.setText("");
                j_descripcion.setText("");
                buttonGroup1.clearSelection();
            }
        }


    }//GEN-LAST:event_jButton2MouseClicked

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        //JFrame.setVisible(false);
        registrar.pack();
        registrar.setModal(true);
        registrar.setLocationRelativeTo(null);
        registrar.setVisible(true);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void btn_regresar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_regresar1MouseClicked
        registrar.dispose();
        j_idAlumno.setText("");
        j_nombreA.setText("");
        j_login.setText("");
        j_pass.setText("");

    }//GEN-LAST:event_btn_regresar1MouseClicked

    private void btn_registrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrarMouseClicked
        if (j_idAlumno.getText().equals("") || j_nombreA.getText().equals("") || j_login.getText().equals("") || j_pass.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Para registrarte debes de llenar todos los campos");

        } else {
            int idAlumno = Integer.parseInt(j_idAlumno.getText());
            String Nombre = j_nombreA.getText();
            String logiin = j_login.getText();
            String sha = j_pass.getText();
            // System.out.println(doHashing(sha));
            Alumno alu = new Alumno(idAlumno, Nombre, logiin, doHashing(sha));
            alumnoos.add(alu);
            students.insertOne(alu);
            JOptionPane.showMessageDialog(null, "Has sido registrado con exito");
            j_idAlumno.setText("");
            j_nombreA.setText("");
            j_login.setText("");
            j_pass.setText("");
            registrar.dispose();
        }


    }//GEN-LAST:event_btn_registrarMouseClicked

    private void btn_ingresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ingresarMouseClicked
        String loginn = userLogin.getText();
        String passw = passLogin.getText();
        String pp = doHashing(passw);
        //System.out.println(pp);
        boolean normal = false;

        if (userLogin.getText().equals("") || passLogin.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No puedes dejar campos vacíos");
        } else {
            if (alumnoos.get(0).getUsername().equals(loginn) && alumnoos.get(0).getPassword().equals(doHashing(passw))) {
                Admin.pack();
                Admin.setModal(true);
                Admin.setLocationRelativeTo(null);
                Admin.setVisible(true);

                userLogin.setText("");
                passLogin.setText("");
            } else {
                for (int i = 1; i < alumnoos.size(); i++) {
                    if (alumnoos.get(i).getUsername().equals(loginn) && alumnoos.get(i).getPassword().equals(doHashing(passw))) {
                        normal = true;
                        flag = i;
                        break;
                    }
                }
                cb_examenAlumno.setModel(new DefaultComboBoxModel<>()); // setea los items del combo
                jLabel24.setText("Bienvenido " + alumnoos.get(flag).getNombreAlumno());
//            tabla.setModel(new DefaultTableModel());
//            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
//            Object[] row = {"Id Examen", "Nota Examen"};
//            model.addRow(row);
                if (normal == true) {
                    for (int i = 0; i < clasess.size(); i++) {

                        cb_examenAlumno.addItem(clasess.get(i).getNombreClase());
                    }
                    Estudiantes.pack();
                    Estudiantes.setModal(true);
                    Estudiantes.setLocationRelativeTo(null);
                    Estudiantes.setVisible(true);

                    userLogin.setText("");
                    passLogin.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos");
                    userLogin.setText("");
                    passLogin.setText("");
                }
            }
        }


    }//GEN-LAST:event_btn_ingresarMouseClicked

    private void btn_cerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMouseClicked
        Admin.dispose();
    }//GEN-LAST:event_btn_cerrarSesionMouseClicked

    private void btn_guardarClaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardarClaseMouseClicked

        if (j_idClase.getText().equals("") || j_nombreClase.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos");
        } else {
            int idClasee = Integer.parseInt(j_idClase.getText());
            String NameClase = j_nombreClase.getText();
            boolean validar = false;
            for (int i = 0; i < clasess.size(); i++) {
                if (idClasee == clasess.get(i).getIdClase()) {
                    validar = true;
                    break;
                }
            }
            if (validar == true) {
                JOptionPane.showMessageDialog(null, "Id clase ya existe, intente de nuevo");
                j_idClase.setText("");
            } else {
                for (int i = 0; i < clasess.size(); i++) {
                    cb_clases.removeItem(clasess.get(i).getNombreClase());
                    cb_examen.removeItem(clasess.get(i).getNombreClase());
                    //cb_mostrarClases.removeItem(clasess.get(i).getNombreClase());
                }

                clasesita cl = new clasesita(idClasee, NameClase);
                Clase.insertOne(cl);
                clasess.add(cl);

                JOptionPane.showMessageDialog(null, "Clase creada con exito");
                for (int i = 0; i < clasess.size(); i++) {
                    cb_clases.addItem(clasess.get(i).getNombreClase());
                    cb_examen.addItem(clasess.get(i).getNombreClase());
                    cb_mostrarClases.addItem(clasess.get(i).getNombreClase());
                }
                j_idClase.setText("");
                j_nombreClase.setText("");
            }
        }


    }//GEN-LAST:event_btn_guardarClaseMouseClicked

    private void btn_crearExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_crearExamenMouseClicked
        if (j_idExamen.getText().equals("") || j_cantP.getText().equals("") || jSeleccionFecha == null) {
            JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos");
        } else {

            try {
                //            String formato = "dd/MM/yyyy";
//            Date date = jSeleccionFecha.getDate();
//  SimpleDateFormat sdf = new SimpleDateFormat(formato);
//lblfecha.setText(sdf.format(date));
                int idExamen = Integer.parseInt(j_idExamen.getText());
                int cantPreg = Integer.parseInt(j_cantP.getText());
                int idClasee = cb_examen.getSelectedIndex();
                String formato = "dd/MM/yyyy";
                Date date = jSeleccionFecha.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                SimpleDateFormat formatoo = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaExa = formatoo.parse(sdf.format(date));
                boolean valido = false, ValidarID = false;

//        long cursi = db.getCollection("Preguntas").countDocuments();
//        +
//       db.getCollection("Preguntas").find(eq("idClase", clasess.get(idClasee).getIdClase())).forEach(doc -> System.out.println(doc.toJson()));
                Date FechaValidacion = new Date();
                if ((date.getDay() < FechaValidacion.getDay()) || (date.getMonth() < FechaValidacion.getMonth()) || (date.getYear() < FechaValidacion.getYear())) {
                    JOptionPane.showMessageDialog(null, "No puedes poner fechas antiguas");
                } else {
                    for (Document cur : db.getCollection("Preguntas").find(eq("idClase", clasess.get(idClasee).getIdClase()))) {
                        CantidadPreguntas++;
                    }   //long count = db.getCollection("Preguntas").countDocuments();
// System.out.println(CantidadPreguntas);
                    if (cantPreg <= CantidadPreguntas && cantPreg >= 1) {
                        for (int i = 0; i < examenes.size(); i++) {
                            if (idExamen == examenes.get(i).getIdExamen()) {
                                ValidarID = true;
                                break;
                            }
                        }

                        if (ValidarID == false) {

                            Examen exs = new Examen(idExamen, clasess.get(idClasee).getIdClase(), cantPreg, fechaExa);

                            clasess.get(cb_examen.getSelectedIndex()).getTests().add(exs);
                            for (int i = 0; i < clasess.get(cb_examen.getSelectedIndex()).getTests().size(); i++) {
                                System.out.println("examenes pertenecientes a esta clase jeje" + clasess.get(cb_examen.getSelectedIndex()).getTests().get(i).getIdExamen());
                            }
                            Exams.insertOne(exs);
                            claseSeleccionada = Clase.find(eq("idClase", clasess.get(cb_examen.getSelectedIndex()).getIdClase())).first();
//                        claseSeleccionada.idClase = clasess.get(cb_examen.getSelectedIndex()).getIdClase();
                            JOptionPane.showMessageDialog(null, claseSeleccionada);

                            claseSeleccionada.addExamen(exs);// guarda el examen en la clase seleccionada.

                            //System.out.println(claseSeleccionada);
                            claseSeleccionada = Clase.findOneAndReplace(eq("idClase", clasess.get(cb_examen.getSelectedIndex()).getIdClase()),
                                    claseSeleccionada, new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER));

                            //JOptionPane.showMessageDialog(null, claseSeleccionada);
//                        claseSeleccionada.getTests().add(exs);
                            //Clase.aggregate(clasess.get(cb_examen.getSelectedIndex()).getTests().add(exs));
                            // clasesita cl = new Examen(idExamen, idClasee, CantidadPreguntas);
                            //Clase.insertOne(cl);
                            //clasess.add(cl);
                            //ex = Exams.findOneAndReplace(eq("idclase",clasess.get(cb_examen.getSelectedIndex()).getIdClase()), ex, new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER));
                            //JOptionPane.showMessageDialog(null,ex);
//                        Document updateArray = new Document();
//                        updateArray.append("$set", new BasicDBObject("tests", exs));
//                        // updateArray.put("tests", exs);
//                        BasicDBObject buscaPorId = new BasicDBObject();
//                        buscaPorId.append("idClase", clasess.get(cb_examen.getSelectedIndex()).getIdClase());
//
//                        //Actualiza la clase y el array de examen
//                        //db.getCollection("Clase").updateMany(buscaPorId, updateArray);
//                        db.getCollection("Clase").insertOne(updateArray);
                            //Clase.insertOne(cla);
                            JOptionPane.showMessageDialog(null, "Examen creado con éxito");
                            j_idExamen.setText("");
                            j_cantP.setText("");

                        } else {
                            JOptionPane.showMessageDialog(null, "idExamen ya existe");
                            j_idExamen.setText("");
                            j_cantP.setText("");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Cantidad de preguntas es mayor a cantidad de preguntas de la clase.");
                        j_cantP.setText("");
                    }
                }

            } catch (ParseException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btn_crearExamenMouseClicked

    private void userLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userLoginActionPerformed

    private void btn_singoutAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_singoutAMouseClicked

//        for (int i = 0; i < clasess.get(1).getTests().size(); i++) {
//            //for (int j = 0; j < clasess.get(i).getTests().size() - 1; j++) {
//                JOptionPane.showMessageDialog(null, clasess.get(1).getTests().size());
//                System.out.println(clasess.get(1).getTests().get(i).getIdExamen());
//            //}
//
//        }
        Estudiantes.dispose();
    }//GEN-LAST:event_btn_singoutAMouseClicked

    private void btn_avanzarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_avanzarMouseClicked

        verdadero = true;
        falso = false;
        // System.out.println(cp);

        if (cp < controlExamen) {

            cp++;

            ventana_examen.setText(preguntasExamen.get(cp));
            acumP = "Pregunta: " + (cp + 1) + " de " + controlExamen;
            info.setText(acumP);
            if (v.isSelected()) {
                verdadero = true;
                if (verdadero.equals(respuestasExamen.get(cp - 1))) {
                    puntaje += 5;
                } else {
                    puntaje += 0;
                }
            }

            if (f.isSelected()) {
                falso = false;
                if (falso.equals(respuestasExamen.get(cp - 1))) {
                    puntaje += 5;
                } else {
                    puntaje += 0;
                }
            }
            //cp--;
            buttonGroup2.clearSelection();
            if (cp == controlExamen - 1) {

                btn_avanzar.setEnabled(false);

                btn_finalizar.setEnabled(true);
            }
        }


    }//GEN-LAST:event_btn_avanzarMouseClicked

    private void btn_finalizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_finalizarMouseClicked
//        if (!v.isSelected() && !f.isSelected()) {
//            puntaje += 0;
//        } else {
//            if (cp == controlExamen) {
//                if (v.isSelected()) {
//                    // System.out.println(verdadero + " verdadero " + respuestasExamen.get(cp+1));
//                    verdadero = true;
//                    if (verdadero.equals(respuestasExamen.get(cp-1))) {
//                        puntaje += 5;
//                        //  System.out.println(verdadero + " verdadero " + respuestasExamen.get(cp+1));
//                    }
//                } else {
//                    falso = false;
//                    //System.out.println(falso + " falso " + respuestasExamen.get(cp+1));
//                    if (falso.equals(respuestasExamen.get(cp-1))) {
//                        // System.out.println(falso + " falso " + respuestasExamen.get(cp+1));
//                        puntaje += 5;
//
//                    }
//                }
//            }
//
//        }
        //System.out.print("");
        if (cp == controlExamen - 1) {
            if (v.isSelected()) {
                verdadero = true;
                if (verdadero.equals(respuestasExamen.get(controlExamen - 1))) {
                    puntaje += 5;
                } else {
                    puntaje += 0;
                }
            }

            if (f.isSelected()) {
                falso = false;
                if (falso.equals(respuestasExamen.get(controlExamen - 1))) {
                    puntaje += 5;
                } else {
                    puntaje += 0;
                }
            }
        }
        puntosPosibles = controlExamen * 5;

        Nota ntt = new Nota(alumnoos.get(flag).getIdAlumno(), clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(itemList).getIdExamen(), puntaje, puntosPosibles);
        notas.insertOne(ntt);
        notes.add(ntt);
        cp = 0;

        JOptionPane.showMessageDialog(null, "Tu puntaje obtenido es: " + puntaje);
        Exameness.dispose();
        Estudiantes.pack();
        Estudiantes.setModal(true);
        Estudiantes.setLocationRelativeTo(null);
        Estudiantes.setVisible(true);


    }//GEN-LAST:event_btn_finalizarMouseClicked

    private void btn_comenzarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_comenzarMouseClicked
        //        for (int i = 0; i < examenes.size(); i++) {
//            if (examenes.get(i).getIdExamen() == notes.get(flag).getIdExamen()) {
//                System.out.println("jeje");
//                break;
//            }
//        }
        itemList = j_examenes.getSelectedIndex();
        JOptionPane.showMessageDialog(null, itemList);
        JOptionPane.showMessageDialog(null, clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(itemList).getIdExamen());
        for (int i = 0; i < notes.size(); i++) {
            System.out.println(notes.get(i).getIdExamen() + "es es del arrayList");
        }
        Date actual2 = null;
        String formato = "dd/MM/yyyy";
        Date actual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        SimpleDateFormat formatoo = new SimpleDateFormat("dd/MM/yyyy");
        try {
            actual2 = formatoo.parse(sdf.format(actual));
        } catch (ParseException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        cp = 0;
        puntaje = 0;
        ventana_examen.setText("");
        buttonGroup2.clearSelection();
        btn_avanzar.setEnabled(true);
        btn_finalizar.setEnabled(false);
        for (int i = 0; i < questions.size(); i++) {
            questions.remove(questions.get(i));
        }
        List<Preguntas> LP = Questions.find().into(new ArrayList<>());
        questions = (ArrayList<Preguntas>) LP;
        for (int i = 0; i < questions.size(); i++) {

            preguntasExamen.remove(questions.get(i).getDescripcion());
            respuestasExamen.remove(questions.get(i).isTipo());
        }

        boolean validar = false, validar2 = false;
        int idExa, idAl = alumnoos.get(flag).getIdAlumno();

        for (int i = 0; i < clasess.size(); i++) {
            System.out.println(clasess.get(i).getIdClase() + " este es del for");
        }
        System.out.println(clasess.get(cb_examenAlumno.getSelectedIndex()).getIdClase() + "este es del combos");
//        System.out.println(idxx + " idxx");
//        System.out.println(alumnoos.get(flag).getIdAlumno() + "control id alumno");
        for (int i = 0; i < examenes.size(); i++) {
            if (examenes.get(i).getIdClase() == clasess.get(cb_examenAlumno.getSelectedIndex()).getIdClase()) {
                idxx = examenes.get(i).getIdExamen();
            }
        }
        for (int i = 0; i < notes.size(); i++) {
            if ((notes.get(i).getIdExamen() == clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(itemList).getIdExamen()) && (notes.get(i).getIdAlumno() == idAl)) {
                System.out.println(notes.get(i).getIdExamen() + "de notes");
                System.out.println(idxx + "del itemList");
                //JOptionPane.showMessageDialog(null, "Este alumno ya hizo algun examen");
                validar = true;
                break;
            }
        }
//        for (int i = 0; i < clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().size(); i++) {
//            if(clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(i).getIdExamen() == idxx){
//                
//            }
//        }

        for (int i = 0; i < examenes.size(); i++) {
            if (examenes.get(i).getIdExamen() == clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(itemList).getIdExamen()) {
                if (!(examenes.get(i).getFecha().equals(actual2))) {
                    JOptionPane.showMessageDialog(null, examenes.get(i).getFecha() + "este del examen " + actual2 + " esta es la actual");
                    validar2 = true;
                }
            }
        }
//        Bson fil = eq("idExamen", notes.get(flag-1).getIdExamen());
//        try {
//            idExa = notas.find(fil).first().getIdExamen();
//            System.out.println(idExa + " este es control examen");
//        } catch (Exception e) {
//        }

//
        if (validar == true) {
            JOptionPane.showMessageDialog(null, "Este examen ya fue realizado");
        } else {
            if (validar2 == true) {
                JOptionPane.showMessageDialog(null, "Aun no es la fecha para elaborar este examen");
            } else {
                int cont = 0;
                Estudiantes.setVisible(false);
                jLabel27.setText("Examen de " + clasess.get(cb_examenAlumno.getSelectedIndex()).getNombreClase());

                int puntos = 0;
                for (int i = 0; i < clasess.size(); i++) {
                    //  System.out.println(i + " indice " + clasess.get(i).getIdClase());
                }
                // System.out.println(examenes.get(idxx).idClase + "getselected");
                for (int i = 0; i < examenes.size(); i++) {
                    // System.out.println(examenes.get(i).getCantPreguntas() + "este es el cant preguntas");
                }
                for (int i = 0; i < examenes.size(); i++) {
                    // System.out.println(i + "" + examenes.get(i).getIdClase());
                }
//        for (int i = 0; i < examenes.size(); i++) {
//            if (examenes.get(i).getIdClase() == examenes.get(cb_examenAlumno.getSelectedIndex()).getIdClase()) {
//                System.out.println(examenes.get(cb_examenAlumno.getSelectedIndex()).getCantPreguntas());
//              controlExamen = examenes.get(cb_examenAlumno.getSelectedIndex()).getCantPreguntas();
//                break;
//            }
//        }
                Bson filter = eq("idClase", clasess.get(cb_examenAlumno.getSelectedIndex()).getIdClase());
                try {
                    controlExamen = Exams.find(filter).first().getCantPreguntas();
                    //  System.out.println(controlExamen + " este es control examen");
                } catch (Exception e) {
                }

                for (int i = 0; i < questions.size(); i++) {
                    if (questions.get(i).getIdClase() == clasess.get(cb_examenAlumno.getSelectedIndex()).getIdClase()) {
                        //  System.out.println(questions.get(i).getDescripcion());
                        preguntasExamen.add(questions.get(i).getDescripcion());
                        respuestasExamen.add(questions.get(i).isTipo());

                    }

                }
                // System.out.println(controlExamen + "este es el control examen");

                ventana_examen.setText(preguntasExamen.get(cp));
                //System.out.println(respuestasExamen.get(cp) + "que es");
                acumP += "Pregunta: " + (cp + 1) + " de " + controlExamen;
                info.setText(acumP);

//        if (!v.isSelected() && !f.isSelected()) {
//            puntaje += 0;
//        } else {
//            if (v.isSelected()) {
//                verdadero = true;
//                if (verdadero.equals(respuestasExamen.get(cp))) {
//                    puntaje += 5;
//                }
//            } else {
//                falso = false;
//                // System.out.println(falso + " jeje " + respuestasExamen.get(cp - 1));
//                if (falso.equals(respuestasExamen.get(cp))) {
//                    //  System.out.println(falso + " jeje " + respuestasExamen.get(cp - 1));
//                    puntaje += 5;
//
//                }
//            }
//        }
                //System.out.println(buttonGroup2.getSelection()+"que es");
                aux = cp;
                // System.out.println(respuestasExamen.get(0) + " este es antes " + cp);

                for (int j = 0; j < preguntasExamen.size(); j++) {
                    // System.out.println(preguntasExamen.get(j));
                }
                for (int i = 0; i < questions.size(); i++) {
                    if (questions.get(i).getIdClase() == clasess.get(cb_examenAlumno.getSelectedIndex()).getIdClase()) {
                        // System.out.println(i + "indice" + questions.get(i).isTipo() + " este del arraylist");
                    }
                }

                for (int i = 0; i < respuestasExamen.size(); i++) {
                    // System.out.println(i + " indice " + respuestasExamen.get(i) + " este de respuestas");
                }
                Exameness.pack();
                Exameness.setModal(true);
                Exameness.setLocationRelativeTo(null);
                Exameness.setVisible(true);

            }
        }

    }//GEN-LAST:event_btn_comenzarMouseClicked

    private void btn_verExamenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verExamenesMouseClicked
        String acum = "", acum2 = "", acum3 = "";
        boolean vacio = false;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getIdAlumno() == alumnoos.get(flag).getIdAlumno()) {
                acum += notes.get(i).getIdExamen() + "\n";
                acum2 += notes.get(i).getNota() + "\n";
                acum3 += notes.get(i).getPuntosPosibles() + "\n";

                vacio = false;

            } else {
                vacio = true;
            }
        }
        if (vacio == true) {
            JOptionPane.showMessageDialog(null, "No tienes ninguna calificacion hasta el momento");
        } else {
            area4.setText(acum);
            area3.setText(acum2);
            area5.setText(acum3);

            Estudiantes.setVisible(false);
            VerNotas.pack();
            VerNotas.setModal(true);
            VerNotas.setLocationRelativeTo(null);
            VerNotas.setVisible(true);
        }

    }//GEN-LAST:event_btn_verExamenesMouseClicked

    private void btn_regresar_verMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_regresar_verMouseClicked
        VerNotas.dispose();
        area3.setText("");
        area4.setText("");
        area5.setText("");
        Estudiantes.pack();
        Estudiantes.setModal(true);
        Estudiantes.setLocationRelativeTo(null);
        Estudiantes.setVisible(true);

    }//GEN-LAST:event_btn_regresar_verMouseClicked

    private void btnregresar_mostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregresar_mostrarMouseClicked
        datosAdmin.dispose();
        Admin.pack();
        Admin.setModal(true);
        Admin.setLocationRelativeTo(null);
        Admin.setVisible(true);
    }//GEN-LAST:event_btnregresar_mostrarMouseClicked

    private void btn_mostrardatosAdmiinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mostrardatosAdmiinMouseClicked
        Admin.dispose();
        datosAdmin.pack();
        datosAdmin.setModal(true);
        datosAdmin.setLocationRelativeTo(null);
        datosAdmin.setVisible(true);
    }//GEN-LAST:event_btn_mostrardatosAdmiinMouseClicked

    private void cb_mostrarClasesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_mostrarClasesItemStateChanged
//        Jlist_Preguntas.setModel(new DefaultListModel<>());
//        DefaultListModel<String> model = (DefaultListModel<String>) Jlist_Preguntas.getModel();
        String aPreg = "", aExa = "";
        for (int i = 0; i < questions.size(); i++) {
            if (clasess.get(cb_mostrarClases.getSelectedIndex()).getIdClase() == questions.get(i).getIdClase()) {
                aPreg += "•" + " Id Pregunta: " + " " + questions.get(i).getIdQ() + " /Titulo: " + questions.get(i).getTitulo() + "\n";
                aPreg += "\n";
            }
        }
        //model.addElement(aPreg+"\n");
        area7.setText(aPreg);
        //  Jlist_Preguntas.setModel(model);
        for (int i = 0; i < examenes.size(); i++) {
            if (clasess.get(cb_mostrarClases.getSelectedIndex()).getIdClase() == examenes.get(i).getIdClase()) {
                aExa += "•" + " Id Examen: " + " " + examenes.get(i).getIdExamen() + " /N° Preguntas: " + examenes.get(i).getCantPreguntas() + "\n";
                aExa += "\n";
            }
        }
        area8.setText(aExa);

    }//GEN-LAST:event_cb_mostrarClasesItemStateChanged

    private void btn_finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_finalizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_finalizarActionPerformed

    private void btn_mostrardatosAdmiinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostrardatosAdmiinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mostrardatosAdmiinActionPerformed

    private void cb_examenAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_examenAlumnoItemStateChanged
//        for (int i = 0; i < clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().size(); i++) {
//            for (int j = 0; j < clasess.get(i).size(); j++) {
//
//                String numCadena = String.valueOf(clasess.get(i).getTests().get(j).getIdExamen());
//                cb_escogerExamen.addItem(numCadena);
//            }
//        }
////        for (int i = 0; i < clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().size(); i++) {
////            JOptionPane.showMessageDialog(null, clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(i).getIdExamen());
////        
        j_examenes.setModel(new DefaultListModel<>());
        //cb_examenAlumno.setModel(new DefaultComboBoxModel<>());

        DefaultListModel<String> modelo = (DefaultListModel<String>) j_examenes.getModel();
        String acum = "";

        for (int i = 0; i < clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().size(); i++) {
            //for (int j = 0; j < clasess.get(i).getTests().size() - 1; j++) {
            // JOptionPane.showMessageDialog(null, clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().size());
            //System.out.println(clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(i).getIdExamen());
            //String numCadena = String.valueOf(clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(i).getIdExamen());
            //cb_escogerExamen.addItem(numCadena);
            acum = clasess.get(cb_examenAlumno.getSelectedIndex()).getTests().get(i).getIdExamen() + "\n";
            //}
            modelo.addElement(acum + "\n");
        }

        j_examenes.setModel(modelo);

    }//GEN-LAST:event_cb_examenAlumnoItemStateChanged

    public static String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Admin;
    private javax.swing.JDialog Estudiantes;
    private javax.swing.JDialog Exameness;
    private javax.swing.JDialog VerNotas;
    private javax.swing.JTextArea area3;
    private javax.swing.JTextArea area4;
    private javax.swing.JTextArea area5;
    private javax.swing.JTextArea area7;
    private javax.swing.JTextArea area8;
    private javax.swing.JButton btn_avanzar;
    private javax.swing.JButton btn_cerrarSesion;
    private javax.swing.JButton btn_comenzar;
    private javax.swing.JButton btn_crearExamen;
    private javax.swing.JButton btn_finalizar;
    private javax.swing.JButton btn_guardarClase;
    private javax.swing.JButton btn_ingresar;
    private javax.swing.JButton btn_mostrardatosAdmiin;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JButton btn_regresar1;
    private javax.swing.JButton btn_regresar_ver;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_singoutA;
    private javax.swing.JButton btn_verExamenes;
    private javax.swing.JButton btnregresar_mostrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cb_clases;
    private javax.swing.JComboBox<String> cb_examen;
    private javax.swing.JComboBox<String> cb_examenAlumno;
    private javax.swing.JComboBox<String> cb_mostrarClases;
    private javax.swing.JTabbedPane crear;
    private javax.swing.JDialog datosAdmin;
    private javax.swing.JRadioButton f;
    private javax.swing.JLabel f_ex;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel fondo_Admin;
    private javax.swing.JLabel fondo_ver;
    private javax.swing.JLabel info;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private com.toedter.calendar.JDateChooser jSeleccionFecha;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField j_cantP;
    private javax.swing.JTextField j_descripcion;
    private javax.swing.JList<String> j_examenes;
    private javax.swing.JTextField j_idAlumno;
    private javax.swing.JTextField j_idClase;
    private javax.swing.JTextField j_idExamen;
    private javax.swing.JTextField j_idPregunta;
    private javax.swing.JTextField j_login;
    private javax.swing.JTextField j_nombreA;
    private javax.swing.JTextField j_nombreClase;
    private javax.swing.JTextField j_pass;
    private javax.swing.JTextField j_titulo;
    private javax.swing.JLabel jl;
    private javax.swing.JPasswordField passLogin;
    private javax.swing.JRadioButton rb_fal;
    private javax.swing.JRadioButton rb_verd;
    private javax.swing.JDialog registrar;
    private javax.swing.JTextField userLogin;
    private javax.swing.JRadioButton v;
    private javax.swing.JTextArea ventana_examen;
    // End of variables declaration//GEN-END:variables
}
