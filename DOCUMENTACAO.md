# DOCUMENTACAO — SpaceMed

---

## 1. Problema Escolhido

O acesso à saúde em regiões remotas do Brasil é um desafio histórico e estrutural. Comunidades indígenas na Amazônia, ribeirinhos no Pantanal, assentamentos rurais no sertão e outras populações isoladas vivem sem acesso regular a médicos, hospitais ou até mesmo postos de saúde. A ausência de infraestrutura de telecomunicação agrava ainda mais esse cenário: sem internet, é impossível utilizar serviços de telemedicina convencionais.

O problema escolhido é a **falta de acesso a atendimento médico em regiões geograficamente isoladas**, onde a distância dos centros urbanos e a inexistência de infraestrutura de conectividade impedem que pessoas recebam diagnósticos, tratamentos e orientações médicas em tempo hábil — o que frequentemente resulta em agravamento de doenças e mortes evitáveis.

---

## 2. Objetivo da Solução

A SpaceMed tem como objetivo simular uma plataforma de **telemedicina via satélite** que permita:

- Conectar unidades de saúde remotas a médicos em centros urbanos por meio de satélites de comunicação;
- Realizar triagem remota de pacientes para identificar e priorizar casos urgentes;
- Manter um prontuário digital de cada paciente, acessível de qualquer unidade conectada;
- Enviar alertas de emergência automáticos a hospitais parceiros quando casos críticos são identificados;
- Registrar consultas online com anotações e prescrições médicas;
- Oferecer uma estrutura escalável para futuras integrações com dispositivos IoT e APIs de saúde.

---

## 3. Justificativa da Relação com o ODS 9

O **Objetivo de Desenvolvimento Sustentável 9 — Indústria, Inovação e Infraestrutura** estabelece metas para construir infraestruturas resilientes, promover a industrialização inclusiva e fomentar a inovação, com especial atenção ao acesso de populações vulneráveis.

A SpaceMed se alinha ao ODS 9 de forma direta:

| Meta do ODS 9 | Como a SpaceMed atende |
|---|---|
| Ampliar o acesso a tecnologias de informação e comunicação | Simula conectividade via satélite para regiões sem internet |
| Aumentar o acesso de países menos desenvolvidos à TIC | Modela unidades remotas em comunidades isoladas |
| Fomentar a inovação e aumentar o número de trabalhadores em setores de tecnologia | Demonstra aplicação de tecnologia para resolver problema social crítico |
| Desenvolver infraestruturas de qualidade, confiáveis e resilientes | A classe `ConexaoSatelital` representa essa infraestrutura alternativa |

---

## 4. Explicação da Modelagem

O sistema foi modelado seguindo princípios de **Domain Driven Design** aplicados a Java puro, organizando as responsabilidades em camadas bem definidas:

### Camada de Modelo (`model`)
Contém todas as entidades do domínio da aplicação. Cada classe representa um conceito real do problema:
- `Paciente` — a pessoa que recebe atendimento;
- `Medico` — o profissional de saúde que realiza consultas remotas;
- `Triagem` — o processo de avaliação inicial dos sintomas;
- `ConsultaOnline` — o atendimento médico realizado via satélite;
- `ProntuarioDigital` — o histórico médico do paciente;
- `AlertaEmergencia` — a notificação enviada em casos críticos;
- `ConexaoSatelital` — a infraestrutura de conectividade;
- `UnidadeRemota` — a unidade de saúde localizada na região isolada;
- `HospitalParceiro` — o hospital em centro urbano que recebe alertas;
- `ExameBasico` — registro de exames realizados na unidade remota.

### Camada Abstrata (`abstracts`)
Define o contrato e estrutura comum para profissionais de saúde, permitindo extensão futura para outros tipos (enfermeiros, técnicos, etc.).

### Camada de Interfaces (`interfaces`)
Define contratos de comportamento desacoplados da hierarquia de classes:
- `Conectavel` — para objetos que simulam conexão;
- `Notificavel` — para objetos que enviam notificações.

### Camada de Serviços (`service`)
Centraliza a lógica de negócio e o armazenamento em memória, mantendo a `Main` limpa e focada apenas na interação com o usuário.

### Camada Utilitária (`util`)
Fornece métodos de validação reutilizáveis, evitando duplicação de lógica de validação espalhada pelo código.

---

## 5. Conceitos de Java Utilizados

### Classe Abstrata
`ProfissionalSaude` é uma classe abstrata que centraliza atributos e comportamentos comuns a qualquer profissional de saúde. O método abstrato `atenderPaciente(Paciente)` obriga subclasses a implementar o atendimento, garantindo o contrato.

### Herança
`Medico extends ProfissionalSaude` demonstra herança real com reutilização de código (construtor via `super()`) e especialização (atributos `especialidade` e `hospitalVinculado`).

### Interfaces
- `Conectavel` implementada por `ConexaoSatelital`: define o contrato de conectividade via satélite;
- `Notificavel` implementada por `AlertaEmergencia`: define o contrato de envio de notificações de emergência.

### Sobrescrita de Métodos (@Override)
- `Medico.atenderPaciente()`: personaliza o atendimento com dados do médico e verificação de grupo de risco;
- `Medico.exibirDados()`: estende a exibição da superclasse com dados específicos do médico;
- `ConexaoSatelital.conectar()`, `desconectar()`, `testarConexao()`: implementam a interface `Conectavel`;
- `AlertaEmergencia.enviarNotificacao()`: implementa a interface `Notificavel`.

### Sobrecarga de Métodos
`ConsultaOnline` possui dois métodos `finalizarConsulta` com assinaturas diferentes:
- `finalizarConsulta(String observacao)` — apenas observação;
- `finalizarConsulta(String observacao, String prescricao)` — observação + prescrição.

### Encapsulamento
Todos os atributos de todas as classes são declarados como `private`, com acesso controlado por getters e setters públicos.

### Coleções
`SpaceMedService` utiliza `ArrayList` para armazenar todas as entidades em memória durante a execução.

### Scanner e Menu Interativo
`Main.java` utiliza `Scanner` para ler entradas do usuário e um menu com `switch` para direcionar as operações.

### Métodos Estáticos
`Validador` utiliza métodos estáticos que podem ser chamados sem instanciar a classe, seguindo o padrão de classe utilitária.

---

## 6. Explicação das Principais Classes

### `Main.java`
Ponto de entrada da aplicação. Responsável apenas pelo menu interativo e por chamar os serviços adequados. Não contém lógica de negócio. Inicializa a aplicação com hospitais parceiros pré-cadastrados.

### `SpaceMedService.java`
Coração do sistema. Armazena todas as entidades em listas (`ArrayList`) e oferece métodos para cadastro, busca e geração de relatórios. Quando um alerta é gerado, automaticamente notifica todos os hospitais parceiros cadastrados.

### `TriagemService.java`
Centraliza a regra de classificação de prioridade da triagem, separando essa lógica da entidade `Triagem` e facilitando futuras alterações na regra de negócio.

### `ProfissionalSaude.java`
Classe abstrata que serve como base para a hierarquia de profissionais de saúde. Define o contrato `atenderPaciente()` que deve ser implementado por todas as subclasses.

### `Medico.java`
Especialização de `ProfissionalSaude`. Implementa `atenderPaciente()` com lógica específica, incluindo verificação automática de grupo de risco do paciente.

### `ConexaoSatelital.java`
Simula a infraestrutura de conectividade via satélite. Implementa a interface `Conectavel` e controla o estado da conexão (`ativa`).

### `AlertaEmergencia.java`
Representa um alerta crítico. Implementa `Notificavel` e, ao enviar a notificação, registra automaticamente o ocorrido no prontuário do paciente.

### `ConsultaOnline.java`
Registra o atendimento médico remoto. Demonstra sobrecarga de métodos com duas versões de `finalizarConsulta()` e garante que o prontuário seja atualizado ao final de cada consulta.

### `Triagem.java`
Avalia os sintomas do paciente e classifica a urgência em quatro níveis: BAIXA, MÉDIA, ALTA ou EMERGÊNCIA, seguindo regras clínicas definidas.

### `Validador.java`
Classe utilitária com métodos estáticos para validar entradas do usuário, evitando duplicação de código de validação nas demais classes.

---

## 7. Possíveis Evoluções Futuras

1. **Persistência de dados**: Salvar e carregar dados em arquivos `.txt`, `.csv` ou banco de dados relacional (JDBC), eliminando a perda de informações ao encerrar o sistema.

2. **API REST com Spring Boot**: Expor os serviços via endpoints HTTP, permitindo integração com aplicativos móveis utilizados por agentes de saúde em campo.

3. **Módulo de exames no menu**: A classe `ExameBasico` já existe; bastaria adicionar uma opção no menu para registrar e consultar exames realizados na unidade remota.

4. **Múltiplos perfis de profissionais**: Expandir a hierarquia de `ProfissionalSaude` para incluir `Enfermeiro`, `TecnicoEnfermagem`, `AgenteSaude`, cada um com comportamentos específicos.

5. **Integração com IoT**: Receber leituras de dispositivos médicos simples (oxímetros, termômetros digitais, pressão arterial) via satélite, alimentando automaticamente a triagem.

6. **Relatórios avançados**: Análise por comunidade, tipo de doença mais frequente, tempo médio entre triagem e consulta, e efetividade dos alertas de emergência.

7. **Autenticação e controle de acesso**: Implementar login com perfis diferenciados (médico, operador de unidade, administrador), restringindo funcionalidades por perfil.

8. **Histórico de conexão satelital**: Registrar logs de conexão e desconexão das unidades remotas para monitoramento de disponibilidade.

---

## 8. Prints da Aplicação Funcionando

> **Instruções**: Execute a aplicação e capture prints das seguintes telas para incluir nesta seção:

- **Print 1**: Tela inicial com boas-vindas e menu principal
- **Print 2**: Cadastro de paciente (preenchimento e confirmação)
- **Print 3**: Cadastro de médico parceiro
- **Print 4**: Cadastro de unidade remota com conexão satelital
- **Print 5**: Triagem de paciente com resultado EMERGÊNCIA e geração de alerta
- **Print 6**: Registro de consulta online com prescrição
- **Print 7**: Exibição do prontuário digital do paciente
- **Print 8**: Relatório geral do sistema

> Substitua este bloco pelos prints reais após executar a aplicação.
