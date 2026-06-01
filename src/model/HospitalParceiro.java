package model;

public class HospitalParceiro {

    private int id;
    private String nome;
    private String cidade;
    private String especialidade;

    public HospitalParceiro(int id, String nome, String cidade, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.especialidade = especialidade;
    }

    public void receberAlerta(String mensagem) {
        System.out.println("  [HOSPITAL] " + nome + " (" + cidade + ") recebeu alerta: " + mensagem);
    }

    public void exibirDados() {
        System.out.println("==============================");
        System.out.println("  HOSPITAL PARCEIRO");
        System.out.println("==============================");
        System.out.println("  ID          : " + id);
        System.out.println("  Nome        : " + nome);
        System.out.println("  Cidade      : " + cidade);
        System.out.println("  Especialidade: " + especialidade);
        System.out.println("==============================");
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
