package model;

public class ConsultaOnline {

    private int id;
    private Paciente paciente;
    private Medico medico;
    private String data;
    private String status;
    private String observacao;

    public ConsultaOnline(int id, Paciente paciente, Medico medico, String data) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data = data;
        this.status = "AGENDADA";
        this.observacao = "";
    }

    public void iniciarConsulta() {
        this.status = "EM ANDAMENTO";
        System.out.println("  [CONSULTA] Consulta #" + id + " iniciada.");
        medico.atenderPaciente(paciente);
    }

    public void finalizarConsulta(String observacao) {
        this.observacao = observacao;
        this.status = "FINALIZADA";
        String registro = "[Consulta #" + id + " - " + data + "] Dr(a). " + medico.getNome()
                + " | Obs: " + observacao;
        paciente.adicionarRegistroProntuario(registro);
        System.out.println("  [CONSULTA] Consulta #" + id + " finalizada com observação.");
    }

    public void finalizarConsulta(String observacao, String prescricao) {
        this.observacao = observacao;
        this.status = "FINALIZADA";
        String registro = "[Consulta #" + id + " - " + data + "] Dr(a). " + medico.getNome()
                + " | Obs: " + observacao + " | Prescrição: " + prescricao;
        paciente.adicionarRegistroProntuario(registro);
        System.out.println("  [CONSULTA] Consulta #" + id + " finalizada com observação e prescrição.");
    }

    public void exibirResumo() {
        System.out.println("==============================");
        System.out.println("  CONSULTA ONLINE");
        System.out.println("==============================");
        System.out.println("  ID       : " + id);
        System.out.println("  Paciente : " + paciente.getNome());
        System.out.println("  Médico   : " + medico.getNome() + " (" + medico.getEspecialidade() + ")");
        System.out.println("  Data     : " + data);
        System.out.println("  Status   : " + status);
        System.out.println("  Observação: " + (observacao.isEmpty() ? "-" : observacao));
        System.out.println("==============================");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
