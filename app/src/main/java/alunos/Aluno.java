package alunos;

public class Aluno {
    String nome;
    int foto;
    String descrição;

    public Aluno(String nome, int foto, String descrição) {
        this.nome = nome;
        this.foto = foto;
        this.descrição = descrição;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFoto() {
        return foto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrição() {
        return descrição;
    }
}
