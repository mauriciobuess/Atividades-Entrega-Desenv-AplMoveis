package com.example.jogojornada

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JogoJornada()
        }
    }
}

// ViewModel que mantém o estado do jogo para a rotação do aparelho
class JogoJornadaViewModel : ViewModel() {
    var cliques by mutableStateOf(0)
    var numeroObjetivo by mutableStateOf(Random.nextInt(1, 51))
    var imagemAtual by mutableStateOf(R.drawable.inicio_jornada)
    var desistir by mutableStateOf(false)
    var jogoConcluido by mutableStateOf(false)
    var confirmacaoDesistir by mutableStateOf(false)
}

@Composable
fun JogoJornada(viewModel: JogoJornadaViewModel = viewModel()) {
    val context = LocalContext.current as? ComponentActivity

    // Observa as variáveis do ViewModel
    val cliques by viewModel::cliques
    val numeroObjetivo by viewModel::numeroObjetivo
    val imagemAtual by viewModel::imagemAtual
    val desistir by viewModel::desistir
    val jogoConcluido by viewModel::jogoConcluido
    val confirmacaoDesistir by viewModel::confirmacaoDesistir

    //Definição de mensagens de progresso
    val mensagemProgresso = when {
        jogoConcluido -> "Parabéns, você chegou ao fim da jornada!"
        cliques >= (numeroObjetivo * 0.66) -> "Quase lá!"
        cliques >= (numeroObjetivo * 0.33) -> "Está indo bem!"
        else -> "Boa sorte na sua jornada!"
    }

    //Box da tela inteira para rodar a interface
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //Imagem de fundo do aplicativo
        Image(
            painter = painterResource(id = R.drawable.imagem_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            //Mensagens de progesso do jogo
            if (jogoConcluido) {
                Text(
                    text = "Parabéns pela Conquista!",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            } else if (!desistir && !confirmacaoDesistir) {
                Text(
                    text = mensagemProgresso,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            //Imagens de interação
            Image(
                painter = painterResource(imagemAtual),
                contentDescription = "Imagem da Jornada",
                modifier = Modifier
                    .size(300.dp)
                    .clickable {
                        if (!desistir && !jogoConcluido && !confirmacaoDesistir) {
                            viewModel.cliques++
                            atualizarImagem(viewModel.cliques, viewModel.numeroObjetivo, { viewModel.imagemAtual = it }, {
                                viewModel.jogoConcluido = true
                            })
                        }
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                jogoConcluido -> {
                    //Botão para reiniciar o jogo
                    Button(
                        onClick = {
                            viewModel.cliques = 0
                            viewModel.numeroObjetivo = Random.nextInt(1, 51)
                            viewModel.imagemAtual = R.drawable.inicio_jornada
                            viewModel.desistir = false
                            viewModel.jogoConcluido = false
                            viewModel.confirmacaoDesistir = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(200.dp, 56.dp)
                    ) {
                        Text("Reiniciar")
                    }
                }
                //Mensagem e opções caso ocorra a desistência
                desistir -> {
                    Text(
                        text = "Você desistiu. Novo jogo?",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                    Row {
                        Button(
                            //Caso o jogador desista, o ViewModel é resetado
                            onClick = {
                                viewModel.cliques = 0
                                viewModel.numeroObjetivo = Random.nextInt(1, 51)
                                viewModel.imagemAtual = R.drawable.inicio_jornada
                                viewModel.desistir = false
                                viewModel.jogoConcluido = false
                                viewModel.confirmacaoDesistir = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                            modifier = Modifier
                                .padding(8.dp)
                                .size(200.dp, 56.dp)
                        ) {
                            Text("Sim")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                context?.finish() //Finaliza o jogo
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            modifier = Modifier
                                .padding(8.dp)
                                .size(200.dp, 56.dp)
                        ) {
                            Text("Não")
                        }
                    }
                }
                //Confirmação de desistência do jogo
                confirmacaoDesistir -> {
                    Text(
                        text = "Tem certeza que deseja desistir?",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                    Row {
                        Button(
                            onClick = {
                                viewModel.desistir = true
                                viewModel.imagemAtual = R.drawable.imagem_desistencia
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                            modifier = Modifier
                                .padding(8.dp)
                                .size(200.dp, 56.dp)
                        ) {
                            Text("Sim")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                viewModel.confirmacaoDesistir = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                            modifier = Modifier
                                .padding(8.dp)
                                .size(200.dp, 56.dp)
                        ) {
                            Text("Não")
                        }
                    }
                }
                else -> {
                    Button(
                        onClick = {
                            viewModel.confirmacaoDesistir = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(200.dp, 56.dp)
                    ) {
                        Text("Desistir")
                    }
                }
            }
        }
    }
}

// Função para atualizar a imagem com base na interação do usuário, sendo um número aleatório a ser definido
fun atualizarImagem(cliques: Int, numeroObjetivo: Int, atualizarImagem: (Int) -> Unit, concluirJogo: () -> Unit) {
    val porcentagem = (cliques.toFloat() / numeroObjetivo) * 100
    when {
        porcentagem >= 100 -> {
            atualizarImagem(R.drawable.imagem_conquista)
            concluirJogo()
        }
        porcentagem >= 66 -> atualizarImagem(R.drawable.imagem_final)
        porcentagem >= 33 -> atualizarImagem(R.drawable.imagem_mediana)
        else -> atualizarImagem(R.drawable.inicio_jornada)
    }
}
