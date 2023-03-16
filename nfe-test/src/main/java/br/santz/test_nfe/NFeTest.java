package br.santz.test_nfe;

import com.fincatto.documentofiscal.DFModelo;
import com.fincatto.documentofiscal.nfe400.classes.statusservico.consulta.NFStatusServicoConsultaRetorno;
import com.fincatto.documentofiscal.nfe400.webservices.WSFacade;

public class NFeTest {
	
	public static void main (String args[]) {
		
		NFeConfigTest config = new NFeConfigTest();
		
		NFStatusServicoConsultaRetorno retorno = null;
		try {
			retorno = new WSFacade(config).consultaStatus(config.getCUF(), DFModelo.NFE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(retorno.getStatus());
		System.out.println(retorno.getMotivo());		
	}
}
