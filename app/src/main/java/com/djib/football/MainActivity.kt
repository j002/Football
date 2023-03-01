package com.djib.football

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.djib.football.navigation.SetupNavGraph
import com.djib.football.ui.theme.FootballTheme
import com.djib.football.utils.Response
import com.djib.football.viewmodel.ListCountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            FootballTheme {

                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }


}

@Composable
fun MainView() {
  //  val listCountryViewModel : ListCountryViewModel = hiltViewModel()
    val listCountryViewModel = hiltViewModel<ListCountryViewModel>()

    fun launch() {
        listCountryViewModel.getAllCountries()
    }

    launch()
    Surface(
        color = MaterialTheme.colors.background
    ) {
        when(val countriesResponse = listCountryViewModel.countryListState.value){
            is Response.Loading -> {
              /*  LoadingCircular(
                    modifier = Modifier.fillMaxWidth()
                )*/
            }
            is Response.Success -> {
                Log.d("djdjdddjdjjd",countriesResponse.data.toString())
            }
            is Response.Failure -> {

                Log.d("djdjdddjdjjd",countriesResponse.toString())
               /* ErrorButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.error_message),
                    onClick = {
                        launch()
                    }
                )*/
            }
        }
    }
}

