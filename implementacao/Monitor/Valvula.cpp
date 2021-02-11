#include "Valvula.h"

Valvula::Valvula(int pino, ServicoRede* servicoRede, const char* endereco, const char* atributoConsulta) : pino(pino), servicoRede(servicoRede), endereco(endereco), atributoConsulta(atributoConsulta)
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
        periodo = servicoRede->obterDados(endereco, atributoConsulta, (int)estado).toInt();
        servicoRede->desconectar();
        
        return (periodo);
}

void
Valvula::executar(int periodo, uint8_t tensaoPino)
{
        digitalWrite(pino, tensaoPino);
        delay(periodo * MINUTO);
}
