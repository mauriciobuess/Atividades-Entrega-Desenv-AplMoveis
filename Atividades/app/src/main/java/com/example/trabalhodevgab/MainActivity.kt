package com.example.trabalhodevgab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.trabalhodevgab.ui.theme.TrabalhoDevGabTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabalhoDevGabTheme {
                GameScreen()
            }
        }
    }
}

@Composable
fun GameScreen() {
    // Estado para armazenar o número de cliques do usuário
    var clickCount by remember { mutableStateOf(0) }
    // Estado para definir a meta de cliques (número aleatório entre 1 e 50)
    var targetClicks by remember { mutableStateOf(Random.nextInt(1, 51)) }
    // Estado para determinar qual imagem deve ser exibida
    var currentImage by remember { mutableStateOf(R.drawable.imagem_inicial) }
    // Estado para indicar se o jogo foi interrompido pelo usuário
    var gameOver by remember { mutableStateOf(false) }
    // Estado para mostrar ou ocultar opções após a conquista
    var showOptions by remember { mutableStateOf(false) }
    // Estado para exibir o diálogo de desistência
    var showExitDialog by remember { mutableStateOf(false) }
    // Estado para exibir o diálogo de congratulações
    var showCongratulationsDialog by remember { mutableStateOf(false) }

    // Atualiza a imagem e a lógica do jogo
    if (gameOver) {
        // Exibe a imagem de desistência se o jogo foi interrompido
        currentImage = R.drawable.imagem_desistencia
    } else if (clickCount >= targetClicks) {
        // Exibe a imagem de conquista se o número de cliques alcançou a meta
        currentImage = R.drawable.imagem_conquista
        showCongratulationsDialog = true
    } else {
        // Atualiza a imagem com base no progresso do número de cliques
        val progress = clickCount.toFloat() / targetClicks
        currentImage = when {
            progress < 0.33 -> R.drawable.imagem_inicial
            progress < 0.66 -> R.drawable.imagem_mediana
            else -> R.drawable.imagem_final
        }
    }

    // Layout principal do jogo
    Column(
        modifier = Modifier
            .fillMaxSize() // Preenche todo o espaço disponível
            .padding(16.dp) // Adiciona padding ao redor do conteúdo
            .background(color = Color.White), // Define a cor de fundo como branco
        horizontalAlignment = Alignment.CenterHorizontally, // Alinha o conteúdo horizontalmente no centro
        verticalArrangement = Arrangement.Center // Alinha o conteúdo verticalmente no centro
    ) {
        // Exibe a imagem atual com base no estado do jogo
        Image(
            painter = painterResource(id = currentImage),
            contentDescription = "Jogo", // Descrição para acessibilidade
            modifier = Modifier
                .fillMaxWidth() // Ajusta a largura da imagem para preencher a largura disponível
                .height(300.dp) // Define a altura da imagem
                .padding(16.dp) // Adiciona padding ao redor da imagem
        )
        Spacer(modifier = Modifier.height(16.dp)) // Espaço entre a imagem e os botões
        if (!gameOver && !showCongratulationsDialog) {
            // Botão para incrementar o número de cliques
            Button(onClick = { clickCount++ }) {
                Text("Clique aqui")
            }
            Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os botões
            // Botão para desistir do jogo
            Button(onClick = { showExitDialog = true }) {
                Text("Desistir")
            }
        }
    }

    // Diálogo exibido quando o usuário desiste do jogo
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false }, // Fecha o diálogo ao tocar fora dele
            title = { Text("Desistir") }, // Título do diálogo
            text = { Text("Você deseja começar um novo jogo ou sair?") }, // Mensagem do diálogo
            confirmButton = {
                Button(onClick = {
                    // Reinicia o jogo
                    clickCount = 0
                    targetClicks = Random.nextInt(1, 51) // Gera um novo número aleatório para a meta
                    gameOver = false
                    showOptions = false
                    showExitDialog = false
                }) {
                    Text("Sim")
                }
            },
            dismissButton = {
                Button(onClick = { System.exit(0) }) {
                    Text("Não")
                }
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
    }

    // Diálogo exibido quando o usuário atinge a meta
    if (showCongratulationsDialog) {
        AlertDialog(
            onDismissRequest = { showCongratulationsDialog = false }, // Fecha o diálogo ao tocar fora dele
            title = { Text("Parabéns!") }, // Título do diálogo
            text = { Text("Você atingiu o objetivo com $clickCount cliques!") }, // Mensagem do diálogo
            confirmButton = {
                Button(onClick = {
                    // Reinicia o jogo
                    clickCount = 0
                    targetClicks = Random.nextInt(1, 51) // Gera um novo número aleatório para a meta
                    gameOver = false
                    showCongratulationsDialog = false
                }) {
                    Text("Novo jogo")
                }
            },
            dismissButton = {
                Button(onClick = { System.exit(0) }) {
                    Text("Sair")
                }
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrabalhoDevGabTheme {
        GameScreen()
    }
}
