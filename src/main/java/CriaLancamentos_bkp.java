import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.util.JpaUtil;

public class CriaLancamentos_bkp {

	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		
		
		 /*int  count=1;
	        for(trx.begin(); count <= 10 ; count++){
	            System.out.println(count);	
	        } 	*/	

		Calendar dataVencimento1 = Calendar.getInstance();
		dataVencimento1.set(2013, 10, 1, 0, 0, 0);

		Calendar dataVencimento2 = Calendar.getInstance();
		dataVencimento2.set(2013, 12, 10, 0, 0, 0);

		Pessoa cliente = new Pessoa();
		cliente.setNome("Igor");
		
		
		/*Pessoa iddaPessoa = new Pessoa();
		iddaPessoa.setId((long) 14);*/

		/*
		 * Pessoa fornecedor = new Pessoa();
		 * fornecedor.setNome("SoftBRAX Treinamentos");
		 */

		Lancamento lancamento3 = new Lancamento();
		/*lancamento3.setDescricao("Treinamento da equipe");
		lancamento3.setPessoa(cliente);
		lancamento3.setDataVencimento(dataVencimento2.getTime());
		lancamento3.setValor(new BigDecimal(68_000));
		lancamento3.setTipo(TipoLancamento.DESPESA);*/

		manager.persist(cliente);
		//manager.persist(lancamento3);
		
		 int  count=1;
        for(
        	lancamento3.setDescricao("Treinamento da equipe"),
        	lancamento3.setPessoa(cliente),
        	lancamento3.setDataVencimento(dataVencimento2.getTime()),
        	lancamento3.setValor(new BigDecimal(68_000)),
        	lancamento3.setTipo(TipoLancamento.DESPESA),
        	manager.persist(lancamento3); count <= 10 ; count++){
            System.out.println(count);	
        } 	
		
		// manager.persist(fornecedor);
		//manager.persist(lancamento1);
		//manager.persist(lancamento2);
	    //manager.persist(lancamento3);		
				
		

		trx.commit();
		manager.close();

		/*
		 * Lancamento lancamento3 = new Lancamento(); int count=1;
		 * for(lancamento3.setDescricao("Treinamento da equipe"),
		 * lancamento3.setPessoa(cliente),
		 * lancamento3.setDataVencimento(dataVencimento2.getTime()),
		 * lancamento3.setValor(new BigDecimal(68_000)),
		 * lancamento3.setTipo(TipoLancamento.DESPESA); count <= 10 ; count++){
		 * System.out.println(count); }
		 */
		
		

	}

}
