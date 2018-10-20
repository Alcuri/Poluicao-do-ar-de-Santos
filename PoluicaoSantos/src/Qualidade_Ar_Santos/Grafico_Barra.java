package Qualidade_Ar_Santos;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

@SuppressWarnings("serial")
public class Grafico_Barra extends JFrame 
{
	public Grafico_Barra() 
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Gr�fico de compara��o de dados");
		setSize(950,700);
		setLocationRelativeTo(null);
		criarGrafico(Menu.planilha);
		setVisible(true);
	}                

	public void criarGrafico(int x)
	{ 
		FileInputStream fisPlanilha = null;
		try
		{
			fisPlanilha = new FileInputStream(new File(Menu.arquivo)); //Leia os dados do gr�fico de barras do arquivo excel
            
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha); //Cria um workbook para verificar se o arquivo � mesmo um excel
           
            XSSFSheet sheet = workbook.getSheetAt(x); //Seleciona qual planilha de dados ser� passada para o gr�fico. x � o valor de "Menu.planilha" passada por no menu
           
            DefaultCategoryDataset barra = new DefaultCategoryDataset(); //Criar conjunto de dados que levar� os dados do gr�fico
            
            /* Temos que carregar os dados do gr�fico de barras agora 
             Comece iterando pela planilha 
             Crie um objeto Iterator */
            Iterator<Row> rowIterator = sheet.iterator(); 
            
            //Fazer um loop pelos dados da planilha e preencher o conjunto de dados do gr�fico de barras
            String caracter="a";
            Number numero=0;
                while(rowIterator.hasNext()) 
                {
                	Row row = rowIterator.next();  //L� linhas do documento do Excel
                	
                	Iterator<Cell> cellIterator = row.cellIterator(); //L� c�lulas em linhas e obter dados do gr�fico
                		
                		while(cellIterator.hasNext()) 
                		{
                			Cell celula = cellIterator.next(); 
                			
                				switch(celula.getCellType()) //Testa o tipo de dado que est� sendo inserido pela tabela
                				{ 
                					case Cell.CELL_TYPE_NUMERIC:
                						numero=celula.getNumericCellValue();
                						break;
                					case Cell.CELL_TYPE_STRING:
                						caracter=celula.getStringCellValue();
                						break;
                				}
                		}          
                	barra.addValue(numero.doubleValue(),Menu.legenda,caracter); //Adiciona os valores colhidos � cada linha do gr�fico, respeitando o tipo
                																//de dado que est� sendo adicionado � sua devida posi��o
                }               
                
                XSSFCell titulo = workbook.getSheetAt(Menu.pla).getRow(x).getCell(0); //Seleciona a planilha de t�tulos, s�o essas duas linhas respons�veis por dar o
                Menu.titulo = titulo.getStringCellValue();					   //t�tulo correto � cada gr�fico de cada g�s
                
                // Cria um objeto gr�fico l�gico com os dados do gr�fico coletados
                JFreeChart grafico=ChartFactory.createBarChart3D(Menu.titulo, "Local e medi��o", Menu.valoresNome,barra,PlotOrientation.HORIZONTAL,true,true,false);  
                
        		//Determina uma cor para as colunas (como s�o um �nico bloco, n�o � possivel colorir diferentemente cada linha)
                CategoryPlot barraItem = grafico.getCategoryPlot();
                switch (Menu.planilha) //Identifica qual planilha est� sendo lida (por tanto qual g�s) e determina uma cor �nica para cada gr�fico de cada g�s
                {
                	case 0:
                		barraItem.getRenderer().setSeriesPaint(0, Color.BLUE);
                		break;
	        		
                	case 1:
                		barraItem.getRenderer().setSeriesPaint(0, Color.RED);
                		break;
	        		
                	case 2:
    	        		barraItem.getRenderer().setSeriesPaint(0, Color.ORANGE);
    	        		break;
    	        	
                	case 3:
    		        	barraItem.getRenderer().setSeriesPaint(0, Color.YELLOW);
    		        	break;
    		        
                	case 4:
    			       	barraItem.getRenderer().setSeriesPaint(0, Color.PINK);
    			        break;
    		        
                	case 5:
    				    barraItem.getRenderer().setSeriesPaint(0, Color.GREEN);
    				    break;
        		}

                //Mostra o gr�fico na tela, atrav�s de uma nova janela
                ChartPanel painel = new ChartPanel(grafico);
        		add(painel);

		}
		
		//Tratamento de erros, demais erros, n�mero de planilhas errado e de falha na localiza��o do arquivo. Finally fecha o arquivo e trata poss�vel erro
		catch (FileNotFoundException error1) 
	    {
	        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, error1);
	        System.out.println("Erro na importa��o do local do arquivo. Por favor verifique se o caminho est� correto!\nErro: " +error1);
	        System.exit(0);
	    } 
		catch (IOException error1)
		{
			System.out.println("Erro na importa��o do local do arquivo. Por favor verifique se o caminho est� correto!\nErro: " +error1);
			System.exit(0);
		}
		catch (IllegalArgumentException error2)
		{
			System.out.println("Erro na importa��o das planilhas do arquivo. Por favor verifique se sua formata��o est� correto!\nErro: " +error2);
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
	}
}