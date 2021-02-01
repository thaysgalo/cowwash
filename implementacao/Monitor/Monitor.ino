#include "ServicoRede.h"
#include "Valvula.h"

ServicoRede* servicoRede = new ServicoRede("CAYO", "peta1234", "192.168.0.17", 8080);
Valvula valvula(5, servicoRede, "/valvula/consulta-periodo");

void setup()
{
        // USAR A LINHA ABAIXO SÓ QUANDO FOR UTILIZAR O SERIAL MONITOR.
        //Serial.begin(115200);
}

void loop() 
{
          valvula.abrir();
          valvula.fechar();
}
