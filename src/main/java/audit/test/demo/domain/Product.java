package audit.test.demo.domain;

import jakarta.persistence.*;
import org.javers.core.metamodel.annotation.PropertyName;
import org.javers.core.metamodel.annotation.TypeName;

@Entity
@TypeName("PRODUCTO")
//Cambia el nombre a la entidad, deberiamos usar el nombre de la tabla, por defecto Javers lo armo con el package + class: audit.test.demo.domain.Product
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @PropertyName("NOMBRE_PRODUCTO") //Cambia el nombre a la property, deberiamos usar el nombre de la columna
    private String name;
    @PropertyName("PRECIO_PRODUCTO")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setNamePrefix(String prefix) {
        this.name = prefix + this.name;
    }
}
