package com.example.ui.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.OpaqueDialogBg
import com.example.data.model.Member

private val BrandLime = Color(0xFFDDF247)
private val TranslucentCardBg = Color(0x3D1F222C)
private val TranslucentCardBorder = Color(0x19FFFFFF)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddTransactionDialog(
    members: List<Member>,
    onDismiss: () -> Unit,
    onSave: (Double, String, String, Int, Boolean, Boolean) -> Unit,
    currencySymbol: String = "$",
    initialIsExpense: Boolean = true
) {
    var isExpense by remember { mutableStateOf(initialIsExpense) }
    var amountStr by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Food") }
    var description by remember { mutableStateOf("") }
    var chosenMemberId by remember { mutableStateOf(members.firstOrNull()?.id ?: 1) }
    var isShared by remember { mutableStateOf(false) }

    val categories = listOf("Food", "Rent", "Salary", "Shopping", "Entertainment", "Utilities", "Investment", "Other")

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = OpaqueDialogBg),
            border = BorderStroke(1.dp, TranslucentCardBorder)
        ) {
            Column(modifier = Modifier.padding(20.dp).fillMaxWidth().verticalScroll(rememberScrollState())) {
                Text("Record Transaction", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(14.dp))
                Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(Color(0x1AFFFFFF))) {
                    Button(
                        onClick = { isExpense = true },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isExpense) Color(0xFFF87171) else Color.Transparent)
                    ) {
                        Text("Expense", color = if (isExpense) Color.White else Color.Gray)
                    }
                    Button(
                        onClick = { isExpense = false },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = if (!isExpense) Color(0xFF10B981) else Color.Transparent)
                    ) {
                        Text("Income", color = if (!isExpense) Color.White else Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(
                    value = amountStr,
                    onValueChange = { amountStr = it },
                    label = { Text("Amount ($currencySymbol)", color = Color.White) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = TranslucentCardBg,
                        unfocusedContainerColor = TranslucentCardBg,
                        focusedBorderColor = BrandLime,
                        unfocusedBorderColor = TranslucentCardBorder
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description", color = Color.White) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = TranslucentCardBg,
                        unfocusedContainerColor = TranslucentCardBg,
                        focusedBorderColor = BrandLime,
                        unfocusedBorderColor = TranslucentCardBorder
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text("Category", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    categories.forEach { cat ->
                        val active = category == cat
                        Button(
                            onClick = { category = cat },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (active) BrandLime else TranslucentCardBg,
                                contentColor = if (active) Color.Black else Color.White
                            ),
                            border = if (!active) BorderStroke(1.dp, TranslucentCardBorder) else null
                        ) {
                            Text(cat, fontSize = 11.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                Text("Assigned Member", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    members.forEach { m ->
                        val active = chosenMemberId == m.id
                        Button(
                            onClick = { chosenMemberId = m.id },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (active) Color(0xFF6366F1) else TranslucentCardBg,
                                contentColor = Color.White
                            ),
                            border = if (!active) BorderStroke(1.dp, TranslucentCardBorder) else null
                        ) {
                            Text(m.name, fontSize = 11.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        val value = amountStr.toDoubleOrNull() ?: 0.0
                        if (value > 0) onSave(value, category, description, chosenMemberId, isShared, isExpense)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Record Transaction")
                }
            }
        }
    }
}
