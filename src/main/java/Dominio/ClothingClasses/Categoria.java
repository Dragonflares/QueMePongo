package Dominio.ClothingClasses;

public enum Categoria {
	PARTE_SUPERIOR,
	CALZADO,
	PARTE_INFERIOR,
	ACCESORIOS;
	
    public String getCategoria() {
        return this.name();
    }
}
