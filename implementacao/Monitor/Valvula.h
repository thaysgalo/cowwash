#ifndef VALVULA_H
#define VALVULA_H

#include <Arduino.h>
#include "ServicoRede.h"

#define MINUTO 60000

enum Estado { ABERTO = 1, FECHADO };

class Valvula
{
public:
        Valvula(int, ServicoRede*, const char*, const char*);
        void abrir(void);
        void fechar(void);
private:
        int pino;
        ServicoRede* servicoRede;
        const char* endereco;
        const char* atributoConsulta;

        int obterPeriodo(Estado);
        void executar(int periodo, uint8_t tensaoPino);
};

#endif
