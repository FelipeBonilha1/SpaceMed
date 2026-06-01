package model;

import java.util.ArrayList;
import java.util.List;

public class ProntuarioDigital {

    private Paciente paciente;
    private List<String> registros;

    public ProntuarioDigital(Paciente paciente) {
        this.paciente = paciente;
        this.registros = new ArrayList<>();
    }

    public void adicionarRegistro(String registro) {
        registros.add(registro);
    }

    public void exibirHistorico() {
        System.out.println("==============================");
        System.out.println("  PRONTUÁRIO DIGITAL");
        System.out.println("  Paciente: " + paciente.getNome());
        System.out.println("==============================");
        if (registros.isEmpty()) {
            System.out.println("  Nenhum registro encontrado.");
        } else {
            for (int i = 0; i < registros.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + registros.get(i));
            }
        }
        System.out.println("==============================");
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<String> getRegistros() {
        return registros;
    }
}
