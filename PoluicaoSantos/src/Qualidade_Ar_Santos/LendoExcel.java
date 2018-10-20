package Qualidade_Ar_Santos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LendoExcel 
{
	public float[] captura()
	{
		double soma = 0;
		float[] vetExcel = null; //Cria um vetor
		FileInputStream fisPlanilha = null; //Cria um objeto da classe FileInputStream
	
	    try 
	    {
	        File file = new File(Menu.arquivo); //Busca o arquivo (que está salvo na pasta do projeto, afim de facilitar o trabalho)
	        fisPlanilha = new FileInputStream(file); //Atribui valor ao objeto de inputstream
	
	        XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);	//Cria um workbook para verificar se o arquivo é mesmo um excel
	
	        XSSFSheet sheet = workbook.getSheetAt(Menu.planilha); //Seleciona a planilha que será lida. Note que a argumentação é de "Menu.planilha", a variável classe
	        													  //que foi passada de Menu para cá, ela carrega a planilha referente ao gás escolhido pelo usuário
	
	        Iterator<Row> rowIterator = sheet.iterator(); //Cria um iterator para as linhas
	        
	        int cont = sheet.getPhysicalNumberOfRows(); //Seleciona o número que será o tamanho do vetor
	        
	        vetExcel = new float[cont]; //Denomina esse tamanho
	        
	        int aux = 0; //Uma variável auxiliar que servirá para atribuição de valores ao vetor em si
	        	
	        while (rowIterator.hasNext()) //Loop responsável pela leitura e por salvar os valores numéricos das células no vetor
	        {
	        	Row row = rowIterator.next();
	
	            Iterator<Cell> cellIterator = row.iterator();
	
	            while (cellIterator.hasNext()) 
	            {
	            	Cell cell = cellIterator.next();
	                
	                switch (cell.getCellType()) //Entende qual o tipo do elemento da célula analisada e caso seja numérico, salva no vetor
	                {
	                    case Cell.CELL_TYPE_NUMERIC:                      
	                        soma = soma + cell.getNumericCellValue();
	                        vetExcel[aux] = (float) cell.getNumericCellValue(); //Lhe é dado esse valor e salvo
	                        aux ++;
	                        break;    
	                }
	            }
	        }
	    }        
	    
	    //Tratamento de erros como não achar o arquivo, número incorreto de planilha e demais possíveis erros. Finally fecha a planilha (há tratamento caso dê errado também)
	    catch (FileNotFoundException error1) 
	    {
	        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, error1);
	        System.out.println("Erro na importação do local do arquivo. Por favor verifique se o caminho está correto!\nErro: " +error1);
	        System.exit(0);
	    } 
	    catch (IOException error2) 
	    {
	        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, error2);
	        System.out.println("Erro! " +error2);
	        System.exit(0);
	    }
		catch (IllegalArgumentException error3)
		{
			System.out.println("Erro na importação das planilhas do arquivo. Por favor verifique se sua formatação está correto!\nErro: " +error3);
			System.exit(0);
		}
	    finally 
	    {
	    	try 
	    	{
	    		fisPlanilha.close();
	        } 
	        catch (IOException error3) 
	    	{
	        	Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, error3);
	        	System.out.println("Erro ao fechar o programa!" + error3);
	        	System.exit(0);
	        }
	    }
	    return vetExcel; //Retorna o valor desse vetor para poder ser utilizado na classe Menu
	}
}