package com.example.discountcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.discountcalc.ui.theme.DiscountCalcTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiscountCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiscountCalc()
                }
            }
        }
    }
}

@Composable
fun DiscountCalc(modifier: Modifier = Modifier) {
    var amountpaid by remember{ mutableStateOf("")}
    val amount = amountpaid.toDoubleOrNull()?:0.0

    val discount = calculateDiscount(amount)
    Column(modifier = Modifier
        .statusBarsPadding()
        .padding(horizontal = 50.dp)
        .verticalScroll(rememberScrollState())
        .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.dscalc),
            modifier = Modifier
                .padding(bottom = 20.dp, top = 44.dp)
                .align(alignment = Alignment.Start)
        )
        EnterAmountField(
            value = amountpaid,
            onValueChange = {amountpaid = it },
            modifier = Modifier
            .padding(bottom = 40.dp)
            .fillMaxWidth())
        Text(
            text = stringResource(id = R.string.totaldiscount,discount),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun EnterAmountField(value:String,
                     onValueChange:(String) ->Unit,
                     modifier: Modifier = Modifier) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(stringResource(id = R.string.tspent)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier,

        )
}

fun calculateDiscount(amount: Double,discountpercent:Double = 30.0): Any {
    val discount = discountpercent/100 * amount
    return NumberFormat.getCurrencyInstance().format(discount)
}

@Preview(showBackground = true)
@Composable
fun DiscountCalcApp() {
    DiscountCalcTheme {
       DiscountCalc()
    }
}