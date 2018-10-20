package Qualidade_Ar_Santos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Calculos 
{
	public void calcularModa(float[] vetor) 
	{
		Map<Float, Integer> frequenciaNumeros = new HashMap<>(); //Cria um mapa para receber os elementos do vetor (extraido em LendoExcel e passado por Menu)
    	int maiorFrequencia = 0;
    	
    	for (Float numero : vetor) //foreach para fazer a leitura dos elementos
    	{
    		Integer quantidade = frequenciaNumeros.get(numero); //Come�a a verificar a quantidade de vezes que um mesmo elemento aparece no programa
    		
    			if (quantidade == null) //Isso faz com que elementos que apare�am apenas uma vez, sejam atribuidos com a quantidade 1
    			{
    				quantidade = 1;
    			}
    				else //Elementos que aparecem mais de uma vez s�o atribuidos pelo valor de sua respectiva quantidade
    				{
    					quantidade += 1;
    				}
    					frequenciaNumeros.put(numero, quantidade); //Substitui o valor referente a numero por quantidade, assim no mapa ficam os valores de suas quantidades
    			
    					if (maiorFrequencia < quantidade)
    					{ 
    						maiorFrequencia = quantidade; //Seleciona quais as maiores frequencias, ou seja, quais n�meros aparecem mais vezes
    					}
    	}
    	
    	System.out.print("\nModa da amostra: ");
    	for (Float numerosChave : frequenciaNumeros.keySet()) //Novo foreach para mostrar o valor da moda
    	{
    		int quantidade = frequenciaNumeros.get(numerosChave);
    		
    			if (maiorFrequencia == 1) //Isso representa que todos os n�meros aparecem 1 vez, caracterizando uma amostra amodal
    				{ 
    				maiorFrequencia = 0 ;
    				System.out.print("A amostra � amodal!"); 
    				}
    		
    			else
    				{ 
    				if (maiorFrequencia == quantidade) //Seleciona apenas elementos que se repitam mais de uma vez E QUE se repitam mais vezes na amostra
    				{ 
    				System.out.print(numerosChave+ " "); 
    				} 
    		}
    	}
	}

	public void calcularMediana(float[] vetor) 
	{
		float resultadoMediana = 0;
		int esq= 0;
		int dir= vetor.length-1;
		int meio;
		int meio2;
		
		if (vetor.length % 2 != 0) //Verifica se o a quantidade de elementos da amostra � �mpar
		{
			meio = (esq+dir) / 2; //Suponha que a amostra tenha 3 elementos. 0, 1, 2 s�o suas posi��es. esq = 0. dir = 3-1. (0+2)/2 = 1. Posi��o 1 � a mediana
			resultadoMediana = vetor[meio];
		}
			
		else //Para quando o n�mero de elementos for par, ent�o � necess�rio tirar a m�dia dos dois elementos centrais da amostra
		{
			meio = (esq+dir) / 2;
			meio2 = meio + 1; //O primeiro elemento central � calculado igual anteriormente e o segundo ele sempre ser� o na posi��o seguindo, por tanto meio + 1
			resultadoMediana = (vetor[meio] +vetor[meio2]) /2 ; //Tira a m�dia desses dois elementos e atribui o resultado � vari�vel de mediana
		}
		
		System.out.println("\nO resultado da Mediana � de: " +resultadoMediana);
	}

	public float calcularMedia(float[] vetor) 
	{
		float resultadoMedia = 0.0F;
		   
		for (Float valor : vetor) //foreach para passar por todos os elementos do vetor
    		{
    			resultadoMedia += valor; //Soma deles
    		}
    	
    	float media = resultadoMedia / vetor.length; //C�lculo da m�dia, soma/n�mero de elementos do vetor
    	
    	return media; //Return na m�dia para poder ser utilizado para o c�lculo de vari�ncia e para montar o gr�fico da distribui��o normal
    }

	
	public float calcularVariancia(float[] vetor)
	{
		DecimalFormat df = new DecimalFormat("0.##"); //Determina quantas casas decimais aparecer�o
		float media = calcularMedia(vetor);
	   		System.out.println("O resultado de m�dia �: " +media); //Recebe o valor de m�dia e j� mostra, porque como utilizamos esse m�todo duas vezes, ent�o
	   															   //deixamos sua mensagem nesse m�todo que � executado apenas uma vez
		
    	float somaNumeros = 0;
		
		for (int i = 0; i < vetor.length; i++) //Vari�ncia se calcula por: (Soma de (cada elemento - m�dia)) / (n�mero de elementos - 1)
		{
			somaNumeros += ((vetor[i] - media) * (vetor[i] - media)); //Loop for faz a parte da soma
		}
		
		float resultadoV = somaNumeros / vetor.length - 1; //Faz a parte da divis�o
		
		if (resultadoV > 0) //Caso vari�ncia seja igual � 0, isso significa que n�o h� vari�ncia, ent�o n�o h� o que mostrar
		{
		System.out.println("A vari�ncia de dados � de: " +df.format(resultadoV));
		}
		else System.out.println("N�o h� vari�ncia de dados!");
		
		return resultadoV; //Return no resultado para ser utilizado no desvio padr�o e para montar o gr�fico da distribui��o normal
	}
	
	public double calcularDesvioPadrao(float [] vetor)
	{
		DecimalFormat df = new DecimalFormat("0.##");
		double variancia = this.calcularVariancia(vetor);
		double desvioPadrao = 0;
		
		if (variancia > 0) //Desvio padr�o se calcula unicamente tirando a ra�z quadrada de vari�ncia 
		{				   //Caso vari�ncia seja igual � 0, tamb�m n�o h� desvio padr�o. Dessa maneira, n�o h� tamb�m distribui��o normal (ocorre em SO2)
			desvioPadrao = Math.sqrt(variancia); //Feito o c�lculo da ra�z
		
			System.out.println("O desvio padr�o � de: " +df.format(desvioPadrao));
		}
		else System.out.println("Vari�ncia � igual a 0, por tanto n�o h� desvio padr�o!");
		float desvioP = (float) desvioPadrao; //Convertido seu valor para float para ser utilizado na cria��o do gr�fico de linha
		
		return desvioP; //Return para montar o gr�fico da distribui��o normal
	}
}