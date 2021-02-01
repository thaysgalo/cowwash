#ifndef SERVICOREDE_H
#define SERVICOREDE_H

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

class ServicoRede
{
public:
        ServicoRede(const char*, const char*, const char*, int);
        
        bool conectar(void);
        void desconectar(void);
        String obterDados(const char*, const char*);

private:
        const char* nome;
        const char* senha;
        const char* endereco;
        int porta;
        
        void criarConexao(void);
        bool estahConectado(void);
};

#endif
