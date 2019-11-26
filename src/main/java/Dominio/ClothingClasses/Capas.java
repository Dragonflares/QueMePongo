
package Dominio.ClothingClasses;

import javax.persistence.Entity;

@Entity
public enum Capas {
	CAPA1,
	CAPA2,
	CAPA3,
	CAPA4,
	CAPA5,
	CAPA6;
	
    public String getCapas() {
        return this.name();
    }
}
