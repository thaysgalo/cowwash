#ifndef SERVICOREDE_H
#define SERVICOREDE_H

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

class ServicoRede
{
public:
        ServicoRede(const char*, const char*, const char*, const int);
        
        bool conectar(void);
        void desconectar(void);
        String obterDados(const char*, const char*, const int);

private:
        const char* nome;
        const char* senha;
        const char* endereco;
        const int porta;
        
        void criarConexao(void);
        bool estahConectado(void);
};

#endif
