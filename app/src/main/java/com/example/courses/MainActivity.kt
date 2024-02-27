package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    companion object {
        val ImageWithHeight = 68.dp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SectionApp(
                        modifier = Modifier.padding(
                            dimensionResource(id = R.dimen.padding_small)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SectionApp(modifier: Modifier = Modifier) {
    CoursesTheme {
        SectionCardList(topics = DataSource.topics)
    }
}

@Preview(showBackground = true)
@Composable
fun SectionApptPreview() {
    CoursesTheme {
        SectionCardList(topics = DataSource.topics)
    }
}

@Composable
fun SectionCardList(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
//        columns = GridCells.Adaptive(minSize = 190.dp),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(topics) {
            SectionCard(topic = it)
        }

        item(span = {
            // LazyGridItemSpanScope:
            // maxLineSpan
            GridItemSpan(maxLineSpan)
        }) {
            SectionCard(Topic(R.string.architecture, 58, R.drawable.architecture))
        }
    }
}

@Composable
fun SectionCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row(
            modifier = Modifier
                .height(MainActivity.ImageWithHeight)
        ) {
            Box {
                Image(
                    painter = painterResource(id = topic.imageResourceId),
                    contentDescription = stringResource(id = topic.stringResourceId),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(
                            width = MainActivity.ImageWithHeight,
                            height = MainActivity.ImageWithHeight
                        )
                        .aspectRatio(1f)
                )
                Icon(Icons.Filled.Check, contentDescription = "Check mark")
            }
//            Image(
//                painter = painterResource(id = topic.imageResourceId),
//                contentDescription = stringResource(id = topic.stringResourceId),
//                contentScale = ContentScale.Crop,
//                modifier = modifier
//                    .size(
//                        width = MainActivity.ImageWithHeight,
//                        height = MainActivity.ImageWithHeight
//                    )
//                    .aspectRatio(1f)
//            )
            Column {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        top = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_small)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = "availableCources",
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
                    )
                    Text(
                        text = topic.availableCources.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SectionCardPreview() {
    CoursesTheme {
        SectionCard(Topic(R.string.architecture, 58, R.drawable.architecture))
    }
}

@Preview(showBackground = true)
@Composable
fun SectionCardPreviewOfGoogle() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionCard(Topic(R.string.architecture, 58, R.drawable.architecture))
    }
}