package com.atakanmadanoglu.musicapp.presentation.music_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.atakanmadanoglu.musicapp.data.service.remote.dto.Genre
import com.atakanmadanoglu.musicapp.presentation.MusicViewModel
import com.atakanmadanoglu.musicapp.presentation.navigation.BottomNavItem
import com.atakanmadanoglu.musicapp.presentation.navigation.Screen

@Composable
fun MusicCategoriesRoute(
    currentRoute: String,
    onCardClicked: (itemId: Int) -> Unit,
    musicCategoriesViewModel: MusicViewModel = hiltViewModel()
) {
    val state by musicCategoriesViewModel.genres.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        musicCategoriesViewModel.getGenres()
    }
    MusicCategoriesScreen(
        genres = state,
        onCardClicked = { onCardClicked(it) },
        currentRoute = currentRoute,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MusicCategoriesScreen(
    modifier: Modifier = Modifier,
    genres: List<Genre>,
    onCardClicked: (itemId: Int) -> Unit,
    currentRoute: String // via navbackstackentry of navController.currentBackStackEntryAsState()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            PageTitleTopAppBar(title = stringResource(id = Screen.MusicCategoryScreen.titleId))
        },
        bottomBar = {
            BottomNavigationBar(
                items = listOf(BottomNavItem.Category, BottomNavItem.Favorites),
                onItemClicked = {},
                currentRoute = currentRoute,
            )
        }
    ) {
        MusicCategoryVerticalGridList(
            genres = genres,
            contentPaddingValues = it,
            onCardClicked = { id -> onCardClicked(id) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageTitleTopAppBar(
    title: String
) {
    val color = Color(0xFFE0E0E0)
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = color
        ),
        title = { Text(text = title) },
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    onItemClicked: (BottomNavItem) -> Unit,
    currentRoute: String
) {
    //val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier
            .height(60.dp)
            .border(width = 1.dp, color = Color.Gray.copy(0.2f)),
        containerColor = Color.White
    ) {
        items.forEach { item ->
            //val selected = (item.route == backStackEntry.value?.destination?.route)
            val selected = (item.route == currentRoute)
            val iconId = if (selected) {
                item.iconId
            } else {
                item.secondaryIconId
            }

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = iconId),
                        contentDescription = stringResource(id = item.contentDescription)
                    )
                },
                selected = selected,
                onClick = { onItemClicked(item) }
            )
        }
    }
}

@Composable
fun MusicCategoryVerticalGridList(
    genres: List<Genre>,
    contentPaddingValues: PaddingValues,
    onCardClicked: (itemId: Int) -> Unit
) {
    LazyVerticalGrid(
        contentPadding = contentPaddingValues,
        columns = GridCells.Fixed(2)
    ) {
        items(
            items = genres,
            key = { it.id }
        ) {genre ->
            CardView(
                title = genre.name,
                imageUrl = genre.picture,
                onItemClicked = {
                    onCardClicked(genre.id)
                }
            )
        }
    }
}

@Composable
fun CardView(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    onItemClicked: () -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .height(200.dp)
            .padding(10.dp)
            .clickable { onItemClicked() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            // To make the text more readable
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )

            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}