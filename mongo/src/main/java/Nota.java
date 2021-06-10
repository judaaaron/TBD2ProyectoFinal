
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author Judá Aarón
 */
public class Nota {

    @BsonProperty(value = "_id")
    ObjectId id;
    int idAlumno, idExamen, nota, puntosPosibles;

    public Nota() {
    }

    public Nota(int idAlumno, int idExamen, int nota, int puntosPosibles) {
        this.idAlumno = idAlumno;
        this.idExamen = idExamen;
        this.nota = nota;
        this.puntosPosibles = puntosPosibles;
    }

    public int getPuntosPosibles() {
        return puntosPosibles;
    }

    public void setPuntosPosibles(int puntosPosibles) {
        this.puntosPosibles = puntosPosibles;
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

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Nota{" + "id=" + id + ", idAlumno=" + idAlumno + ", idExamen=" + idExamen + ", nota=" + nota + ", puntosPosibles=" + puntosPosibles + '}';
    }

}
