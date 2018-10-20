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
public class GraficoDistribuiçãoNormal extends JFrame 
{
	public GraficoDistribuiçãoNormal() throws IOException 
	{		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Gráfico de distribuição normal");
		setSize(950,700);
		setLocationRelativeTo(null);
		criarGrafico();
		setVisible(true);
	}                
               
	private void criarGrafico() 
	{
		float vMinimo = (float) (Menu.mediaGrafico - Menu.desvioPadraoGrafico); //Determina o valor mínimo no da linha, que é dado por: VMP = média - desvio padrão
		float vMaximo = (float) (Menu.mediaGrafico + Menu.desvioPadraoGrafico); //Determina o valor máximo no da linha, que é dado por: VMP = média + desvio padrão
		float altura = (float) (Menu.desvioPadraoGrafico) / 5; //Determina a altura no eixo y da linha
		
		Function2D normal = new NormalDistributionFunction2D(Menu.mediaGrafico, altura); //Topo do gráfico (média) e onde começa a subir
        XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, vMinimo, vMaximo, 1000, 
        		"Variação de média e desvio padrão \nLimite mínimo - Média (pico) - Limite máximo"); //Limite mínimo, limite máximo e grossura da linha
        JFreeChart chart = ChartFactory.createXYLineChart(Menu.titulo, "", "", dataset, PlotOrientation.VERTICAL, true, true, true);

        //Criação do painel que irá mostrar o gráfico na tela
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(750, 500));
        setContentPane(chartPanel);	
	}
}