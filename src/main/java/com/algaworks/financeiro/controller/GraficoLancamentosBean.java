package com.algaworks.financeiro.controller;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import com.algaworks.financeiro.model.Lancamento;
//import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.financeiro.repository.Lancamentos;
//import com.algaworks.pedidovenda.security.UsuarioLogado;
//import com.algaworks.pedidovenda.security.UsuarioSistema;

@Named
@SessionScoped
public class GraficoLancamentosBean implements Serializable {

	// private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentos;
	
	@Inject
	private ConsultaLancamentosBean lancamentosbean;
	
	@Inject
	private Lancamento lancamento;	

	// @Inject
	// @UsuarioLogado
	// private UsuarioSistema usuarioLogado;

	private LineChartModel model;

	public void preRender() {
		this.model = new LineChartModel();
		this.model.setTitle("Lançamentos");
		this.model.setLegendPosition("e");
		this.model.setAnimate(true);		
		this.model.getAxes().put(AxisType.X, new CategoryAxis());		
	   adicionarSerie("RECEITAS|DESPESAS");
	   //adicionarSerieNeg("DEPESAS");
	   
	  // adicionarSerie("Todos os pedidos", null);
		//adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
	}
	
	/*private void adicionarSerieNeg(String string) {
		//this.lancamentos.saldoNegativo();
		ChartSeries series1 = new ChartSeries();
		series1.set("Despesas", 0);
		//series1.set("Despesas2", lancamentosbean.getTotal());
		series1.set("Despesas1",  lancamentos.saldoNegativo());	
		this.model.addSeries(series1);
	}*/	
	
	

	private void adicionarSerie(String string) {
		this.lancamentos.Lucro(0);		
		
		//series1.setLabel("Lancamento");		
		System.out.println("CHART-:"+lancamentos.Lucro(0)
		+"-->VALORES: "+ lancamento.getValor()+"TOTAL->"+lancamentosbean.getTotal());
		ChartSeries series = new ChartSeries();	
		series.set("Inicial", 0);
		series.set("Total de Receitas* "+lancamentos.Lucro(15),  lancamentos.Lucro(15));  
		series.set("Total Negativado* "+lancamentos.saldoNegativo(),  lancamentos.saldoNegativo());
		series.set("SALDO* "+lancamentosbean.getLucro(),lancamentosbean.getLucro());		
		
		/*series.set("Pesquisa 3", 400);
		series.set("Pesquisa 4",90);
		series.set("Pesquisa 5", 800);*/
		/*
		 * for (Date data : valoresPorData.keySet()) {
		 * series.set(DATE_FORMAT.format(data), valoresPorData.get(data)); }*/	 
		this.model.addSeries(series);
	}	
	

	public LineChartModel getModel() {
		return model;
	}

}
