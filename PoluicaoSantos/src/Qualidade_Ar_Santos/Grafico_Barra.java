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
		setTitle("Gráfico de comparação de dados");
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
			fisPlanilha = new FileInputStream(new File(Menu.arquivo)); //Leia os dados do gráfico de barras do arquivo excel
            
            XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha); //Cria um workbook para verificar se o arquivo é mesmo um excel
           
            XSSFSheet sheet = workbook.getSheetAt(x); //Seleciona qual planilha de dados será passada para o gráfico. x é o valor de "Menu.planilha" passada por no menu
           
            DefaultCategoryDataset barra = new DefaultCategoryDataset(); //Criar conjunto de dados que levará os dados do gráfico
            
            /* Temos que carregar os dados do gráfico de barras agora 
             Comece iterando pela planilha 
             Crie um objeto Iterator */
            Iterator<Row> rowIterator = sheet.iterator(); 
            
            //Fazer um loop pelos dados da planilha e preencher o conjunto de dados do gráfico de barras
            String caracter="a";
            Number numero=0;
                while(rowIterator.hasNext()) 
                {
                	Row row = rowIterator.next();  //Lê linhas do documento do Excel
                	
                	Iterator<Cell> cellIterator = row.cellIterator(); //Lê células em linhas e obter dados do gráfico
                		
                		while(cellIterator.hasNext()) 
                		{
                			Cell celula = cellIterator.next(); 
                			
                				switch(celula.getCellType()) //Testa o tipo de dado que está sendo inserido pela tabela
                				{ 
                					case Cell.CELL_TYPE_NUMERIC:
                						numero=celula.getNumericCellValue();
                						break;
                					case Cell.CELL_TYPE_STRING:
                						caracter=celula.getStringCellValue();
                						break;
                				}
                		}          
                	barra.addValue(numero.doubleValue(),Menu.legenda,caracter); //Adiciona os valores colhidos à cada linha do gráfico, respeitando o tipo
                																//de dado que está sendo adicionado à sua devida posição
                }               
                
                XSSFCell titulo = workbook.getSheetAt(Menu.pla).getRow(x).getCell(0); //Seleciona a planilha de títulos, são essas duas linhas responsáveis por dar o
                Menu.titulo = titulo.getStringCellValue();					   //título correto à cada gráfico de cada gás
                
                // Cria um objeto gráfico lógico com os dados do gráfico coletados
                JFreeChart grafico=ChartFactory.createBarChart3D(Menu.titulo, "Local e medição", Menu.valoresNome,barra,PlotOrientation.HORIZONTAL,true,true,false);  
                
        		//Determina uma cor para as colunas (como são um único bloco, não é possivel colorir diferentemente cada linha)
                CategoryPlot barraItem = grafico.getCategoryPlot();
                switch (Menu.planilha) //Identifica qual planilha está sendo lida (por tanto qual gás) e determina uma cor única para cada gráfico de cada gás
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

                //Mostra o gráfico na tela, através de uma nova janela
                ChartPanel painel = new ChartPanel(grafico);
        		add(painel);

		}
		
		//Tratamento de erros, demais erros, número de planilhas errado e de falha na localização do arquivo. Finally fecha o arquivo e trata possível erro
		catch (FileNotFoundException error1) 
	    {
	        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, error1);
	        System.out.println("Erro na importação do local do arquivo. Por favor verifique se o caminho está correto!\nErro: " +error1);
	        System.exit(0);
	    } 
		catch (IOException error1)
		{
			System.out.println("Erro na importação do local do arquivo. Por favor verifique se o caminho está correto!\nErro: " +error1);
			System.exit(0);
		}
		catch (IllegalArgumentException error2)
		{
			System.out.println("Erro na importação das planilhas do arquivo. Por favor verifique se sua formatação está correto!\nErro: " +error2);
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