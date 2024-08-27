# Atividades-Entrega-Desenv-AplMoveis

Esta área é destinada à entrega de atividades participativas ou avaliativas.

Passos Iniciais: Faça um fork do repositório e crie uma branch com o seu nome seguido de um "-" concatenado com a identificação da atividade.
Entrega: Adicione seus arquivos na pasta Atividades do seu fork e envie um pull request com suas alterações para o repositório principal.


# Instruções:

Fork do Repositório:

- Acesse o repositório Atividades-Entrega-Desenv-AplMoveis.
- Clique no botão "Fork" no canto superior direito.
  - Isso fará com que o repositório seja copiado para sua conta GitHub.

Clone o Repositório Forkado:

- Clone o repositório forkado para o seu ambiente local, substituindo "seu-usuario" pelo seu nome de usuário do GitHub:
- Exemplo:
   git clone https://github.com/seu-usuario/Desenvolvimento_Aplicativos_Moveis.git

- Navegue até o diretório do repositório clonado, exemplo:
  cd Atividades-Entrega-Desenv-AplMoveis

Adicionar Trabalho:

- Navegue até a pasta Atividades-Entrega-Desenv-AplMoveis no diretório do repositório clonado.
- Adicione seus arquivos de trabalho na pasta Atividades.

Commit e Push:

- Adicione os arquivos ao estágio de commit, exemplo:
  git add .
- Faça um commit das suas alterações com uma mensagem descritiva, exemplo:
  git commit -m "Adicionando trabalho de [Seu Nome]"
- Envie suas alterações para o repositório forkado no GitHub:
  git push origin main

Abrir um Pull Request:

- Acesse o repositório forkado no GitHub.
- Navegue até a aba "Pull requests".
- Clique no botão "New pull request".
- Certifique-se de que a base do pull request está configurada para o repositório original e a branch main do seu fork.
- Preencha o título e a descrição do pull request e clique em "Create pull request".

# Dicas Adicionais

- Verifique se a Branch está Atualizada:
  Antes de fazer o commit, é uma boa prática garantir que seu repositório forkado esteja atualizado com as últimas alterações do repositório original. Você pode fazer isso com os seguintes comandos:
  
  git fetch upstream
  git merge upstream/main

- Utilize Branches para Novas Funcionalidades: Se estiver trabalhando em várias tarefas, considere criar branches diferentes para cada uma delas. Por exemplo:

  git checkout -b nome-da-sua-branch

- Mensagens de Commit: Seja claro e descritivo nas suas mensagens de commit para facilitar a revisão do seu trabalho.
