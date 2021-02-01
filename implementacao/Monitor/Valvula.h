#ifndef VALVULA_H
#define VALVULA_H

#include <Arduino.h>
#include "ServicoRede.h"

enum Estado{ABERTO, FECHADO};

class Valvula
{
public:
        Valvula(int, ServicoRede*, const char*);
        void abrir();
        void fechar();
private:
        int pino;
        ServicoRede* servicoRede;
        const char* endereco;

        int obterPeriodo(Estado);
};

#endif
