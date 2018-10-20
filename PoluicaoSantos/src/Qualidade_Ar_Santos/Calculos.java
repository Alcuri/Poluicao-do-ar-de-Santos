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
    		Integer quantidade = frequenciaNumeros.get(numero); //Começa a verificar a quantidade de vezes que um mesmo elemento aparece no programa
    		
    			if (quantidade == null) //Isso faz com que elementos que apareçam apenas uma vez, sejam atribuidos com a quantidade 1
    			{
    				quantidade = 1;
    			}
    				else //Elementos que aparecem mais de uma vez são atribuidos pelo valor de sua respectiva quantidade
    				{
    					quantidade += 1;
    				}
    					frequenciaNumeros.put(numero, quantidade); //Substitui o valor referente a numero por quantidade, assim no mapa ficam os valores de suas quantidades
    			
    					if (maiorFrequencia < quantidade)
    					{ 
    						maiorFrequencia = quantidade; //Seleciona quais as maiores frequencias, ou seja, quais números aparecem mais vezes
    					}
    	}
    	
    	System.out.print("\nModa da amostra: ");
    	for (Float numerosChave : frequenciaNumeros.keySet()) //Novo foreach para mostrar o valor da moda
    	{
    		int quantidade = frequenciaNumeros.get(numerosChave);
    		
    			if (maiorFrequencia == 1) //Isso representa que todos os números aparecem 1 vez, caracterizando uma amostra amodal
    				{ 
    				maiorFrequencia = 0 ;
    				System.out.print("A amostra é amodal!"); 
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
		
		if (vetor.length % 2 != 0) //Verifica se o a quantidade de elementos da amostra é ímpar
		{
			meio = (esq+dir) / 2; //Suponha que a amostra tenha 3 elementos. 0, 1, 2 são suas posições. esq = 0. dir = 3-1. (0+2)/2 = 1. Posição 1 é a mediana
			resultadoMediana = vetor[meio];
		}
			
		else //Para quando o número de elementos for par, então é necessário tirar a média dos dois elementos centrais da amostra
		{
			meio = (esq+dir) / 2;
			meio2 = meio + 1; //O primeiro elemento central é calculado igual anteriormente e o segundo ele sempre será o na posição seguindo, por tanto meio + 1
			resultadoMediana = (vetor[meio] +vetor[meio2]) /2 ; //Tira a média desses dois elementos e atribui o resultado à variável de mediana
		}
		
		System.out.println("\nO resultado da Mediana é de: " +resultadoMediana);
	}

	public float calcularMedia(float[] vetor) 
	{
		float resultadoMedia = 0.0F;
		   
		for (Float valor : vetor) //foreach para passar por todos os elementos do vetor
    		{
    			resultadoMedia += valor; //Soma deles
    		}
    	
    	float media = resultadoMedia / vetor.length; //Cálculo da média, soma/número de elementos do vetor
    	
    	return media; //Return na média para poder ser utilizado para o cálculo de variância e para montar o gráfico da distribuição normal
    }

	
	public float calcularVariancia(float[] vetor)
	{
		DecimalFormat df = new DecimalFormat("0.##"); //Determina quantas casas decimais aparecerão
		float media = calcularMedia(vetor);
	   		System.out.println("O resultado de média é: " +media); //Recebe o valor de média e já mostra, porque como utilizamos esse método duas vezes, então
	   															   //deixamos sua mensagem nesse método que é executado apenas uma vez
		
    	float somaNumeros = 0;
		
		for (int i = 0; i < vetor.length; i++) //Variância se calcula por: (Soma de (cada elemento - média)) / (número de elementos - 1)
		{
			somaNumeros += ((vetor[i] - media) * (vetor[i] - media)); //Loop for faz a parte da soma
		}
		
		float resultadoV = somaNumeros / vetor.length - 1; //Faz a parte da divisão
		
		if (resultadoV > 0) //Caso variância seja igual à 0, isso significa que não há variância, então não há o que mostrar
		{
		System.out.println("A variância de dados é de: " +df.format(resultadoV));
		}
		else System.out.println("Não há variância de dados!");
		
		return resultadoV; //Return no resultado para ser utilizado no desvio padrão e para montar o gráfico da distribuição normal
	}
	
	public double calcularDesvioPadrao(float [] vetor)
	{
		DecimalFormat df = new DecimalFormat("0.##");
		double variancia = this.calcularVariancia(vetor);
		double desvioPadrao = 0;
		
		if (variancia > 0) //Desvio padrão se calcula unicamente tirando a raíz quadrada de variância 
		{				   //Caso variância seja igual à 0, também não há desvio padrão. Dessa maneira, não há também distribuição normal (ocorre em SO2)
			desvioPadrao = Math.sqrt(variancia); //Feito o cálculo da raíz
		
			System.out.println("O desvio padrão é de: " +df.format(desvioPadrao));
		}
		else System.out.println("Variância é igual a 0, por tanto não há desvio padrão!");
		float desvioP = (float) desvioPadrao; //Convertido seu valor para float para ser utilizado na criação do gráfico de linha
		
		return desvioP; //Return para montar o gráfico da distribuição normal
	}
}