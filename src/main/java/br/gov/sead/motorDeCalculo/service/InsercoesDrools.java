package br.gov.sead.motorDeCalculo.service;

import br.gov.sead.motorDeCalculo.model.IPE;
import br.gov.sead.motorDeCalculo.model.SalarioMinimo;
import br.gov.sead.motorDeCalculo.model.INSS;
import br.gov.sead.motorDeCalculo.model.IRRF;
import br.gov.sead.motorDeCalculo.model.RubricaDrools;
import br.gov.sead.motorDeCalculo.repository.INSSRepository;
import br.gov.sead.motorDeCalculo.repository.IPERepository;
import br.gov.sead.motorDeCalculo.repository.IRRFRepository;
import br.gov.sead.motorDeCalculo.repository.RubricaDroolsRepository;
import br.gov.sead.motorDeCalculo.repository.SalarioMinimoRepository;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InsercoesDrools {

    private INSSRepository inssRepository;

    private SalarioMinimoRepository salarioMinimoRepository;

    private IRRFRepository irrfRepository;

    private RubricaDroolsRepository rubricaDroolsRepository;

    private IPERepository ipeRepository;

    public  InsercoesDrools(INSSRepository inssRepository, SalarioMinimoRepository salarioMinimoRepository,
                            IRRFRepository irrfRepository, RubricaDroolsRepository rubricaDroolsRepository,
                            IPERepository ipeRepository) {
        this.inssRepository = inssRepository;
        this.salarioMinimoRepository = salarioMinimoRepository;
        this.irrfRepository = irrfRepository;
        this.rubricaDroolsRepository = rubricaDroolsRepository;
        this.ipeRepository = ipeRepository;
    }
    @PostConstruct
    void instanciaDB() {

        List<SalarioMinimo> salarios = new ArrayList<>();

        salarios.add(new SalarioMinimo("100.00", "01-05-1995", "01-05-1996"));
        salarios.add(new SalarioMinimo("112.00", "01-05-1996", "01-05-1997"));
        salarios.add(new SalarioMinimo("120.00", "01-05-1997", "01-05-1998"));
        salarios.add(new SalarioMinimo("130.00", "01-05-1998", "01-05-1999"));
        salarios.add(new SalarioMinimo("136.00", "01-05-1999", "01-04-2000"));
        salarios.add(new SalarioMinimo("151.00", "01-04-2000", "01-04-2001"));
        salarios.add(new SalarioMinimo("180.00", "01-04-2001", "01-04-2002"));
        salarios.add(new SalarioMinimo("200.00", "01-04-2002", "01-04-2003"));
        salarios.add(new SalarioMinimo("240.00", "01-04-2003", "01-05-2004"));
        salarios.add(new SalarioMinimo("260.00", "01-05-2004", "01-05-2005"));
        salarios.add(new SalarioMinimo("300.00", "01-05-2005", "01-04-2006"));
        salarios.add(new SalarioMinimo("350.00", "01-04-2006", "01-04-2007"));
        salarios.add(new SalarioMinimo("380.00", "01-04-2007", "01-03-2008"));
        salarios.add(new SalarioMinimo("415.00", "01-03-2008", "01-02-2009"));
        salarios.add(new SalarioMinimo("465.00", "01-02-2009", "01-01-2010"));
        salarios.add(new SalarioMinimo("510.00", "01-01-2010", "01-01-2011"));
        salarios.add(new SalarioMinimo("545.00", "01-01-2011", "01-01-2012"));
        salarios.add(new SalarioMinimo("622.00", "01-01-2012", "01-01-2013"));
        salarios.add(new SalarioMinimo("678.00", "01-01-2013", "01-01-2014"));
        salarios.add(new SalarioMinimo("724.00", "01-01-2014", "01-01-2015"));
        salarios.add(new SalarioMinimo("788.00", "01-01-2015", "01-01-2016"));
        salarios.add(new SalarioMinimo("880.00", "01-01-2016", "01-01-2017"));
        salarios.add(new SalarioMinimo("937.00", "01-01-2017", "01-01-2018"));
        salarios.add(new SalarioMinimo("954.00", "01-01-2018", "01-01-2019"));
        salarios.add(new SalarioMinimo("998.00", "01-01-2019", "01-01-2020"));
        salarios.add(new SalarioMinimo("1039.00", "01-01-2020", "01-02-2020"));
        salarios.add(new SalarioMinimo("1045.00", "01-02-2020", "01-01-2021"));
        salarios.add(new SalarioMinimo("1100.00", "01-01-2021", "01-01-2022"));
        salarios.add(new SalarioMinimo("1212.00", "01-01-2022", "01-01-2023"));

        salarioMinimoRepository.saveAll(salarios);


        List<INSS> listaINSS = Stream.of(new INSS(new BigDecimal("0"), new BigDecimal("1212.00"), "0.075"),
                new INSS(new BigDecimal("1212.01"), new BigDecimal("2427.35"), "0.09"),
                new INSS(new BigDecimal("2427.36"), new BigDecimal("3641.03"), "0.12"),
                new INSS(new BigDecimal("3641.04"), new BigDecimal("7087.22"), "0.14")).collect(Collectors.toList());

        inssRepository.saveAll(listaINSS);

        List<IPE> listaIPE = Stream.of(new IPE(new BigDecimal("0"), new BigDecimal("4065.73"), "0.11"),
                new IPE(new BigDecimal("4065.74"), new BigDecimal("7087.22"), "0.14"),
                new IPE(new BigDecimal("7087.23"), new BigDecimal("17424.56"), "0.15"),
                new IPE(new BigDecimal("17424.57"), new BigDecimal("34849.11"), "0.16"),
                new IPE(new BigDecimal("34849.12"), new BigDecimal("0"), "0.18")).collect(Collectors.toList());

        ipeRepository.saveAll(listaIPE);

        List<IRRF> tabelaIRRF = Stream.of(new IRRF(new BigDecimal("0.00"), new BigDecimal("1903.98"), "0.00", new BigDecimal("0.00")),
                new IRRF(new BigDecimal("1903.99"), new BigDecimal("2826.65"), "0.075", new BigDecimal("142.80")),
                new IRRF(new BigDecimal("2826.66"), new BigDecimal("3751.05"), "0.15", new BigDecimal("354.80")),
                new IRRF(new BigDecimal("3751.06"), new BigDecimal("4664.68"), "0.225", new BigDecimal("636.13")),
                new IRRF(new BigDecimal("4664.69"), new BigDecimal("0.00"), "0.275", new BigDecimal("869.36"))).collect(Collectors.toList());

        irrfRepository.saveAll(tabelaIRRF);

        List<RubricaDrools> rubricaDroolsList = List.of(new RubricaDrools("001", "Vencimento Básico", "Vencimento básico tabelado do cargo","Vantagem", LocalDate.of(2010, 01, 01),LocalDate.of(2022, 01, 01), true, true, true),
                new RubricaDrools("002", "Quinquênio", "Adicional por tempo de serviço", "Vantagem",  LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, true, true),
                new RubricaDrools("003", "Insalubridade", "Adicional devido à insalubridade do cargo", "Vantagem", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, true, true),
                new RubricaDrools("004", "Periculosidade", "Adicional devido à periculosidade do cargo", "Vantagem",LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), true, true, true),
                new RubricaDrools("005", "Ferias", "Adicional de férias", "Vantagem", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), true, false, false),
                new RubricaDrools("006", "Faltas", "Desconto referente às faltas do servidor", "Desconto", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, false, false),
                new RubricaDrools("007", "Salário Família", "Salário família do servidor", "Vantagem", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), true, true, true),
                new RubricaDrools("008", "Adicional Noturno", "Adicional referente às horas noturnas do servidor", "Vantagem", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), true, true, true),
                new RubricaDrools("009", "INSS", "Desconto previdenciário do celetista", "Desconto", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, false, false),
                new RubricaDrools("010", "IPE","Desconto previdenciário do estatutário", "Desconto",LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, false, false),
                new RubricaDrools("011", "IRRF", "Imposto de renda do servidor", "Desconto", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, false, false),
                new RubricaDrools("012", "Décimo Terceiro", "Gratificação natalina proporcional", "Vantagem", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, false, false),
                new RubricaDrools("E01", "Salario Bruto", "Salario bruto do servidor", "Vantagem", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, false, false),
                new RubricaDrools("E02", "Salario liquido", "Salario liquido do servidor", "Vantagem", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, false, false),
                new RubricaDrools("A01", "somatorio IRRF", "variavel para auxiliar no calculo do irrf", "Desconto", LocalDate.of(2010, 01, 01), LocalDate.of(2022, 01, 01), false, false, false));

        rubricaDroolsRepository.saveAll(rubricaDroolsList);
    }

}
