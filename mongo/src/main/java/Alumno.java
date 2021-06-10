
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Alumno {

    @BsonProperty(value = "_id")
    ObjectId id;
    int idAlumno;
    String nombreAlumno, username, password;

    public Alumno() {
    }
   
    public Alumno(int idAlumno, String nombreAlumno, String username, String password) {
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.username = username;
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", idAlumno=" + idAlumno + ", nombreAlumno=" + nombreAlumno + ", username=" + username + ", password=" + password + '}';
    }
}