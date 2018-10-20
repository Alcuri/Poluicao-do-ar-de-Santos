package Qualidade_Ar_Santos;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Menu 
{
	//Declarando as vari�veis de classe que ser�o respons�veis pelo uso de uma �nica classe para cria��o de diferentes gr�ficos
	static int planilha = 0; //Planilha que ser� usada
	static String titulo = ""; //T�tulo em cada gr�fico
	static String legenda = ""; //Legenda em cada gr�fico
	static String valoresNome = "Valores (em (�g/m�))"; //Nome dos valores no eixo x
	static float mediaGrafico = 0; //M�dia a ser passada para o gr�fico de linha
	static double desvioPadraoGrafico = 0; //Desvio padr�o a ser passado para o gr�fico de linha
	static int pla = 6; //Quantidade total de planilha no arquivo (come�ando do 0)
	static String arquivo = "Dados das medi��es de Santos.xlsx"; //Arquivo que ser� lido

	public static void main (String []args) throws IOException
	{
		Scanner leTeclado = new Scanner(System.in);
		LendoExcel dadosExcel = new LendoExcel(); //Recebe o valor em vetor que ser� utilizado para fazer os c�lculos do programa (recebe de LendoExcel e passa para Calculos)
			
		//Menu dos dados de Santos	
		System.out.println("Selecione qual material deseja fazer a compara��o: ");
		System.out.println("1 - Part�culas Inal�veis (MP10)");
		System.out.println("2 - Part�culas inal�veis finas (MP2,5)");
		System.out.println("3 - Di�xido de enxofre (SO2)");
		System.out.println("4 - Oz�nio (O3)");
		System.out.println("5 - Di�xido de nitrog�nio (NO2)");
		String escolha = leTeclado.nextLine();
		
		//Menu dados das Empresas
		/*Menu.arquivo = "Dados Empresas.xlsx";
		Menu.valoresNome = "Valores (em reais (milhares))";
		System.out.print("Selecione uma das op��es do lucro do conglomerado:\n1 - 2010\n2 - 2012\n3 - 2014: ");
		String escolha = leTeclado.nextLine();
		System.out.print("Digite quantas planilhas h� no arquivo .xlsx: ");
		int pla = leTeclado.nextInt();
		Menu.pla = pla - 1;*/
			
		try
		{
			switch (escolha)
			{
				case "1":
				{
					planilha = 0; //Determina qual planilha ser� lida pelas classes respons�veis, cada case est� relacionado a uma planilha
					legenda = "�ndice da Medi��o	\nBO - Boqueir�o	\nEM - Emiss�rio"; //Determina a legenda para o gr�fico de barra do g�s
					new Grafico_Barra(); //Mostra o gr�fico de barra
						
					float[] vetor = dadosExcel.captura(); //Atribui o valor recebido pela LendoExcel ao vetor em quest�o
					Arrays.sort(vetor); //Organiza em ordem crescente o vetor e manda para a classe Calculos
						Calculos mememo = new Calculos();
				    		mememo.calcularModa(vetor);
				    		mememo.calcularMediana(vetor);
				    		mediaGrafico = mememo.calcularMedia(vetor); //Atribui novo valor de m�dia e desvio padr�o para ser montado
				    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor); //o gr�fico de distribui��o normal (gr�fico de linha)
			    	
			    	new GraficoDistribui��oNormal(); //Mostra o gr�fico da normal
			    	break;
					}
				
				case "2":
				{
					planilha = 1;
					legenda = "�ndice da Medi��o	\nEM - Emiss�rio";
					new Grafico_Barra();
					
					float[] vetor = dadosExcel.captura();
					Arrays.sort(vetor);	
						Calculos mememo = new Calculos();
				    		mememo.calcularModa(vetor);
				    		mememo.calcularMediana(vetor);
				    		mediaGrafico = mememo.calcularMedia(vetor);
				    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
					    				
				    new GraficoDistribui��oNormal();
			    	break;
				}
				
				case "3":
				{
					planilha = 2;
					legenda = "�ndice da Medi��o	\nEM - Emiss�rio";
					new Grafico_Barra();
		
					float[] vetor = dadosExcel.captura();
					Arrays.sort(vetor);
						Calculos mememo = new Calculos();
			    			mememo.calcularModa(vetor);
			    			mememo.calcularMediana(vetor);
			    			mediaGrafico = mememo.calcularMedia(vetor);
				    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
					    		
				    new GraficoDistribui��oNormal();
			    	break;
				}
				
				case "4":
				{
					System.out.println("Devido aos padr�es de avali��o da m�dia Estadual e Nacional serem diferentes, por favor escolha qual compara��o deseja ver: ");
					String escolha2 = leTeclado.nextLine();
					
					if (escolha2.equals("Estadual") || (escolha2.equals("estadual")))
					{
						planilha = 4;
						legenda = "�ndice da Medi��o	\nBO - Boqueir�o	\nEM - Emiss�rio";
						new Grafico_Barra();
							
						float[] vetor = dadosExcel.captura();
						Arrays.sort(vetor);	
							Calculos mememo = new Calculos();
					    		mememo.calcularModa(vetor);
					    		mememo.calcularMediana(vetor);
					    		mediaGrafico = mememo.calcularMedia(vetor);
					    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
						    			    		
					    new GraficoDistribui��oNormal();
				    	break;
					}
					else if (escolha2.equals("Nacional") || (escolha2.equals("nacional")))
					{
						planilha = 3;
						legenda = "�ndice da Medi��o	\nBO - Boqueir�o	\nEM - Emiss�rio";
						new Grafico_Barra();
						
						float[] vetor = dadosExcel.captura();
						Arrays.sort(vetor);	
							Calculos mememo = new Calculos();
					    		mememo.calcularModa(vetor);
					    		mememo.calcularMediana(vetor);
					    		mediaGrafico = mememo.calcularMedia(vetor);
					    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
						    		
					    new GraficoDistribui��oNormal();
				    	break;
					}
					else
					{
						System.out.print("Por favor digite apenas 'Estadual' ou 'Nacional'");
						break;
					}
				}
				
				case "5":
				{
					planilha = 5;
					legenda = "�ndice da Medi��o	\nBO - Boqueir�o	\nEM - Emiss�rio";
					new Grafico_Barra();
		
					float[] vetor = dadosExcel.captura();
					Arrays.sort(vetor);	
						Calculos mememo = new Calculos();
				    		mememo.calcularModa(vetor);
				    		mememo.calcularMediana(vetor);
				    		mediaGrafico = mememo.calcularMedia(vetor);
				    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
					    			
				    new GraficoDistribui��oNormal();
			    	break;
				}
				
				default: System.out.println("Por favor selecione uma das op��es acima!"); break; //Trata um poss�vel erro de digita��o do usu�rio
			} //final switch
		} //final try
		
		//Tratamento de erro, no caso o �nico poss�vel � o de imcompatibilidade do n�mero de planilhas do arquivo
		catch (NullPointerException error)
		{
			System.out.println("Erro na importa��o das planilhas do arquivo. Por favor verifique se sua formata��o est� correto!\nErro: " +error);
			System.exit(0);
		}
			
		leTeclado.close();
	} //final main
} //final classe Menu

