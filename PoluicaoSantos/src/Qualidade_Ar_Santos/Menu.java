package Qualidade_Ar_Santos;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Menu 
{
	//Declarando as variáveis de classe que serão responsáveis pelo uso de uma única classe para criação de diferentes gráficos
	static int planilha = 0; //Planilha que será usada
	static String titulo = ""; //Título em cada gráfico
	static String legenda = ""; //Legenda em cada gráfico
	static String valoresNome = "Valores (em (µg/m³))"; //Nome dos valores no eixo x
	static float mediaGrafico = 0; //Média a ser passada para o gráfico de linha
	static double desvioPadraoGrafico = 0; //Desvio padrão a ser passado para o gráfico de linha
	static int pla = 6; //Quantidade total de planilha no arquivo (começando do 0)
	static String arquivo = "Dados das medições de Santos.xlsx"; //Arquivo que será lido

	public static void main (String []args) throws IOException
	{
		Scanner leTeclado = new Scanner(System.in);
		LendoExcel dadosExcel = new LendoExcel(); //Recebe o valor em vetor que será utilizado para fazer os cálculos do programa (recebe de LendoExcel e passa para Calculos)
			
		//Menu dos dados de Santos	
		System.out.println("Selecione qual material deseja fazer a comparação: ");
		System.out.println("1 - Partículas Inaláveis (MP10)");
		System.out.println("2 - Partículas inaláveis finas (MP2,5)");
		System.out.println("3 - Dióxido de enxofre (SO2)");
		System.out.println("4 - Ozônio (O3)");
		System.out.println("5 - Dióxido de nitrogênio (NO2)");
		String escolha = leTeclado.nextLine();
		
		//Menu dados das Empresas
		/*Menu.arquivo = "Dados Empresas.xlsx";
		Menu.valoresNome = "Valores (em reais (milhares))";
		System.out.print("Selecione uma das opções do lucro do conglomerado:\n1 - 2010\n2 - 2012\n3 - 2014: ");
		String escolha = leTeclado.nextLine();
		System.out.print("Digite quantas planilhas há no arquivo .xlsx: ");
		int pla = leTeclado.nextInt();
		Menu.pla = pla - 1;*/
			
		try
		{
			switch (escolha)
			{
				case "1":
				{
					planilha = 0; //Determina qual planilha será lida pelas classes responsáveis, cada case está relacionado a uma planilha
					legenda = "Índice da Medição	\nBO - Boqueirão	\nEM - Emissário"; //Determina a legenda para o gráfico de barra do gás
					new Grafico_Barra(); //Mostra o gráfico de barra
						
					float[] vetor = dadosExcel.captura(); //Atribui o valor recebido pela LendoExcel ao vetor em questão
					Arrays.sort(vetor); //Organiza em ordem crescente o vetor e manda para a classe Calculos
						Calculos mememo = new Calculos();
				    		mememo.calcularModa(vetor);
				    		mememo.calcularMediana(vetor);
				    		mediaGrafico = mememo.calcularMedia(vetor); //Atribui novo valor de média e desvio padrão para ser montado
				    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor); //o gráfico de distribuição normal (gráfico de linha)
			    	
			    	new GraficoDistribuiçãoNormal(); //Mostra o gráfico da normal
			    	break;
					}
				
				case "2":
				{
					planilha = 1;
					legenda = "Índice da Medição	\nEM - Emissário";
					new Grafico_Barra();
					
					float[] vetor = dadosExcel.captura();
					Arrays.sort(vetor);	
						Calculos mememo = new Calculos();
				    		mememo.calcularModa(vetor);
				    		mememo.calcularMediana(vetor);
				    		mediaGrafico = mememo.calcularMedia(vetor);
				    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
					    				
				    new GraficoDistribuiçãoNormal();
			    	break;
				}
				
				case "3":
				{
					planilha = 2;
					legenda = "Índice da Medição	\nEM - Emissário";
					new Grafico_Barra();
		
					float[] vetor = dadosExcel.captura();
					Arrays.sort(vetor);
						Calculos mememo = new Calculos();
			    			mememo.calcularModa(vetor);
			    			mememo.calcularMediana(vetor);
			    			mediaGrafico = mememo.calcularMedia(vetor);
				    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
					    		
				    new GraficoDistribuiçãoNormal();
			    	break;
				}
				
				case "4":
				{
					System.out.println("Devido aos padrões de avalição da média Estadual e Nacional serem diferentes, por favor escolha qual comparação deseja ver: ");
					String escolha2 = leTeclado.nextLine();
					
					if (escolha2.equals("Estadual") || (escolha2.equals("estadual")))
					{
						planilha = 4;
						legenda = "Índice da Medição	\nBO - Boqueirão	\nEM - Emissário";
						new Grafico_Barra();
							
						float[] vetor = dadosExcel.captura();
						Arrays.sort(vetor);	
							Calculos mememo = new Calculos();
					    		mememo.calcularModa(vetor);
					    		mememo.calcularMediana(vetor);
					    		mediaGrafico = mememo.calcularMedia(vetor);
					    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
						    			    		
					    new GraficoDistribuiçãoNormal();
				    	break;
					}
					else if (escolha2.equals("Nacional") || (escolha2.equals("nacional")))
					{
						planilha = 3;
						legenda = "Índice da Medição	\nBO - Boqueirão	\nEM - Emissário";
						new Grafico_Barra();
						
						float[] vetor = dadosExcel.captura();
						Arrays.sort(vetor);	
							Calculos mememo = new Calculos();
					    		mememo.calcularModa(vetor);
					    		mememo.calcularMediana(vetor);
					    		mediaGrafico = mememo.calcularMedia(vetor);
					    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
						    		
					    new GraficoDistribuiçãoNormal();
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
					legenda = "Índice da Medição	\nBO - Boqueirão	\nEM - Emissário";
					new Grafico_Barra();
		
					float[] vetor = dadosExcel.captura();
					Arrays.sort(vetor);	
						Calculos mememo = new Calculos();
				    		mememo.calcularModa(vetor);
				    		mememo.calcularMediana(vetor);
				    		mediaGrafico = mememo.calcularMedia(vetor);
				    		desvioPadraoGrafico = mememo.calcularDesvioPadrao(vetor);
					    			
				    new GraficoDistribuiçãoNormal();
			    	break;
				}
				
				default: System.out.println("Por favor selecione uma das opções acima!"); break; //Trata um possível erro de digitação do usuário
			} //final switch
		} //final try
		
		//Tratamento de erro, no caso o único possível é o de imcompatibilidade do número de planilhas do arquivo
		catch (NullPointerException error)
		{
			System.out.println("Erro na importação das planilhas do arquivo. Por favor verifique se sua formatação está correto!\nErro: " +error);
			System.exit(0);
		}
			
		leTeclado.close();
	} //final main
} //final classe Menu

