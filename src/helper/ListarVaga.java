package helper;

public class ListarVaga{
    private int idVaga;
    private String nome;

    public ListarVaga(int idVaga, String nome) {
        this.idVaga = idVaga;
        this.nome = nome;
    }

    public ListarVaga() {}

	public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}