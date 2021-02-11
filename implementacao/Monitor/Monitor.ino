#include "ServicoRede.h"
#include "Valvula.h"

ServicoRede servicoRede("CAYO", "peta1234", "192.168.0.17", 8080);
Valvula valvula(5, &servicoRede, "/valvula/consulta-periodo", "estado");

void setup()
{
}

void loop() 
{
        valvula.abrir();
        valvula.fechar();
}
