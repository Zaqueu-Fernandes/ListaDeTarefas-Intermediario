# Lista de Tarefas — Projeto Final (Módulo Intermediário)

**Nome do aluno:** Zaqueu Fernandes Alves
**Matrícula:** 333333
**Data de entrega:** 18/09/2026
**Forma de entrega:** GitHub

## Justificativa da escolha do tema

O tema "Lista de Tarefas" foi escolhido por ser um dos exemplos sugeridos na atividade
e por ser simples o suficiente para permitir foco total nos requisitos técnicos
obrigatórios do módulo (Jetpack Compose, Navigation Compose, Room e arquitetura
MVVM), sem a distração de regras de negócio complexas.

## Descrição do funcionamento do aplicativo

O app possui 2 telas, navegadas com Navigation Compose:

1. **Lista de Tarefas** (`TaskListScreen`): exibe em uma `LazyColumn` as tarefas
   salvas localmente com Room. Um botão flutuante (+) leva à tela de cadastro; tocar
   em uma tarefa da lista leva à mesma tela, agora em modo de detalhes.
2. **Cadastro ou Detalhes da Tarefa** (`TaskFormScreen`): uma única tela reaproveitada
   nos dois fluxos — quando aberta a partir do botão (+), começa vazia (cadastro);
   quando aberta a partir de um item da lista, é pré-preenchida com os dados da
   tarefa selecionada (visualização/edição). O botão "Salvar" grava os dados no Room.

### Arquitetura (MVVM)

- **Model** (`data/`): `TaskEntity` + `TaskDao` + `AppDatabase` (Room) cuidam da
  persistência local; `TaskRepository` expõe essas operações de forma simples para
  o restante do app.
- **ViewModel** (`ui/viewmodel/TaskViewModel.kt`): expõe a lista de tarefas como
  `StateFlow` e a ação `saveTask`/`getTaskById` para a UI, sem conhecer detalhes do
  Room.
- **View** (`ui/screens/`): telas Compose que apenas observam o estado do
  ViewModel e disparam eventos — nenhuma lógica de dados na UI.

### Requisitos técnicos atendidos

| Requisito | Onde |
|---|---|
| Kotlin | todo o projeto |
| Pelo menos 2 telas em Jetpack Compose | `TaskListScreen`, `TaskFormScreen` |
| Navegação com Navigation Compose | `navigation/NavGraph.kt` |
| Room Database com pelo menos 1 entidade | `data/local/` (`TaskEntity`, `TaskDao`, `AppDatabase`) |
| Arquitetura MVVM com separação em camadas | pacotes `data/`, `ui/viewmodel/`, `ui/screens/` |

## Como abrir o projeto

1. Abra a pasta `ListaDeTarefas-Intermediario` no Android Studio (Open > selecione
   esta pasta).
2. Aguarde o Gradle sincronizar as dependências (é necessário estar conectado à
   internet na primeira vez, apenas para baixar as bibliotecas).
3. Rode em um emulador ou dispositivo físico com Android 7.0 (API 24) ou superior.

## Observações

Este projeto é a versão simplificada, contendo apenas os requisitos obrigatórios do
Módulo Intermediário (sem consumo de API). A versão completa, com Retrofit e uma
terceira tela, está no repositório do Módulo Avançado.
