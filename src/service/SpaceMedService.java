package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class SpaceMedService {

    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<UnidadeRemota> unidades;
    private List<Triagem> triagens;
    private List<ConsultaOnline> consultas;
    private List<AlertaEmergencia> alertas;
    private List<HospitalParceiro> hospitais;

    public SpaceMedService() {
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.unidades = new ArrayList<>();
        this.triagens = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.alertas = new ArrayList<>();
        this.hospitais = new ArrayList<>();
    }

    public void cadastrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println("  Paciente '" + paciente.getNome() + "' cadastrado com sucesso!");
    }

    public void cadastrarMedico(Medico medico) {
        medicos.add(medico);
        System.out.println("  Médico '" + medico.getNome() + "' cadastrado com sucesso!");
    }

    public void cadastrarUnidade(UnidadeRemota unidade) {
        unidades.add(unidade);
        System.out.println("  Unidade remota '" + unidade.getNome() + "' cadastrada com sucesso!");
    }

    public void cadastrarHospital(HospitalParceiro hospital) {
        hospitais.add(hospital);
        System.out.println("  Hospital parceiro '" + hospital.getNome() + "' cadastrado com sucesso!");
    }

    public Paciente buscarPacientePorId(int id) {
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Medico buscarMedicoPorId(int id) {
        for (Medico m : medicos) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public void realizarTriagem(Triagem triagem) {
        triagens.add(triagem);
    }

    public void registrarConsulta(ConsultaOnline consulta) {
        consultas.add(consulta);
    }

    public void gerarAlerta(AlertaEmergencia alerta) {
        alertas.add(alerta);
        for (HospitalParceiro hospital : hospitais) {
            hospital.receberAlerta(alerta.getTipoAlerta() + " - Paciente: " + alerta.getPaciente().getNome());
        }
    }

    public void exibirProntuarioPaciente(int idPaciente) {
        Paciente paciente = buscarPacientePorId(idPaciente);
        if (paciente == null) {
            System.out.println("  Paciente com ID " + idPaciente + " não encontrado.");
            return;
        }
        paciente.getProntuario().exibirHistorico();
    }

    public void gerarRelatorioGeral() {
        System.out.println("============================================");
        System.out.println("       RELATÓRIO GERAL DO SISTEMA SPACEMED");
        System.out.println("============================================");
        System.out.println("  Total de Pacientes       : " + pacientes.size());
        System.out.println("  Total de Médicos          : " + medicos.size());
        System.out.println("  Total de Unidades Remotas : " + unidades.size());
        System.out.println("  Total de Hospitais Parceiros: " + hospitais.size());
        System.out.println("  Total de Triagens         : " + triagens.size());
        System.out.println("  Total de Consultas        : " + consultas.size());
        System.out.println("  Total de Alertas Emergenciais: " + alertas.size());
        System.out.println("============================================");
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<UnidadeRemota> getUnidades() {
        return unidades;
    }

    public List<Triagem> getTriagens() {
        return triagens;
    }

    public List<ConsultaOnline> getConsultas() {
        return consultas;
    }

    public List<AlertaEmergencia> getAlertas() {
        return alertas;
    }

    public List<HospitalParceiro> getHospitais() {
        return hospitais;
    }
}
