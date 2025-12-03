package unioeste.apoio.viacep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConexaoViaCEP {
    public String obterEnderecoViaCepAPI(String urlRequisicao) throws Exception {
        URL url = new URL(urlRequisicao);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("GET");

        if(conexao.getResponseCode() != HttpURLConnection.HTTP_OK){
            throw new RuntimeException("Não foi possível se conectar a API do ViaCEP");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
            StringBuilder conteudoBuilder = new StringBuilder();
            String conteudo;

            while ((conteudo = reader.readLine()) != null) {
                conteudoBuilder.append(conteudo);
            }

            return conteudoBuilder.toString();
        }
    }
}
