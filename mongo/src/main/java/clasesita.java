
import java.util.ArrayList;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author Judá Aarón
 */
public class clasesita {

    @BsonProperty(value = "_id")
    ObjectId id;
    int idClase;
    String nombreClase;
    List<Examen> tests = new ArrayList<>();

    public clasesita(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public clasesita(int idClase, String nombreClase) {
        this.idClase = idClase;
        this.nombreClase = nombreClase;
    }

    public clasesita() {
    }

    public List<Examen> getTests() {
        return tests;
    }

    public void setTests(List<Examen> tests) {
        this.tests = tests;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public void addExamen(Examen exa) {
        tests.add(exa);
    }

    @Override
    public String toString() {
        return "clasesita{" + "id=" + id + ", idClase=" + idClase + ", nombreClase=" + nombreClase + ", tests=" + tests + '}';
    }

}
