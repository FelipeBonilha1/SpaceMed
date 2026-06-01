package app;

import model.*;
import service.SpaceMedService;
import service.TriagemService;
import util.Validador;

import java.util.Scanner;

public class Main {

    private static final SpaceMedService servico = new SpaceMedService();
    private static final TriagemService triagemService = new TriagemService();
    private static final Scanner scanner = new Scanner(System.in);

    private static int proximoIdPaciente = 1;
    private static int proximoIdMedico = 1;
    private static int proximoIdUnidade = 1;
    private static int proximoIdTriagem = 1;
    private static int proximoIdConsulta = 1;
    private static int proximoIdAlerta = 1;
    private static int proximoIdHospital = 10;

    public static void main(String[] args) {
        inicializarDados();
        exibirBoasVindas();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            processarOpcao(opcao);
        }

        System.out.println("\n  Encerrando o SpaceMed. Até logo!");
        scanner.close();
    }

    private static void inicializarDados() {
        HospitalParceiro hospital1 = new HospitalParceiro(proximoIdHospital++,
                "Hospital das Clínicas de São Paulo", "São Paulo", "Geral e Emergência");
        HospitalParceiro hospital2 = new HospitalParceiro(proximoIdHospital++,
                "Hospital Universitário de Manaus", "Manaus", "Medicina Tropical");
        servico.cadastrarHospital(hospital1);
        servico.cadastrarHospital(hospital2);
        System.out.println("  Hospitais parceiros pré-cadastrados com sucesso.");
        System.out.println();
    }

    private static void exibirBoasVindas() {
        System.out.println("============================================");
        System.out.println("   Bem-vindo ao SpaceMed");
        System.out.println("   Telemedicina via Satélite para Regiões Isoladas");
        System.out.println("   ODS 9 - Indústria, Inovação e Infraestrutura");
        System.out.println("============================================");
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== SPACEMED =====");
        System.out.println("1 - Cadastrar paciente");
        System.out.println("2 - Cadastrar médico parceiro");
        System.out.println("3 - Cadastrar unidade remota");
        System.out.println("4 - Realizar triagem");
        System.out.println("5 - Gerar alerta de emergência");
        System.out.println("6 - Registrar consulta online");
        System.out.println("7 - Exibir prontuário de paciente");
        System.out.println("8 - Gerar relatório geral do sistema");
        System.out.println("0 - Sair");
        System.out.println("====================");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarPaciente(); break;
            case 2: cadastrarMedico(); break;
            case 3: cadastrarUnidadeRemota(); break;
            case 4: realizarTriagem(); break;
            case 5: gerarAlertaEmergencia(); break;
            case 6: registrarConsultaOnline(); break;
            case 7: exibirProntuario(); break;
            case 8: servico.gerarRelatorioGeral(); break;
            case 0: break;
            default: System.out.println("  Opção inválida. Tente novamente.");
        }
    }

    private static void cadastrarPaciente() {
        System.out.println("\n--- CADASTRO DE PACIENTE ---");

        String nome = lerTextoObrigatorio("Nome do paciente: ");
        int idade = lerInteiroValidado("Idade: ", 1, 120);
        String comunidade = lerTextoObrigatorio("Comunidade/Localidade: ");
        String cpf = lerTextoObrigatorio("CPF: ");

        Paciente paciente = new Paciente(proximoIdPaciente++, nome, idade, comunidade, cpf);
        servico.cadastrarPaciente(paciente);
        paciente.exibirDados();
    }

    private static void cadastrarMedico() {
        System.out.println("\n--- CADASTRO DE MÉDICO PARCEIRO ---");

        String nome = lerTextoObrigatorio("Nome do médico: ");
        String crm = lerTextoObrigatorio("CRM: ");
        String especialidade = lerTextoObrigatorio("Especialidade: ");
        String hospital = lerTextoObrigatorio("Hospital vinculado: ");

        Medico medico = new Medico(proximoIdMedico++, nome, crm, especialidade, hospital);
        servico.cadastrarMedico(medico);
        medico.exibirDados();
    }

    private static void cadastrarUnidadeRemota() {
        System.out.println("\n--- CADASTRO DE UNIDADE REMOTA ---");

        String nome = lerTextoObrigatorio("Nome da unidade: ");
        String localizacao = lerTextoObrigatorio("Localização (ex: AM-470, km 32): ");
        String tipoComunidade = lerTextoObrigatorio("Tipo de comunidade (ex: Indígena, Ribeirinha, Rural): ");
        String satelite = lerTextoObrigatorio("Nome do satélite: ");
        double velocidade = lerDouble("Velocidade da conexão (Mbps): ");

        ConexaoSatelital conexao = new ConexaoSatelital(satelite, velocidade);
        conexao.conectar();

        UnidadeRemota unidade = new UnidadeRemota(proximoIdUnidade++, nome, localizacao, tipoComunidade, conexao);
        servico.cadastrarUnidade(unidade);
        unidade.exibirUnidade();
    }

    private static void realizarTriagem() {
        System.out.println("\n--- TRIAGEM DO PACIENTE ---");

        if (servico.getPacientes().isEmpty()) {
            System.out.println("  Nenhum paciente cadastrado. Cadastre um paciente primeiro.");
            return;
        }

        listarPacientes();
        int idPaciente = lerInteiro("ID do paciente: ");
        Paciente paciente = servico.buscarPacientePorId(idPaciente);
        if (paciente == null) {
            System.out.println("  Paciente não encontrado.");
            return;
        }

        String sintomas = lerTextoObrigatorio("Descreva os sintomas: ");
        int nivelDor = lerInteiroValidado("Nível de dor (0 a 10): ", 0, 10);
        boolean febre = lerSimNao("Está com febre? (s/n): ");
        boolean faltaAr = lerSimNao("Está com falta de ar? (s/n): ");

        String prioridade = triagemService.calcularPrioridade(faltaAr, febre, nivelDor);

        Triagem triagem = new Triagem(proximoIdTriagem++, paciente, sintomas, nivelDor, febre, faltaAr);
        triagem.classificarPrioridade();
        servico.realizarTriagem(triagem);
        triagem.exibirResultado();

        paciente.adicionarRegistroProntuario("[Triagem #" + triagem.getId() + "] Prioridade: "
                + prioridade + " | Sintomas: " + sintomas);

        if ("EMERGÊNCIA".equals(prioridade)) {
            boolean gerarAlerta = lerSimNao("Prioridade EMERGÊNCIA detectada! Deseja gerar alerta? (s/n): ");
            if (gerarAlerta) {
                criarAlertaParaPaciente(paciente, "Triagem com prioridade EMERGÊNCIA");
            }
        }
    }

    private static void gerarAlertaEmergencia() {
        System.out.println("\n--- GERAR ALERTA DE EMERGÊNCIA ---");

        if (servico.getPacientes().isEmpty()) {
            System.out.println("  Nenhum paciente cadastrado. Cadastre um paciente primeiro.");
            return;
        }

        listarPacientes();
        int idPaciente = lerInteiro("ID do paciente: ");
        Paciente paciente = servico.buscarPacientePorId(idPaciente);
        if (paciente == null) {
            System.out.println("  Paciente não encontrado.");
            return;
        }

        String tipoAlerta = lerTextoObrigatorio("Tipo de alerta (ex: Parada Cardíaca, Trauma, Crise Respiratória): ");
        criarAlertaParaPaciente(paciente, tipoAlerta);
    }

    private static void criarAlertaParaPaciente(Paciente paciente, String tipoAlerta) {
        AlertaEmergencia alerta = new AlertaEmergencia(proximoIdAlerta++, paciente, tipoAlerta, "CRÍTICO");
        String mensagem = "Paciente " + paciente.getNome() + " da comunidade " + paciente.getComunidade()
                + " necessita de atendimento urgente! Tipo: " + tipoAlerta;

        servico.gerarAlerta(alerta);
        alerta.enviarNotificacao(mensagem);
    }

    private static void registrarConsultaOnline() {
        System.out.println("\n--- REGISTRAR CONSULTA ONLINE ---");

        if (servico.getPacientes().isEmpty()) {
            System.out.println("  Nenhum paciente cadastrado. Cadastre um paciente primeiro.");
            return;
        }
        if (servico.getMedicos().isEmpty()) {
            System.out.println("  Nenhum médico cadastrado. Cadastre um médico primeiro.");
            return;
        }

        listarPacientes();
        int idPaciente = lerInteiro("ID do paciente: ");
        Paciente paciente = servico.buscarPacientePorId(idPaciente);
        if (paciente == null) {
            System.out.println("  Paciente não encontrado.");
            return;
        }

        listarMedicos();
        int idMedico = lerInteiro("ID do médico: ");
        Medico medico = servico.buscarMedicoPorId(idMedico);
        if (medico == null) {
            System.out.println("  Médico não encontrado.");
            return;
        }

        String data = lerTextoObrigatorio("Data da consulta (ex: 01/06/2026): ");

        ConsultaOnline consulta = new ConsultaOnline(proximoIdConsulta++, paciente, medico, data);
        consulta.iniciarConsulta();

        String observacao = lerTextoObrigatorio("Observações da consulta: ");

        boolean adicionarPrescricao = lerSimNao("Deseja adicionar prescrição? (s/n): ");
        if (adicionarPrescricao) {
            String prescricao = lerTextoObrigatorio("Prescrição médica: ");
            consulta.finalizarConsulta(observacao, prescricao);
        } else {
            consulta.finalizarConsulta(observacao);
        }

        servico.registrarConsulta(consulta);
        consulta.exibirResumo();
    }

    private static void exibirProntuario() {
        System.out.println("\n--- PRONTUÁRIO DIGITAL ---");

        if (servico.getPacientes().isEmpty()) {
            System.out.println("  Nenhum paciente cadastrado.");
            return;
        }

        listarPacientes();
        int idPaciente = lerInteiro("ID do paciente: ");
        servico.exibirProntuarioPaciente(idPaciente);
    }

    private static void listarPacientes() {
        System.out.println("  Pacientes cadastrados:");
        for (Paciente p : servico.getPacientes()) {
            System.out.println("    [" + p.getId() + "] " + p.getNome() + " - " + p.getComunidade());
        }
    }

    private static void listarMedicos() {
        System.out.println("  Médicos cadastrados:");
        for (Medico m : servico.getMedicos()) {
            System.out.println("    [" + m.getId() + "] " + m.getNome() + " - " + m.getEspecialidade());
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static int lerInteiroValidado(String mensagem, int min, int max) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("  Valor fora do intervalo permitido (" + min + " a " + max + ").");
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Digite um número.");
            }
        }
    }

    private static String lerTextoObrigatorio(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine();
            if (Validador.validarTexto(texto)) {
                return texto.trim();
            }
            System.out.println("  Campo obrigatório. Por favor, informe um valor.");
        }
    }

    private static boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s") || resposta.equals("sim")) return true;
            if (resposta.equals("n") || resposta.equals("não") || resposta.equals("nao")) return false;
            System.out.println("  Digite 's' para sim ou 'n' para não.");
        }
    }
}
