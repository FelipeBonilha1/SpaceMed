# SpaceMed - Script de compilacao e execucao (Windows PowerShell)
Write-Host "Compilando SpaceMed..."

$src = "src"
$out = "out"

javac -encoding UTF-8 -d $out `
  $src/app/Main.java `
  $src/abstracts/ProfissionalSaude.java `
  $src/interfaces/Conectavel.java `
  $src/interfaces/Notificavel.java `
  $src/model/ProntuarioDigital.java `
  $src/model/Paciente.java `
  $src/model/Medico.java `
  $src/model/ConexaoSatelital.java `
  $src/model/UnidadeRemota.java `
  $src/model/Triagem.java `
  $src/model/ConsultaOnline.java `
  $src/model/AlertaEmergencia.java `
  $src/model/HospitalParceiro.java `
  $src/model/ExameBasico.java `
  $src/service/TriagemService.java `
  $src/service/SpaceMedService.java `
  $src/util/Validador.java

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilacao concluida com sucesso!"
    Write-Host "Iniciando SpaceMed..."
    java -cp $out app.Main
} else {
    Write-Host "Erro na compilacao. Verifique os arquivos."
}
