
public class Fila {
	
	private int numelementos;
	private int limite = 5;
	private int inicio;
	private String[] fila;
	
	
	public Fila(){
		numelementos = 0;
		inicio = 0;
		fila = new String[limite];
	}
	
	public void enqueue(String elemento){
		if(isfull()){
			 new RuntimeException("Fila Cheia");
		}
		
		fila[(inicio+numelementos)%limite] = elemento;
		numelementos++;
	}
	

	public String dequeue(){
		if(isEmpty()){
			 new RuntimeException("Fila Vazia");
		}
		String primeiroelemento = fila[inicio];
		inicio=(inicio+1)%limite;
		numelementos--;
		return primeiroelemento;
	}
	
	public boolean isEmpty() {
		if(numelementos == 0){
		return true;
		}
		else{
		return false;
		}
	}
	
	public boolean isfull() {
		if(numelementos == limite){
		 grow();
		}
		return false;
	}

	public void grow(){
		limite = limite*2;
		int aux = numelementos;
		String[] filaaux = new String[limite];
		for( int i = 0; i < aux ; i++ ){
			filaaux[i]=this.dequeue();
		}
		inicio = 0;
		numelementos = aux;
		fila=filaaux;
	}

}
