package Qualidade_Ar_Santos;

import java.io.IOException;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;

@SuppressWarnings("serial")
public class GraficoDistribui��oNormal extends JFrame 
{
	public GraficoDistribui��oNormal() throws IOException 
	{		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Gr�fico de distribui��o normal");
		setSize(950,700);
		setLocationRelativeTo(null);
		criarGrafico();
		setVisible(true);
	}                
               
	private void criarGrafico() 
	{
		float vMinimo = (float) (Menu.mediaGrafico - Menu.desvioPadraoGrafico); //Determina o valor m�nimo no da linha, que � dado por: VMP = m�dia - desvio padr�o
		float vMaximo = (float) (Menu.mediaGrafico + Menu.desvioPadraoGrafico); //Determina o valor m�ximo no da linha, que � dado por: VMP = m�dia + desvio padr�o
		float altura = (float) (Menu.desvioPadraoGrafico) / 5; //Determina a altura no eixo y da linha
		
		Function2D normal = new NormalDistributionFunction2D(Menu.mediaGrafico, altura); //Topo do gr�fico (m�dia) e onde come�a a subir
        XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, vMinimo, vMaximo, 1000, 
        		"Varia��o de m�dia e desvio padr�o \nLimite m�nimo - M�dia (pico) - Limite m�ximo"); //Limite m�nimo, limite m�ximo e grossura da linha
        JFreeChart chart = ChartFactory.createXYLineChart(Menu.titulo, "", "", dataset, PlotOrientation.VERTICAL, true, true, true);

        //Cria��o do painel que ir� mostrar o gr�fico na tela
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(750, 500));
        setContentPane(chartPanel);	
	}
}