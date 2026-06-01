package model;

public class Paciente {

    private int id;
    private String nome;
    private int idade;
    private String comunidade;
    private String cpf;
    private ProntuarioDigital prontuario;

    public Paciente(int id, String nome, int idade, String comunidade, String cpf) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.comunidade = comunidade;
        this.cpf = cpf;
        this.prontuario = new ProntuarioDigital(this);
    }

    public void exibirDados() {
        System.out.println("==============================");
        System.out.println("  DADOS DO PACIENTE");
        System.out.println("==============================");
        System.out.println("  ID        : " + id);
        System.out.println("  Nome      : " + nome);
        System.out.println("  Idade     : " + idade + " anos");
        System.out.println("  Comunidade: " + comunidade);
        System.out.println("  CPF       : " + cpf);
        System.out.println("  Grupo de Risco: " + (isGrupoRisco() ? "SIM" : "NÃO"));
        System.out.println("==============================");
    }

    public void adicionarRegistroProntuario(String registro) {
        prontuario.adicionarRegistro(registro);
    }

    public boolean isGrupoRisco() {
        return idade >= 60;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getComunidade() {
        return comunidade;
    }

    public void setComunidade(String comunidade) {
        this.comunidade = comunidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ProntuarioDigital getProntuario() {
        return prontuario;
    }

    public void setProntuario(ProntuarioDigital prontuario) {
        this.prontuario = prontuario;
    }
}
