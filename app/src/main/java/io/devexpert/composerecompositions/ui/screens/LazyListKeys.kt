package io.devexpert.composerecompositions.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.devexpert.composerecompositions.data.Movie
import io.devexpert.composerecompositions.data.buildMovies
import io.devexpert.composerecompositions.ui.screens.shared.RefreshAction
import io.devexpert.composerecompositions.ui.screens.shared.Screen
import io.devexpert.composerecompositions.ui.screens.shared.TopBar

@Immutable
class MovieListWrapper(val list: List<Movie>)

@Composable
fun LazyListKeys() {
    Screen {
        var movies by remember { mutableStateOf(MovieListWrapper(buildMovies(4))) }
        Scaffold(
            topBar = {
                TopBar("LazyList Keys") {
                    RefreshAction { movies = MovieListWrapper(movies.list.shuffled()) }
                }
            },
        ) { padding ->
            MoviesList(padding, movies)
        }
    }
}

@Composable
private fun MoviesList(
    padding: PaddingValues,
    movies: MovieListWrapper
) {
    LazyColumn(
        contentPadding = padding
    ) {
        items(movies.list) {
            MovieItem(movie = it)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    ListItem(
        headlineContent = { Text(movie.title) },
        supportingContent = { Text(movie.description) },
        leadingContent = {
            AsyncImage(
                model = movie.image,
                contentDescription = movie.title,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
            )
        }
    )
}
