package elResto;


public class GrafoNoDirigido<T> extends GrafoDirigido<T>{

	@Override
    public void agregarArco(int origen, int destino, T etiqueta){
        super.agregarArco(origen, destino, etiqueta);
        super.agregarArco(destino, origen, etiqueta);
    }

	@Override
    public void borrarArco(int origen, int destino){
        super.borrarArco(origen, destino);
        super.borrarArco(destino, origen);
    }

	@Override
	public int cantidadArcos() {
		return super.cantidadArcos() / 2;
	}

}
