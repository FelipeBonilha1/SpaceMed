#!/bin/bash
echo "Compilando SpaceMed..."

javac -encoding UTF-8 -d out \
  src/app/Main.java \
  src/abstracts/ProfissionalSaude.java \
  src/interfaces/Conectavel.java \
  src/interfaces/Notificavel.java \
  src/model/ProntuarioDigital.java \
  src/model/Paciente.java \
  src/model/Medico.java \
  src/model/ConexaoSatelital.java \
  src/model/UnidadeRemota.java \
  src/model/Triagem.java \
  src/model/ConsultaOnline.java \
  src/model/AlertaEmergencia.java \
  src/model/HospitalParceiro.java \
  src/model/ExameBasico.java \
  src/service/TriagemService.java \
  src/service/SpaceMedService.java \
  src/util/Validador.java

if [ $? -eq 0 ]; then
  echo "Compilacao concluida com sucesso!"
  echo "Iniciando SpaceMed..."
  java -cp out app.Main
else
  echo "Erro na compilacao. Verifique os arquivos."
fi
