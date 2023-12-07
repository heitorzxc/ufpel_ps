import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private List<Enderecos> memoria;

    public Memoria(String arquivo) {
        memoria = new ArrayList<>();
        carregarInstrucoes(arquivo);
    }

    private void carregarInstrucoes(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int endereco = 0;
            int posicaoIns = 0;

            while ((linha = br.readLine()) != null) {
                Instrucao instrucao = new Instrucao(linha.trim());

                Enderecos enderecoAtual = new Enderecos((endereco));
                enderecoAtual.setPalavra(instrucao);
                enderecoAtual.setIndice(posicaoIns);
                memoria.add(enderecoAtual);

                // Aumenta o endereço conforme o tamanho da instrução em bits
                endereco += instrucao.getNumBin().length();
                posicaoIns++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Enderecos> getMemoria() {
        return memoria;
    }

    public void imprimirMemoria() {
        for (Enderecos endereco : memoria) {
            System.out.println("Intrucao com endereço: " + endereco.getEndDeci());
            endereco.getPalavra().imprimirDetalhesInstrucao();
            System.out.println("--------");
        }
    }

}
