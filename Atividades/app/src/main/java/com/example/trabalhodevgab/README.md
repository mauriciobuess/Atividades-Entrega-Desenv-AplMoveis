# Projeto de Jogo de Jornada Pessoal

Este é um projeto de aplicativo Android desenvolvido com Kotlin e Jetpack Compose. O aplicativo simula uma jornada pessoal com diferentes estágios e imagens que representam o progresso. O usuário interage com o aplicativo clicando em um botão para avançar na jornada ou desistir.

## Estrutura do Projeto

- **MainActivity.kt**: Contém a lógica principal do aplicativo e a interface do usuário.
- **drawable/**: Contém os arquivos de imagem usados no aplicativo.
- **README.md**: Este arquivo, que fornece informações sobre o projeto.

## Funcionalidade

O aplicativo possui as seguintes funcionalidades:

1. **Imagens e Estágios**:
    - **Imagem Inicial**: Representa o início da jornada.
    - **Imagem Mediana**: Representa o progresso intermediário.
    - **Imagem Final**: Indica que o usuário está próximo da conquista.
    - **Imagem de Conquista**: Celebra a conquista completa.
    - **Imagem de Desistência**: Mostra quando o usuário opta por desistir.

2. **Lógica do Jogo**:
    - O usuário deve clicar no botão para avançar na jornada.
    - O número de cliques necessários para alcançar a conquista é definido aleatoriamente entre 1 e 50.
    - As imagens mudam conforme a porcentagem de progresso.

3. **Opção de Desistência**:
    - O usuário pode desistir a qualquer momento, levando à exibição da Imagem de Desistência e um diálogo para decidir se deseja iniciar um novo jogo ou sair.

4. **Mensagem de Congratulações**:
    - Quando o objetivo é alcançado, uma mensagem de congratulações é exibida com a opção de jogar novamente ou sair.

## Instruções de Execução

### Pré-requisitos

- **Android Studio**: Certifique-se de ter o Android Studio instalado. Você pode baixá-lo em [Android Studio](https://developer.android.com/studio).

### Clonar o Repositório

Clone o repositório para sua máquina local:

```sh
git clone https://github.com/gabriels03t/Atividades-Entrega-Desenv-AplMoveis.git
