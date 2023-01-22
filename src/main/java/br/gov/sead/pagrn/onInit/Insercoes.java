package br.gov.sead.pagrn.onInit;

import br.gov.sead.pagrn.domain.concrets.*;
import br.gov.sead.pagrn.domain.concrets.TipoGratificacao;
import br.gov.sead.pagrn.domain.events.ProvimentoDeCargo;
import br.gov.sead.pagrn.domain.events.ProvimentoDeFuncao;
import br.gov.sead.pagrn.domain.type.*;
import br.gov.sead.pagrn.domain.vinculos.Vinculo;
import br.gov.sead.pagrn.service.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public record Insercoes(VinculoService vinculoService, PessoaFisicaService pessoaFisicaService, ServidorService servidorService,
                        PessoaJuridicaService pessoaJuridicaService, UnidadeOrganizacionalService unidadeOrganizacionalService,
                        SetorService setorService, CargoService cargoService,
                        ProvimentosService provimentoService,
                        RemuneracaoBasicaService remuneracaoBasicaService,
                        CboService cboService, GrupoOcupacionalService grupoOcupacionalService, PCCRService pccrService,
                        RubricaService rubricaService, JornadaTrabalhoService jornadaTrabalhoService,
                        NivelDesempenhoService nivelDesempenhoService, FuncaoService funcaoService,
                        TipoGratificacaoService tipoGratificacaoService) {

    @PostConstruct
    public void init() {
        Deficiencia deficiencia = new Deficiencia();
        Endereco endereco = criarEndereco("Potengi", "12334-132", "Natal", "Avareza", "10",
                TipoLogradouro.RUA, Pais.BRASIL, UnidadeFederativa.RIO_GRANDE_DO_NORTE);

        PessoaFisica pessoaFisica = inserirPessoaFisica("João Grilo", "84833783452", "joaogrilo@gmail.com",
                Genero.MASCULINO, TipoSanguineo.Op, "João da Prestação", "Maria Joana",
                "Brasileiro", "Natal", EstadoVital.VIVO, EstadoCivil.SOLTEIRO, deficiencia, endereco);

        Servidor servidor = inserirServidor("12345", "54321", "Joãozinho", Escolaridade.SUPERIOR, 1L);

        endereco = criarEndereco("Igapó", "12334-142", "Natal", "Varzea", "SN",
                TipoLogradouro.AVENIDA, Pais.BRASIL, UnidadeFederativa.RIO_GRANDE_DO_NORTE);

        PessoaJuridica pessoaJuridica = inserirPessoaJuridica("Governo do Estado" , "govrn",
                "88450606000170", ClassificacaoTributaria.TIPO_1, endereco);

        UnidadeOrganizacional unidadeOrganizacional1 = inserirUnidadeOrganizacional(LocalDate.now(), "31231",
                "1", "SEADRH", 1L);

        //UnidadeOrganizacional unidadeOrganizacional2 = inserirUnidadeOrganizacional(LocalDate.now(), "31232", "2", "SET", 1L);

        endereco = criarEndereco("Igapó", "12334-142", "Natal", "Varzea", "SN",
                TipoLogradouro.AVENIDA, Pais.BRASIL, UnidadeFederativa.RIO_GRANDE_DO_NORTE);

        Setor setor1 = inserirSetor("Coordenadorai de Pagamentos", "COPAG", null, 1L, endereco);

        //Setor setor2 = inserirSetor("Coordenadoria de Tecnologia da Informação e Comunicação", "COTIC", null, 1L, endereco);

        Vinculo vinculo = inserirVinculo(TipoVinculo.EFETIVO_CIVIL, SituacaoVinculo.ATIVO, RegimeJuridico.ESTATUTARIO,
                1L, 1L, 1L, 1L);

        Cbo cbo = inserirCbo("sei la", "12");

        List<UnidadeOrganizacional> unidadeOrganizacionals = new ArrayList<>();
        unidadeOrganizacionals.add(unidadeOrganizacional1);
        //unidadeOrganizacionals.add(unidadeOrganizacional2);

        PCCR pccr = inserirPCCR("pcccr", LocalDate.now(), unidadeOrganizacionals, LocalDate.of(2030, 4, 20));

        Rubrica rubrica = inserirRubrica("rubrica 1", "R1", "R1");

        GrupoOcupacional grupoOcupacional = inserirGrupoOcupacional("seila", Escolaridade.ENSINO_MEDIO, pccr,
                rubrica, 2);

        Cargo cargo = inserirCargo("Programador", Escolaridade.SUPERIOR, cbo, grupoOcupacional, 10);

        JornadaTrabalho jornadaTrabalho = inserirJornadaTrabalho(30, 1);

        NivelDesempenho nivelDesempenho = inserirNivelDesempenho("1", "A", grupoOcupacional);

        RemuneracaoBasica remuneracaoBasica = inserirRemuneracaoBasica(BigDecimal.valueOf(5000), cargo,
                jornadaTrabalho, nivelDesempenho);


        inserirProvimentoDeCargo(remuneracaoBasica, LocalDate.now(), LocalDate.now(), LocalDate.now(), LocalDate.now(),
                "31287631231", RegimeJuridico.ESTATUTARIO, TipoVinculo.EFETIVO_CIVIL,
                "Nomeação de servidor efetivo civil", 1L, 1L, 1L);

        TipoGratificacao tipoGratificacao = inserirTipoGratificacao(rubrica, "das", LocalDate.now(),
                LocalDate.of(2030, 2, 15), "VB", BigDecimal.valueOf(1000), BigDecimal.valueOf(5000));

        inserirFuncao(cbo, "sei la cara", 10, LocalDate.now(), LocalDate.of(2030, 2, 15),
                tipoGratificacao, unidadeOrganizacional1);

        inserirProvimentoDeFuncao(LocalDate.now(), LocalDate.now(), LocalDate.now(), "281y281",
                RegimeJuridico.ESTATUTARIO, TipoVinculo.COMISSIONADO, LocalDate.now(), "provimentoFuncao",
                1L, 1L, 1L, 1L);
    }

    private Endereco criarEndereco(String bairro, String cep, String cidade, String logradouro, String numero,
                                   TipoLogradouro tipoLogradouro, Pais pais, UnidadeFederativa unidadeFederativa){
        Endereco endereco = new Endereco();
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setTipoLogradouro(tipoLogradouro);
        endereco.setPais(pais);
        endereco.setUnidadeFederativa(unidadeFederativa);
        return endereco;
    }

    private PessoaFisica inserirPessoaFisica(String nome, String cpf, String email, Genero genero,
                                             TipoSanguineo tipoSanguineo, String nomePai, String nomeMae,
                                             String nacionalidadePais, String naturalidadeCidade, EstadoVital estadoVital,
                                             EstadoCivil estadoCivil, Deficiencia deficiencia, Endereco endereco){
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome(nome);
        pessoaFisica.setCpf(cpf);
        pessoaFisica.setEmail(email);
        pessoaFisica.setGenero(genero);
        pessoaFisica.setDataNascimento(LocalDate.EPOCH);
        pessoaFisica.setTipoSanguineo(tipoSanguineo);
        pessoaFisica.setNomePai(nomePai);
        pessoaFisica.setNomeMae(nomeMae);
        pessoaFisica.setNacionalidadePais(nacionalidadePais);
        pessoaFisica.setNaturalidadeCidade(naturalidadeCidade);
        pessoaFisica.setEstadoVital(estadoVital);
        pessoaFisica.setEstadoCivil(estadoCivil);
        pessoaFisica.setEndereco(endereco);
        return pessoaFisicaService.insert(pessoaFisica);
    }

    private Servidor inserirServidor(String matricula, String pispasep, String nomesocial, Escolaridade escolaridade, Long idPessoaFisica){
        Servidor servidor = new Servidor();
        servidor.setMatricula(matricula);
        servidor.setPispasep(pispasep);
        servidor.setNomeSocial(nomesocial);
        servidor.setEscolaridade(escolaridade);
        return servidorService.insert(servidor, idPessoaFisica);
    }

    private PessoaJuridica inserirPessoaJuridica(String nomeFantasia, String razaoSocial, String cnpj,
                                                 ClassificacaoTributaria classificacaoTributaria, Endereco endereco){
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNomeFantasia(nomeFantasia);
        pessoaJuridica.setRazaoSocial(razaoSocial);
        pessoaJuridica.setCnpj(cnpj);
        pessoaJuridica.setClassificacaoTributaria(classificacaoTributaria);
        pessoaJuridica.setEndereco(endereco);
        return pessoaJuridicaService.insert(pessoaJuridica);
    }

    private UnidadeOrganizacional inserirUnidadeOrganizacional(LocalDate dataInicioOperacao, String codIbgeCnae,
                                                               String codigoLegado, String sigla, Long idPessoaJuridica){
        UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
        unidadeOrganizacional.setDataInicioOperacao(dataInicioOperacao);
        unidadeOrganizacional.setCodIbgeCnae(codIbgeCnae);
        unidadeOrganizacional.setCodigoLegado(codigoLegado);
        unidadeOrganizacional.setSigla(sigla);
        return unidadeOrganizacionalService.insert(unidadeOrganizacional, idPessoaJuridica);
    }

    private Setor inserirSetor(String denominacao, String sigla, Long idSetorSuperior, Long idUnidadeOrganizacional, Endereco endereco){
        Setor setor = new Setor();
        setor.setDenominacao(denominacao);
        setor.setSigla(sigla);
        return setorService.insert(setor, idSetorSuperior,idUnidadeOrganizacional, endereco);
    }

    private Cbo inserirCbo(String denominacao, String codigo){
        Cbo cbo = new Cbo();
        cbo.setDenominacao(denominacao);
        cbo.setCodigo(codigo);
        return cboService.create(cbo);
    }

    private PCCR inserirPCCR(String denominacao, LocalDate dataVigencia, List<UnidadeOrganizacional> unidadesOrganizacionais,
                             LocalDate dataExtincao){
        PCCR pccr = new PCCR();
        pccr.setDenominacao(denominacao);
        pccr.setDataVigencia(dataVigencia);
        pccr.setUnidadesOrganizacionais(unidadesOrganizacionais);
        pccr.setDataExtincao(dataExtincao);
        return pccrService.create(pccr);
    }

    private Rubrica inserirRubrica(String denominacao, String sigla, String mneumonico){
        Rubrica rubrica = new Rubrica();
        rubrica.setDenominacao(denominacao);
        rubrica.setSigla(sigla);
        rubrica.setMnemonico(mneumonico);
        return rubricaService.create(rubrica);
    }

    private GrupoOcupacional inserirGrupoOcupacional(String denominacao, Escolaridade escolaridade, PCCR pccr, Rubrica rubrica,
                                                     int anosADTS){
        GrupoOcupacional grupoOcupacional = new GrupoOcupacional();
        grupoOcupacional.setDenominacao(denominacao);
        grupoOcupacional.setEscolaridade(escolaridade);
        grupoOcupacional.setPccr(pccr);
        grupoOcupacional.setRubrica(rubrica);
        grupoOcupacional.setAnosADTS(anosADTS);
        return grupoOcupacionalService.create(grupoOcupacional);
    }

    private Cargo inserirCargo(String denominacao, Escolaridade escolaridade, Cbo cbo, GrupoOcupacional grupoOcupacional, Integer vagas){
        Cargo cargo = new Cargo();
        cargo.setDenominacao(denominacao);
        cargo.setDataCriacao(LocalDate.now());
        cargo.setEscolaridade(escolaridade);
        cargo.setCbo(cbo);
        cargo.setGrupoOcupacional(grupoOcupacional);
        cargo.setVagas(vagas);
        return cargoService.create(cargo);
    }

    private Vinculo inserirVinculo(TipoVinculo tipoVinculo, SituacaoVinculo situacaoVinculo, RegimeJuridico regimeJuridico,
                                   Long setorId, Long pessoaJuridicaContratanteId, Long uoPaganteId, Long servidorId){
        Vinculo vinculo = new Vinculo();
        vinculo.setTipoVinculo(tipoVinculo);
        vinculo.setSituacao(situacaoVinculo);
        vinculo.setRegimeJuridico(regimeJuridico);
        return vinculoService.criarVinculo(vinculo, setorId, pessoaJuridicaContratanteId, uoPaganteId, servidorId);
    }

    private JornadaTrabalho inserirJornadaTrabalho(int horasJornadaSemanal, int plantoesMes){
        JornadaTrabalho jornadaTrabalho = new JornadaTrabalho();
        jornadaTrabalho.setHoras_jornada_semana(horasJornadaSemanal);
        jornadaTrabalho.setPlantoes_mes(plantoesMes);
        return jornadaTrabalhoService.create(jornadaTrabalho);
    }

    private NivelDesempenho inserirNivelDesempenho(String grau, String sigla, GrupoOcupacional grupoOcupacional){
        NivelDesempenho nivelDesempenho = new NivelDesempenho();
        nivelDesempenho.setGrau(grau);
        nivelDesempenho.setSigla(sigla);
        nivelDesempenho.setGrupoOcupacional(grupoOcupacional);
        return nivelDesempenhoService.create(nivelDesempenho);
    }

    private RemuneracaoBasica inserirRemuneracaoBasica(BigDecimal valueRemuneracaoBasica, Cargo cargo,
                                                       JornadaTrabalho jornadaTrabalho, NivelDesempenho nivelDesempenho) {
        RemuneracaoBasica remuneracaoBasica = new RemuneracaoBasica();
        remuneracaoBasica.setRemuneracaoBasica(valueRemuneracaoBasica);
        remuneracaoBasica.setCargo(cargo);
        remuneracaoBasica.setDataVigencia(LocalDate.now());
        remuneracaoBasica.setJornadaTrabalho(jornadaTrabalho);
        remuneracaoBasica.setNivelDesempenho(nivelDesempenho);
        return remuneracaoBasicaService.create(remuneracaoBasica);

    }

    private ProvimentoDeCargo inserirProvimentoDeCargo(RemuneracaoBasica remuneracaoBasica, LocalDate dataNomeacao,
                                                       LocalDate dataPosse, LocalDate dataExercicio, LocalDate dataVigencia,
                                                       String processoAdministrativo, RegimeJuridico regimeJuridico,
                                                       TipoVinculo tipoVinculo, String descricao, Long vinculoId,
                                                       Long pessoaJuridicaContratanteId, Long uoPaganteId){
        ProvimentoDeCargo provimentoDeCargo = new ProvimentoDeCargo();
        provimentoDeCargo.setDataNomeacao(dataNomeacao);
        provimentoDeCargo.setDataPosse(dataPosse);
        provimentoDeCargo.setDataExercicio(dataExercicio);
        provimentoDeCargo.setProcessoAdministrativo(processoAdministrativo);
        provimentoDeCargo.setRegimeJuridico(regimeJuridico);
        provimentoDeCargo.setTipoVinculo(tipoVinculo);
        provimentoDeCargo.setDataVigencia(dataVigencia);
        provimentoDeCargo.setDescricao(descricao);
        return provimentoService.proverCargo(provimentoDeCargo, vinculoId , pessoaJuridicaContratanteId, uoPaganteId, remuneracaoBasica.getId());
    }

    private TipoGratificacao inserirTipoGratificacao(Rubrica rubrica, String denominacao, LocalDate dataCriacao,
                                                     LocalDate dataExtincao, String mneumonico, BigDecimal valorRepresentacao,
                                                     BigDecimal valorVencimento){
        TipoGratificacao tipoGratificacao = new TipoGratificacao();
        tipoGratificacao.setRubrica(rubrica);
        tipoGratificacao.setDenominacao(denominacao);
        tipoGratificacao.setDataCriacao(dataCriacao);
        tipoGratificacao.setDataExtincao(dataExtincao);
        tipoGratificacao.setMneumonico(mneumonico);
        tipoGratificacao.setValorRepresentacao(valorRepresentacao);
        tipoGratificacao.setValorVencimento(valorVencimento);
        return tipoGratificacaoService.create(tipoGratificacao);

    }

    private Funcao inserirFuncao(Cbo cbo, String denominacao, Integer vagas, LocalDate dataCriacao, LocalDate dataExtincao,
                                 TipoGratificacao tipoGratificacao, UnidadeOrganizacional uo){
        Funcao funcao = new Funcao();
        funcao.setCbo(cbo);
        funcao.setDenominacao(denominacao);
        funcao.setVagas(vagas);
        funcao.setDataCriacao(dataCriacao);
        funcao.setDataExtincao(dataExtincao);
        funcao.setTipoGratificacao(tipoGratificacao);
        funcao.setUnidadeOrganizacional(uo);
        return funcaoService.create(funcao);
    }

    private ProvimentoDeFuncao inserirProvimentoDeFuncao(LocalDate dataNomeacao, LocalDate dataPosse, LocalDate dataExercicio,
                                                         String processoAdministrativo, RegimeJuridico regimeJuridico,
                                                         TipoVinculo tipoVinculo, LocalDate dataVigencia, String descricao,
                                                         Long vinculoId, Long pessoaJuridicaContratanteId, Long uoPaganteId,
                                                         Long funcaoId){
        ProvimentoDeFuncao provimentoDeFuncao = new ProvimentoDeFuncao();
        provimentoDeFuncao.setDataNomeacao(dataNomeacao);
        provimentoDeFuncao.setDataPosse(dataPosse);
        provimentoDeFuncao.setDataExercicio(dataExercicio);
        provimentoDeFuncao.setProcessoAdministrativo(processoAdministrativo);
        provimentoDeFuncao.setRegimeJuridico(regimeJuridico);
        provimentoDeFuncao.setTipoVinculo(tipoVinculo);
        provimentoDeFuncao.setDataVigencia(dataVigencia);
        provimentoDeFuncao.setDescricao(descricao);
        return provimentoService.proverFuncao(provimentoDeFuncao, vinculoId, pessoaJuridicaContratanteId, uoPaganteId, funcaoId);
    }

}
