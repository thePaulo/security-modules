//package br.gov.sead.motorDeCalculo.service;
//
//import br.gov.sead.motorDeCalculo.model.*;
//import br.gov.sead.motorDeCalculo.repository.*;
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.KieModule;
//import org.kie.api.builder.KieRepository;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.kie.internal.io.ResourceFactory;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class DroolsService {
//
//    private KieSession session;
//
//    private INSSRepository inssRepository;
//
//    private IPERepository ipeRepository;
//
//    private SalarioMinimoRepository salarioRepository;
//
//    private IRRFRepository irrfRepository;
//
//    private SnapshotRepository snapshotRepository;
//
//    private VantagemDescontoRepository vantagemDescontoRepository;
//
//    private ContrachequeRepository contrachequeRepository;
//
//    private RubricaContrachequeRepository rubricaContrachequeRepository;
//
//    private RubricaDroolsRepository rubricaDroolsRepository;
//
//    private InsercoesDrools insercoesDrools;
//
//    private int quantExecucao;
//
//    public DroolsService(KieSession session,IPERepository ipeRepository, INSSRepository inssRepository, SalarioMinimoRepository salarioMinimoRepository,
//                         IRRFRepository irrfRepository, SnapshotRepository snapshotRepository,
//                         VantagemDescontoRepository vantagemDescontoRepository, InsercoesDrools insercoesDrools,
//                         ContrachequeRepository contrachequeRepository, RubricaDroolsRepository rubricaDroolsRepository,
//                         RubricaContrachequeRepository rubricaContrachequeRepository) {
//
//        this.session = session;
//        this.inssRepository = inssRepository;
//        this.ipeRepository = ipeRepository;
//        this.salarioRepository = salarioMinimoRepository;
//        this.irrfRepository = irrfRepository;
//        this.snapshotRepository = snapshotRepository;
//        this.vantagemDescontoRepository = vantagemDescontoRepository;
//        this.insercoesDrools = insercoesDrools;
//        this.contrachequeRepository = contrachequeRepository;
//        this.quantExecucao = 0;
//        this.rubricaDroolsRepository = rubricaDroolsRepository;
//        this.rubricaContrachequeRepository = rubricaContrachequeRepository;
//    }
//
//    public void rodarApp() throws IOException {
//
//        // permitir que o metodo seja executado mais de uma vez sem acusar erro
//        if(quantExecucao > 0)  {
//            rubricaContrachequeRepository.deleteAll();
//            snapshotRepository.deleteAll();
//        }
//
//        quantExecucao++;
//
//        insercoesDrools.instanciaDB();
//
//        List<INSS> listaINSS = inssRepository.findAll();
//
//        List<IPE> listaIPE = ipeRepository.findAll();
//
//        List<IRRF> tabelasNoRepositorio = irrfRepository.findAll();
//
//        List<SalarioMinimo> salariosNoRepositorio = salarioRepository.findAll();
//
//        List<Snapshot> snapshots_agosto = snapshotRepository.buscarComData("30-12-2022");
//
//        List<Snapshot> snapshots = new ArrayList<>();
//
//        snapshots.addAll(snapshots_agosto);
//
//        String drlVantagens = "vantagens.drl";
//        configurarDrools(drlVantagens);
//
//        session.insert(listaINSS);
//        session.insert(listaIPE);
//
//        session.insert(tabelasNoRepositorio);
//
//        inserindoDadosNoDrools(session, snapshots, salariosNoRepositorio);
//
//        session.fireAllRules();
//
//        snapshotRepository.saveAll(snapshots);
//
//        for(Snapshot servidor: snapshots) {
//            servidor.setSomatorioIRRF(vantagemDescontoRepository.somatorioIRRF(servidor.getVinculoId(), LocalDate.parse("2022-12-30")));
//            servidor.setSomatorioINSS(vantagemDescontoRepository.somatorioINSS(servidor.getVinculoId(), LocalDate.parse("2022-12-30")));
//            servidor.setSomatorioIPE(vantagemDescontoRepository.somatorioIPE(servidor.getVinculoId(), LocalDate.parse("2022-12-30")));
//        }
//
//        String drlDescontos = "descontos.drl";
//
//        configurarDrools(drlDescontos);
//
//        session.insert(listaINSS);
//        session.insert(listaIPE);
//        session.insert(tabelasNoRepositorio);
//
//        inserindoDadosNoDrools(session, snapshots, salariosNoRepositorio);
//
//        session.fireAllRules();
//
//        snapshotRepository.saveAll(snapshots);
//
//        //impressao simples da lista de vantagens e descontos dos vinculos
//        for (Snapshot servidor : snapshots) {
//            servidor.setSomatorioDescontos(vantagemDescontoRepository.somatorioDescontos(servidor.getVinculoId(),LocalDate.parse("2022-12-30")));
//            servidor.setLiquido(servidor.getBruto().subtract(vantagemDescontoRepository.somatorioDescontos(servidor.getVinculoId(), LocalDate.parse("2022-12-30"))));
//            for (VantagemDesconto vantagemDesconto : servidor.getVantagemDescontos()) {
//                System.out.println("cod: " + vantagemDesconto.getCodRubrica() + " valor: " + vantagemDesconto.getValor() + " vinculoId: " + vantagemDesconto.getSnapshot().getVinculoId() + " vigencia: " + vantagemDesconto.getVigencia());
//            }
//            System.out.println("Somatório IRRF: " + servidor.getSomatorioIRRF());
//            System.out.println("Somatório INSS: " + servidor.getSomatorioINSS());
//            System.out.println("Somatório IPE: " + servidor.getSomatorioIPE());
//            System.out.println("Bruto: " + servidor.getBruto());
//            System.out.println("Liquído: " + servidor.getLiquido());
//        }
//
//        snapshotRepository.saveAll(snapshots);
//
//        List<Contracheque> contracheques = new ArrayList<>();
//        int i=0;
//        for(Snapshot servidor: snapshots) {
//            contracheques.add(contrachequeRepository.buscarContracheque(servidor.getMatricula()));
//            List<RubricaContracheque> rubricaContracheques = rubricaContrachequeRepository.buscarRubricasContracheque(servidor.getVinculoId());
//            for(RubricaContracheque rubricas: rubricaContracheques) {
//                rubricas.setContracheque(contracheques.get(i));
//            }
//            contracheques.get(i).setRubricasContracheque(rubricaContracheques);
//            i++;
//        }
//        contrachequeRepository.saveAll(contracheques);
//    }
//
//    void configurarDrools(String arquivoDRL) {
//
//        KieServices services = KieServices.Factory.get();
//        KieRepository repository = services.getRepository();
//
//        KieFileSystem kieFileSystem = services.newKieFileSystem();
//        kieFileSystem.write((ResourceFactory.newClassPathResource(arquivoDRL)));
//
//        KieBuilder builder = services.newKieBuilder(kieFileSystem);
//        builder.buildAll();
//
//        KieModule module = repository.getKieModule(repository.getDefaultReleaseId());
//        KieContainer container = services.newKieContainer(module.getReleaseId());
//        session = container.newKieSession();
//    }
//
//    void inserindoDadosNoDrools(KieSession session, List<Snapshot> snapshots, List<SalarioMinimo> salariosNoRepositorio) {
//        for(Snapshot servidor : snapshots){
//            session.insert(salariosNoRepositorio.stream().filter(s ->
//                    servidor.getVigencia().isAfter(s.getDataCriacao()) && servidor.getVigencia().isBefore(s.getDataExtincao())
//            ).collect(Collectors.toList()).get(0));
//            session.insert(servidor);
//        }
//    }
//
//}
//
