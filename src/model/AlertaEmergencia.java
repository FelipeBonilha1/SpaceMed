package model;

import interfaces.Notificavel;

public class AlertaEmergencia implements Notificavel {

    private int id;
    private Paciente paciente;
    private String tipoAlerta;
    private String mensagem;
    private String nivelUrgencia;

    public AlertaEmergencia(int id, Paciente paciente, String tipoAlerta, String nivelUrgencia) {
        this.id = id;
        this.paciente = paciente;
        this.tipoAlerta = tipoAlerta;
        this.nivelUrgencia = nivelUrgencia;
        this.mensagem = "";
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        this.mensagem = mensagem;
        System.out.println("==============================");
        System.out.println("  !!! ALERTA DE EMERGÊNCIA !!!");
        System.out.println("==============================");
        System.out.println("  ID Alerta     : " + id);
        System.out.println("  Paciente      : " + paciente.getNome());
        System.out.println("  Tipo de Alerta: " + tipoAlerta);
        System.out.println("  Nível Urgência: " + nivelUrgencia);
        System.out.println("  Mensagem      : " + mensagem);
        System.out.println("  >> Notificação enviada aos hospitais parceiros! <<");
        System.out.println("==============================");
        paciente.adicionarRegistroProntuario("[ALERTA #" + id + "] " + tipoAlerta + " - " + mensagem);
    }

    public void exibirAlerta() {
        System.out.println("==============================");
        System.out.println("  ALERTA DE EMERGÊNCIA #" + id);
        System.out.println("==============================");
        System.out.println("  Paciente      : " + paciente.getNome());
        System.out.println("  Tipo de Alerta: " + tipoAlerta);
        System.out.println("  Nível Urgência: " + nivelUrgencia);
        System.out.println("  Mensagem      : " + (mensagem.isEmpty() ? "-" : mensagem));
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

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNivelUrgencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(String nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }
}
