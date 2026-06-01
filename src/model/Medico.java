package model;

import abstracts.ProfissionalSaude;

public class Medico extends ProfissionalSaude {

    private String especialidade;
    private String hospitalVinculado;

    public Medico(int id, String nome, String registroProfissional, String especialidade, String hospitalVinculado) {
        super(id, nome, registroProfissional);
        this.especialidade = especialidade;
        this.hospitalVinculado = hospitalVinculado;
    }

    @Override
    public void atenderPaciente(Paciente paciente) {
        System.out.println("==============================");
        System.out.println("  ATENDIMENTO INICIADO");
        System.out.println("  Médico  : " + getNome() + " (" + especialidade + ")");
        System.out.println("  Paciente: " + paciente.getNome());
        if (paciente.isGrupoRisco()) {
            System.out.println("  ATENÇÃO: Paciente pertence ao grupo de risco!");
        }
        System.out.println("==============================");
        paciente.adicionarRegistroProntuario("Atendimento pelo médico " + getNome() + " - Especialidade: " + especialidade);
    }

    @Override
    public void exibirDados() {
        System.out.println("==============================");
        System.out.println("  DADOS DO MÉDICO");
        System.out.println("==============================");
        super.exibirDados();
        System.out.println("Especialidade     : " + especialidade);
        System.out.println("Hospital Vinculado: " + hospitalVinculado);
        System.out.println("==============================");
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getHospitalVinculado() {
        return hospitalVinculado;
    }

    public void setHospitalVinculado(String hospitalVinculado) {
        this.hospitalVinculado = hospitalVinculado;
    }
}
