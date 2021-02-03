#include "Valvula.h"

Valvula::Valvula(int pino, ServicoRede* servicoRede, const char* endereco) : pino(pino), servicoRede(servicoRede), endereco(endereco)
{
}

void
Valvula::abrir(void)
{
        executar(obterPeriodo(Estado::ABERTO), HIGH);
}

void
Valvula::fechar(void)
{
        executar(obterPeriodo(Estado::FECHADO), LOW);
}

int
Valvula::obterPeriodo(Estado estado)
{
        int periodo;
        
        servicoRede->conectar();
        periodo = estado == Estado::ABERTO ? servicoRede->obterDados(endereco, "aberto").toInt() : servicoRede->obterDados(endereco, "fechado").toInt();
        servicoRede->desconectar();
        
        return (periodo);
}

void
Valvula::executar(int periodo, uint8_t tensaoPino)
{
        digitalWrite(pino, tensaoPino);
        delay(periodo * 10000);
}
