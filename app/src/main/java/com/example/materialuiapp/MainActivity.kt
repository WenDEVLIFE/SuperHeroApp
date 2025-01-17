package com.example.materialuiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.materialuiapp.data.MemeHeroes
import com.example.materialuiapp.data.memeHeroes
import com.example.materialuiapp.ui.theme.MaterialTheme
import com.example.materialuiapp.ui.theme.SuperheroesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {

                // Initialize the MemeApp
                MemeApp()
            }
        }
    }
}

@Composable
fun MemeItems(
    memeHeroes: MemeHeroes,
    modifier: Modifier = Modifier
){

    // use remember to keep track of the state of the button
    var expanded by remember { mutableStateOf(false) }

 // This is our card together withs its column, animation, and material components.
    Card(
        modifier = modifier

    ){

                // This is the column that will hold the meme heroes
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ){

                        // This is the row that will hold the meme heroes
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ){

                // This is the meme icons
                MemeIcons(memeHeroes.imageResourceId)

                // This is the meme description
                MemeDescription(memeHeroes.name , memeHeroes.description)

                // This is the meme button
                Spacer(modifier = Modifier.weight(1f))

                // This is the meme button
                MemeButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )

            }
        }
    }

}

@Composable
fun MemeButton( expanded: Boolean,
                onClick: () -> Unit,
                modifier: Modifier = Modifier) {

    // This is the button that will expand the meme heroes
    IconButton(
        onClick = onClick,
        modifier = modifier
    ){
        // This is the icon that will be displayed
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary
        )
    }

}

@Composable
fun MemeDescription(@StringRes name: Int,
                    description: Int,
                    modifier: Modifier = Modifier
) {

    // Assign column
    Column(
        modifier = modifier
    ){

        // This is name of the meme hero
        Text(
            text = stringResource(name),
            style = androidx.compose.material3.MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )

        // This is the description of the meme hero
        Text(
            text = stringResource(description),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
    }

}

@Composable
fun MemeIcons(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {

    // This is for our icons and material component
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(androidx.compose.material3.MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(

                // This is the title of the app
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.logo_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.cardo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = androidx.compose.material3.MaterialTheme.typography.displaySmall
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MemeApp(){
    Scaffold(

        // This is our topbar
        topBar = {

            // Call the meme top bar
            MemeTopBar()
        }
    ) { it ->

        // Use Lazy Column to display the meme heroes
        LazyColumn(contentPadding = it) {

            // Display all the meme heroes
            items(memeHeroes) {

                // call the Meme items composable
                MemeItems(
                    memeHeroes = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewMemeItems(){
    SuperheroesTheme(darkTheme = false) {
        MemeApp()

    }
}


@Preview
@Composable
fun PreviewMemeItemsDark(){
    SuperheroesTheme(darkTheme = true) {
        MemeApp()
    }
}
