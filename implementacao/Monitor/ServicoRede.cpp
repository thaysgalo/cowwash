#include "ServicoRede.h"

ServicoRede::ServicoRede(const char* nome, const char* senha, const char* endereco, int porta) : nome(nome), senha(senha), endereco(endereco), porta(porta)
{
}

bool
ServicoRede::conectar(void)
{
        if (estahConectado())
                return (true);

        criarConexao();
                
        while (!estahConectado())
                // INTERESSANTE ACIONAR UM LED VERMELHO, INFORMANDO UM ALERTA PELA FALTA DE CONECTIVIDADE COM A REDE.
                delay(500);

        // INTERESSANTE ACIONAR UM LED VERDE, INFORMANDO UMA MENSAGEM DE FORNECIMENTO NORMAL DE CONECTIVIDADE COM A REDE.
        return (true);
}

String
ServicoRede::obterDados(const char* arquivo, const char* atributoConsulta, int estado)
{
        HTTPClient clienteHTTP;
        WiFiClient clienteWiFi;
        String mensagem;
        int codigoRetornoHTTP;
        
        clienteHTTP.begin(clienteWiFi, (String)"http://" + endereco + (String)":" + porta + (String)"/" + arquivo + (String)"?" + atributoConsulta + (String)"=" + estado);
        codigoRetornoHTTP = clienteHTTP.GET();
        mensagem = (codigoRetornoHTTP < 0 || codigoRetornoHTTP != HTTP_CODE_OK) ? "1" : clienteHTTP.getString();
        clienteWiFi.stop();
        clienteHTTP.end();

        return (mensagem);
}

void
ServicoRede::desconectar(void)
{
        WiFi.disconnect(true);
}

void
ServicoRede::criarConexao(void)
{
        WiFi.mode(WIFI_STA);
        WiFi.begin(nome, senha);
}

bool
ServicoRede::estahConectado(void)
{
        return (WiFi.status() == WL_CONNECTED);
}
