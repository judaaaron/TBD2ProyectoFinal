
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author Judá Aarón
 */
public class Preguntas {
    @BsonProperty(value = "_id")
    ObjectId id;
    int IDPregunta;
    String Titulo;
    String descripcion;
    int idClase;
    boolean tipo;

    public Preguntas() {
    }

    public Preguntas(int IDPregunta, String Titulo, String descripcion, int idClase) {
        this.IDPregunta = IDPregunta;
        this.Titulo = Titulo;
        this.descripcion = descripcion;
        this.idClase = idClase;
    }

    public Preguntas(int IDPregunta, boolean tipo) {
        this.IDPregunta = IDPregunta;
        this.tipo = tipo;
    }
    
    

    public Preguntas(int IDPregunta, String Titulo, String descripcion, int idClase, boolean tipo) {
        this.IDPregunta = IDPregunta;
        this.Titulo = Titulo;
        this.descripcion = descripcion;
        this.idClase = idClase;
        this.tipo = tipo;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
    
    public int getIdQ() {
        return IDPregunta;
    }

    public void setIdQ(int IDPregunta) {
        this.IDPregunta = IDPregunta;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Preguntas{" + "id=" + id + ", IDPregunta=" + IDPregunta + ", Titulo=" + Titulo + ", descripcion=" + descripcion + ", idClase=" + idClase + ", tipo=" + tipo + '}';
    }

    
    
    

   
    
    
    
    
    
}
