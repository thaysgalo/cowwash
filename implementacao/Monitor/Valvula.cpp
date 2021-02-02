#include "Valvula.h"

Valvula::Valvula(int pino, ServicoRede* servicoRede, const char* endereco)
{
        this->pino = pino;
        this->servicoRede = servicoRede;
        this->endereco = endereco;
}

void
Valvula::abrir()
{
        servicoRede->conectar();
        digitalWrite(pino, HIGH);
        delay(obterPeriodo(Estado::ABERTO) * 10000);
        servicoRede->desconectar();
}

void
Valvula::fechar()
{
        servicoRede->conectar();
        digitalWrite(pino, LOW);
        delay(obterPeriodo(Estado::FECHADO) * 10000);
        servicoRede->desconectar();
}

int
Valvula::obterPeriodo(Estado estado)
{
        return (estado == Estado::ABERTO ? servicoRede->obterDados(endereco, "aberto").toInt() : servicoRede->obterDados(endereco, "fechado").toInt());
}
