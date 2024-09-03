package com.barril.pokedexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barril.pokedexapp.ui.theme.PokeDexAppTheme
import java.util.EnumSet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<DummyViewModel>()

        enableEdgeToEdge()
        setContent {
            PokeDexAppTheme {
                MainApp(/*viewModel = viewModel*/)
            }
        }
    }
}

@Preview
@Composable
fun MainAppPreview() {
    PokeDexAppTheme {
        MainApp()
    }
}

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home_destination, Icons.Rounded.Home, R.string.home_description),
    FAVORITES(R.string.favorites_destination, Icons.Default.Star, R.string.favorites_description),
    SETTINGS(R.string.settings_destination, Icons.Rounded.Settings, R.string.settings_description),
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar()
        },
        ) { innerPadding ->
            PokemonColumnList(Modifier.padding(innerPadding))
        }
}

@Composable
fun PokemonColumnList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            val types = PokemonType.entries
            for(i in 1..2) {
                val random = (0..types.size).random()

                PokemonCard(
                    pokemonName = "Bulbasaur",
                    pokemonType = EnumSet.of(PokemonType.GRASS),
                )
            }
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row() {
        SearchBar()
        Button(onClick = { /*TODO*/ }) {
            Icon(painterResource(id = R.drawable.filter_icon), contentDescription = "Filtros")
        }
    }
}

@Composable
fun OutlinedText(
    text: String,
    color: Color = MaterialTheme.colorScheme.surface,
    fontWeight: FontWeight = FontWeight.Bold,
    fontFamily: FontFamily? = null,
    outlineColor: Color = Color(0xFF9CB2D0),
    outlineMiter: Float = 10f,
    outlineWidth: Float = 10f,
    fontSize: TextUnit = 86.sp,
    modifier: Modifier = Modifier
) {
    val superLargeStyle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        letterSpacing = fontSize/10,
        lineHeight = 20.sp,
        textAlign = TextAlign.Center,
        color = color
    )
    val superLargeStyleOutline = superLargeStyle.copy(
        color = outlineColor,
        drawStyle = Stroke(
            miter = outlineMiter,
            width = outlineWidth,
            join = StrokeJoin.Miter
        ),
    )

    Text(
        text = text,
        style = superLargeStyleOutline,
        modifier = modifier
    )

    Text(
        text = text,
        style = superLargeStyle,
        modifier = modifier
    )
}


@Preview
@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        NavigationBarItem(
            icon = {
                Icon(AppDestinations.HOME.icon, contentDescription = stringResource(AppDestinations.HOME.contentDescription))
            },
            label = {
                Text(stringResource(AppDestinations.HOME.label))
            },
            selected = false,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = {
                Icon(AppDestinations.FAVORITES.icon, contentDescription = stringResource(AppDestinations.FAVORITES.contentDescription))
            },
            label = {
                Text(stringResource(AppDestinations.FAVORITES.label))
            },
            selected = false,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = {
                Icon(AppDestinations.SETTINGS.icon, contentDescription = stringResource(AppDestinations.SETTINGS.contentDescription))
            },
            label = {
                Text(stringResource(AppDestinations.SETTINGS.label))
            },
            selected = false,
            onClick = { /* TODO */ }
        )
    }
}

enum class PokemonType {
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    // ...
}

@Preview
@Composable
fun PokemonCardPreview() {
    PokemonCard("Bulbasaur", EnumSet.of(PokemonType.GRASS))
}

@Composable
fun PokemonCard(pokemonName: String, pokemonType: EnumSet<PokemonType>, modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.balbasaur), // placeholder
                contentDescription = null,
            ) // aqui vai ter a imagem do pokemon
            Column {
                Text(
                    text = pokemonName,
                )
                when {
                    pokemonType.contains(PokemonType.FIRE) -> {
                        Icon(Icons.Default.Clear, "")
                    }
                    pokemonType.contains(PokemonType.WATER) -> {
                        Icon(Icons.Default.Clear, "")
                    }
                    pokemonType.contains(PokemonType.GRASS) -> {
                        GrassTypeCard()
                    }
                    pokemonType.contains(PokemonType.ELECTRIC) -> {
                        Icon(Icons.Default.Clear, "")
                    }
                }
            }
            // TODO: colocar propriedades de acessibilidade
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Favoritos",
                tint = Color.Yellow,
                modifier = modifier.clickable {  },
            )
        }

    }

}

@Composable
fun GrassTypeCard() {
    PokemonTypeCard(
        typeName = "GRASS",
        backgroundColor = Color(0xFF7ED956)
    )
}

@Composable
fun PokemonTypeCard(
    typeName: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val borderSize = 1.5.dp

    // TODO: refatorar essa parte como: MultiOutlinedCard
    // primeira borda
    OutlinedCard(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(borderSize / 4, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        modifier = modifier
    ) {
        // segunda borda
        Surface(
            border = BorderStroke(borderSize, Color.White),
            shape = MaterialTheme.shapes.medium,
            color = backgroundColor,
            modifier = modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                // TODO: arrumar uma maneira de por um outline preto na letra
                OutlinedText(
                    text = typeName,
                    color = Color.White,
                    outlineColor = Color.Black,
                    outlineWidth = 3f,
                    outlineMiter = 3f,
                    fontWeight = FontWeight.W800,
                    modifier = modifier.padding(4.dp),
                    fontSize = 5.sp
                )
            }
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = { /* TODO */ },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(),
        placeholder = {
            Text("Pesquisar")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}
