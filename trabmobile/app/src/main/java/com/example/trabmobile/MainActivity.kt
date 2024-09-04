package com.example.trabmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import android.app.Activity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleJourneyApp()
        }
    }
}

@Composable
fun SimpleJourneyApp() {
    // Estado para contar o número de cliques, preservado ao virar a tela
    var click by rememberSaveable { mutableStateOf(0) }
    // Estado para o número aleatório de cliques necessários para vencer, preservado ao virar a tela
    val randomClicksRequired = rememberSaveable { Random.nextInt(1, 51) }
    // Estado para a imagem atual exibida, preservado ao virar a tela
    var currentImage by rememberSaveable { mutableStateOf(R.drawable.inicial) }
    // Estado para determinar se o jogo acabou, preservado ao virar a tela
    var fimJogo by rememberSaveable { mutableStateOf(false) }
    // Estado para determinar se o jogador venceu, preservado ao virar a tela
    var vitoria by rememberSaveable { mutableStateOf(false) }
    // Estado para controlar a exibição da caixa de diálogo de desistência
    var showDesistirDialog by rememberSaveable { mutableStateOf(false) }
    // Estado para controlar a exibição da caixa de diálogo de jogo finalizado
    var showFinalDialog by rememberSaveable { mutableStateOf(false) }

    // Contexto para finalizar a atividade
    val activity = (LocalContext.current as? Activity)

    // Layout principal usando um Column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Exibir texto "Clique até vencer!" apenas se o jogo não acabou
        if (!fimJogo) {
            Text(text = "Clique até vencer!",
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Exibir a imagem clicável e lidar com a lógica de clique
        if (!fimJogo) {
            Image(
                painter = painterResource(id = currentImage),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .clickable {
                        click++  // Incrementa o contador de cliques
                        when {
                            click >= randomClicksRequired -> {
                                currentImage = R.drawable.fim  // Imagem de Conquista
                                fimJogo = true
                                vitoria = true
                                showFinalDialog = true // Exibe o diálogo final
                            }

                            click >= (randomClicksRequired * 2 / 3) -> currentImage =
                                R.drawable.vitoria // Imagem Final
                            click >= (randomClicksRequired / 3) -> currentImage =
                                R.drawable.mediana  // Imagem Mediana
                            else -> currentImage = R.drawable.inicial  // Imagem Inicial
                        }
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botão "Desistir" para encerrar o jogo sem vencer
            Button(onClick = {
                showDesistirDialog = true // Exibe o diálogo de desistência
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text(text = "Desistir",
                    fontSize = 20.sp
                )
            }

            if (showDesistirDialog) {
                // Caixa de diálogo para confirmar se o usuário deseja iniciar um novo jogo ou não
                AlertDialog(
                    onDismissRequest = {
                        showDesistirDialog = false // Fecha o diálogo se o usuário clicar fora dele
                    },
                    title = { Text("Desistir do jogo") },
                    text = { Text("Você quer iniciar um novo jogo?") },
                    confirmButton = {
                        TextButton(onClick = {
                            // Reinicia o jogo com uma nova quantidade de cliques necessários
                            click = 0
                            currentImage = R.drawable.inicial
                            fimJogo = false
                            vitoria = false
                            showDesistirDialog = false // Fecha o diálogo
                        }) {
                            Text("Sim")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            currentImage = R.drawable.inicial
                            fimJogo = true
                            vitoria = false
                            showDesistirDialog = false // Fecha o diálogo
                        }) {
                            Text("Não")
                        }
                    }
                )
            }
        } else {
            // Mostrar mensagem apropriada e a imagem final quando o jogo acabar
            Text(text = if (vitoria) "Você expandiu a sua mente em um nivel cósmico" else "Perdeu Playboy!",
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = if (vitoria) R.drawable.fim else R.drawable.giveup),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Botão "Novo jogo?" para reiniciar o jogo
            Button(onClick = {
                click = 0
                currentImage = R.drawable.inicial
                fimJogo = false
                vitoria = false
                showFinalDialog = false // Fecha o diálogo final
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
            ) {
                Text(text = "Novo jogo?",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

            if (showFinalDialog) {
                // Caixa de diálogo para confirmar se o usuário deseja jogar novamente ou sair
                AlertDialog(
                    onDismissRequest = {
                        showFinalDialog = false // Fecha o diálogo se o usuário clicar fora dele
                    },
                    title = { Text("Fim do jogo") },
                    text = { Text("Deseja jogar novamente?") },
                    confirmButton = {
                        TextButton(onClick = {
                            // Reinicia o jogo com uma nova quantidade de cliques necessários
                            click = 0
                            currentImage = R.drawable.inicial
                            fimJogo = false
                            vitoria = false
                            showFinalDialog = false // Fecha o diálogo
                        }) {
                            Text("Sim")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            activity?.finish() // Finaliza o aplicativo
                            showFinalDialog = false // Fecha o diálogo
                        }) {
                            Text("Não")
                        }
                    }
                )
            }
        }
    }
}