package abstracts;

import model.Paciente;

public abstract class ProfissionalSaude {

    private int id;
    private String nome;
    private String registroProfissional;

    public ProfissionalSaude(int id, String nome, String registroProfissional) {
        this.id = id;
        this.nome = nome;
        this.registroProfissional = registroProfissional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Registro Profissional: " + registroProfissional);
    }

    public abstract void atenderPaciente(Paciente paciente);
}
