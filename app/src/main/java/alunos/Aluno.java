package alunos;

public class Aluno {
    private String nome;
    private int foto;
    private String descricao;

    public Aluno(String nome, int foto, String descricao) {
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
    }

    public int getFoto() {
        return foto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
