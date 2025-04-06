package com.example.calculadora.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadora.ui.theme.CalculadoraTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var ope by remember { mutableStateOf("+") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculadora", style = MaterialTheme.typography.titleLarge, modifier = Modifier) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = num1,
                    onValueChange = { num1 = it },
                    label = { Text("Digite o 1° Valor") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = num2,
                    onValueChange = { num2 = it },
                    label = { Text("Digite o 2° Valor") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(onClick = { ope = "+" }, modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.extraLarge) { Text("+", style = MaterialTheme.typography.titleLarge) }
                    Button(onClick = { ope = "-" }, modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.extraLarge) { Text("-", style = MaterialTheme.typography.titleLarge) }
                    Button(onClick = { ope = "*" }, modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.extraLarge) { Text("x", style = MaterialTheme.typography.titleLarge) }
                    Button(onClick = { ope = "/" }, modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.extraLarge) { Text("÷", style = MaterialTheme.typography.titleLarge) }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = result,
                    onValueChange = {},
                    label = { Text("Resultado") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val n1 = num1.toDoubleOrNull()
                        val n2 = num2.toDoubleOrNull()
                        result = when {
                            n1 == null || n2 == null -> "Entrada inválida"
                            ope == "/" && n2 == 0.0 -> "Não foi possivel dividir o número!"
                            else -> {
                                val calc = when (ope) {
                                    "+" -> n1 + n2
                                    "-" -> n1 - n2
                                    "*" -> n1 * n2
                                    "/" -> n1 / n2
                                    else -> return@Button
                                }
                                calc.toString()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Calcular", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimary)
                }
                OutlinedButton(onClick = {
                    num1 = ""
                    num2 = ""
                    result = ""
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                    shape = MaterialTheme.shapes.medium) {
                    Text("Limpar", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculadoraTheme {
        CalculatorScreen()
    }
}
