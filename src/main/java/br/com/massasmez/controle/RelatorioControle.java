/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;


import br.com.massasmez.util.ReportsUtil;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Marcelo
 */
@ManagedBean
@RequestScoped
public class RelatorioControle {

    @Resource(mappedName = "pedidoMassas_JNDI")
    private DataSource dataSource;

    public void gerarRelatorio(String arquivo) throws JRException {
        try {
            ReportsUtil ru = new ReportsUtil();
            ru.gerarRelatorioPDF(null, "/WEB-INF/relatorios/"+arquivo+".jasper", 
                    dataSource.getConnection(), 
                    "cidade.pdf");
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

}
