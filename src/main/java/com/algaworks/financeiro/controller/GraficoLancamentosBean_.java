package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import com.algaworks.financeiro.model.Lancamento;

@ManagedBean(name = "GraficoLinhaMB")
public class GraficoLancamentosBean_ implements Serializable {
	private LineChartModel lineModel1;
	
	private List<Lancamento> lancamentos;
	
	ConsultaLancamentosBean luc = new ConsultaLancamentosBean();

	private BigDecimal lucro;
	
	public BigDecimal getLucro() {
		return luc.getLucro();
	}

	public void setLucro(BigDecimal lucro) {
		this.lucro = luc.getLucro();
		System.out.println("LUCRO CHART"+luc.getLucro());
	}

	@PostConstruct
	public void init() {
		createLineModels();
	}

	public CartesianChartModel getLineModel1() {
		return lineModel1;
	}

	private void createLineModels() {
		lineModel1 = initLinearModel();
		lineModel1.setTitle("Gráficos");
		lineModel1.setLegendPosition("e");
		lineModel1.setShowPointLabels(true);
		lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Lançamentos"));
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setLabel("Receitas|Despesas");
		yAxis.setMin(0);
		yAxis.setMax(100);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();
		ChartSeries series1 = new ChartSeries();
		
		series1.setLabel("Lancamento");
		series1.set("Pesquisa 1", luc.getLucro());
		series1.set("Pesquisa 2",luc.getSaldoNegativos());
	
		//System.out.println("CHART:"+luc.getLucro());
		/*ChartSeries series2 = new ChartSeries();
		series2.setLabel("Candidato 2");
		series2.set("Pesquisa 1", 20);
		series2.set("Pesquisa 2", 22);
		series2.set("Pesquisa 3", 26);
		series2.set("Pesquisa 4", 25);
		series2.set("Pesquisa 5", 29);
		ChartSeries series3 = new ChartSeries();
		series3.setLabel("Candidato 3");
		series3.set("Pesquisa 1", 10);
		series3.set("Pesquisa 2", 12);
		series3.set("Pesquisa 3", 16);
		series3.set("Pesquisa 4", 15);
		series3.set("Pesquisa 5", 19);*/
		model.addSeries(series1);
		//model.addSeries(series2);
		//model.addSeries(series3);
		return model;
	}
}
