import java.awt.Color;
import java.awt.FileDialog;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class inicio {
	private  Fila fila = new Fila();
    private  Stack<String> pilha = new Stack<String>();
    private  String    expressao = "";
	private  JPanel    painel;
	static   JTextArea area;
	private  JButton   BOTAOPEN, BOTAOCLOSE;
	private  JFrame    frame;
	private  JLabel    label;
	private  JLabel    label1;
	private  TextArea  texto;
	{
	///Construção do meu Painel//
	//                        //
	//						 //
	//////////////////////////
		
	painel     = new JPanel();
	BOTAOPEN   = new JButton("Abrir");
	BOTAOCLOSE = new JButton("Fechar");
	label      = new JLabel("Arquivo não Aberto");
	frame      = new JFrame("Abrir Arquivo");
	area       = new JTextArea();
	label1 	   = new JLabel();
	
	painel.add(label1);
	painel.add(area);
	painel.add(BOTAOPEN);
	painel.add(BOTAOCLOSE);
	painel.add(label);
	painel.setBackground(Color.LIGHT_GRAY);
	frame.add(painel);
	frame.setVisible(true);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//Tratamento dos eventos//
	//Evento abrir arquivo/
	BOTAOPEN.addActionListener(
	new ActionListener(){
		public void actionPerformed(ActionEvent abrir){
			try{
				
				FileDialog open = new FileDialog(frame, "Abrir...", FileDialog.LOAD);
				open.show();
				label.setText(open.getFile()+ " Aberto");
				frame.pack();
				File arquivo = new File(open.getDirectory()+open.getFile());
				FileReader reader = new FileReader(arquivo);
				BufferedReader bReader = new BufferedReader(reader);
				StreamTokenizer st = new StreamTokenizer(bReader);
				
				st.resetSyntax();
				st.whitespaceChars(0,' ');
				st.wordChars(' '+1,255);
				st.slashSlashComments(true);
				st.slashSlashComments(true);
				st.nextToken();
				
				//Lendo arquivo//
				while(st.ttype != StreamTokenizer.TT_EOF){
					String palavra = st.sval;
					String palavraaux;
					st.nextToken();
					//Tratamento de Expressoes Infixas//
					if(palavra.equals("I"))
					{
						while(!pilha.isEmpty()){
							pilha.pop();	
							}
						while(!fila.isEmpty()){
							fila.dequeue();	
							}
						int linhaAtual = st.lineno();
						int linha = linhaAtual;
						palavraaux = st.sval;
						while(linha == linhaAtual ){
							st.pushBack();
							while(!(st.sval.equals("P")) && !(st.sval.equals("I")) && (st.nextToken() != st.TT_EOF) ) 
							{
								if(!(st.sval.equals("+")) && !(st.sval.equals("-")) && !(st.sval.equals("*")) && 
								   !(st.sval.equals("/" ))&& !(st.sval .equals("(")) && !(st.sval .equals (")")) &&
								   !(st.sval.equals("pow")) && !(st.sval.equals("!")))
								{
									fila.enqueue(st.sval);
									}
								else{
									if(!(st.sval.equals("(")) && !st.sval.equals(")") ){
										pilha.add(st.sval);
										}
									}
								}
							linha++;
							}
						System.out.print("P");
						while(!fila.isEmpty()){
							System.out.print(" "+fila.dequeue());
							}
						while(!pilha.isEmpty()){
							System.out.print(" "+pilha.pop());	
							}
						System.out.println("");
						}
					
					if(palavra.equals("P"))
					{
						while(!pilha.isEmpty()){
							pilha.pop();	
							}
						palavraaux = st.sval;
						st.pushBack();
						while(!(st.sval.equals("P")) && !(st.sval.equals("I")) && (st.nextToken() != st.TT_EOF)) 
						{
							String aux = expressao;
							expressao = aux +" " +st.sval;
							}
						System.out.println("I "  +posfixa_infixa());		
						}
					System.out.print("\n");
					}
				}
			catch( IOException ioex ){                 
				ioex.printStackTrace();   
				}
			}

private String posfixa_infixa() {
	String Aux= "";
	String operando1 , operando2;
			
	StringTokenizer string = new StringTokenizer(expressao);
			
	while(string.hasMoreTokens())
	{
		String elemento = string.nextToken();
		if(elemento.equals("+") || elemento.equals("-") ||
		   elemento.equals("*") || elemento.equals("/") ||
		   elemento.equals("!")){
			if(elemento.equals("!")){
				operando2 = pilha.pop();
				Aux = "("+ operando2 + elemento+")";  
				pilha.push(Aux);
				}
			else{
				operando2 = pilha.pop();
				operando1 = pilha.pop();
				Aux = "("+ operando1 + ""+ elemento + operando2+")";  
				pilha.push(Aux);
				}
			}
		else
		{
			if(elemento != " "){
				String express = String.valueOf(elemento);
				pilha.push(express);
				}
			}
		}
	expressao = " ";
	return Aux;
	}
}
);
	
	//Evento fechar arquivo//
	BOTAOCLOSE.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent fechar){
				frame.dispose();
				}
			}
			
			);
}

public static void main(String args[]){
	inicio ini = new inicio();
	}
}
