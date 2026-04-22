package models.grafo;

public class Arista {
	

			private int origen;
			private int destino;
			private double peso;
			
		public Arista(int origen, int destino, double peso) {
			this.origen = origen;
			this.destino = destino;
			this.peso = peso;;
		}

		public int getOrigen() {
			return origen;
		}

		public void setOrigen(int origen) {
			this.origen = origen;
		}

		public int getDestino() {
			return destino;
		}

		public void setDestino(int destino) {
			this.destino = destino;
		}

		public double getPeso() {
			return peso;
		}

		public void setPeso(double peso) {
			this.peso = peso;
		}
		
		

}
