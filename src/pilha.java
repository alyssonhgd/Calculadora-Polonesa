
public class pilha {
private int  topo ;
private String[] pilha;
	
	public pilha(){
	topo = 0;
	pilha = new String[1000];
	}
	
	public boolean isEmpty(){
		return topo == 0;
	}
	
	public boolean isFull(){
		return (topo == pilha.length);
	}
	public void push(String str){
		pilha[topo++] = str;
	}
	public String pop(){
	String retorna = pilha[topo];
	topo--;
	return retorna;	
	}
}
