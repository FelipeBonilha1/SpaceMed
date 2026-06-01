package model;

public class Triagem {

    private int id;
    private Paciente paciente;
    private String sintomas;
    private int nivelDor;
    private boolean febre;
    private boolean faltaAr;
    private String prioridade;

    public Triagem(int id, Paciente paciente, String sintomas, int nivelDor, boolean febre, boolean faltaAr) {
        this.id = id;
        this.paciente = paciente;
        this.sintomas = sintomas;
        this.nivelDor = nivelDor;
        this.febre = febre;
        this.faltaAr = faltaAr;
        this.prioridade = "NÃO CLASSIFICADA";
    }

    public void classificarPrioridade() {
        if (faltaAr || nivelDor >= 9) {
            prioridade = "EMERGÊNCIA";
        } else if (nivelDor >= 7 || febre) {
            prioridade = "ALTA";
        } else if (nivelDor >= 4) {
            prioridade = "MÉDIA";
        } else {
            prioridade = "BAIXA";
        }
    }

    public void exibirResultado() {
        System.out.println("==============================");
        System.out.println("  RESULTADO DA TRIAGEM");
        System.out.println("==============================");
        System.out.println("  ID Triagem : " + id);
        System.out.println("  Paciente   : " + paciente.getNome());
        System.out.println("  Sintomas   : " + sintomas);
        System.out.println("  Nível de Dor: " + nivelDor + "/10");
        System.out.println("  Febre      : " + (febre ? "SIM" : "NÃO"));
        System.out.println("  Falta de Ar: " + (faltaAr ? "SIM" : "NÃO"));
        System.out.println("------------------------------");
        System.out.println("  PRIORIDADE : " + prioridade);
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

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public int getNivelDor() {
        return nivelDor;
    }

    public void setNivelDor(int nivelDor) {
        this.nivelDor = nivelDor;
    }

    public boolean isFebre() {
        return febre;
    }

    public void setFebre(boolean febre) {
        this.febre = febre;
    }

    public boolean isFaltaAr() {
        return faltaAr;
    }

    public void setFaltaAr(boolean faltaAr) {
        this.faltaAr = faltaAr;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
