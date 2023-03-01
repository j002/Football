package com.djib.football

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.djib.football.domain.model.CountryResponse
import com.djib.football.ui.component.ErrorButton
import com.djib.football.ui.component.LoadingCircular
import com.djib.football.ui.theme.FootballTheme
import com.djib.football.utils.Response
import com.djib.football.utils.Response.*
import com.djib.football.viewmodel.ListCountryViewModel

@Composable
fun ListCountryScreen(modifier: Modifier = Modifier, listCountryViewModel: ListCountryViewModel = hiltViewModel(),){


    fun launch() {
        listCountryViewModel.getAllCountries()
    }

    launch()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(val countriesResponse = listCountryViewModel.countryListState.value){
            is Loading -> {
                  LoadingCircular(modifier = Modifier.fillMaxWidth())
            }
            is Success -> {
                Log.d("RESPONSEAPI",countriesResponse.data.toString())
              if (countriesResponse.data!!.isNotEmpty()){
                  Surface(modifier =modifier.fillMaxSize()) {
                      //   CountryListItem(countryResponse = countriesResponse!!.data?.get(5)!!)
                      Text(text = "VIEW DETAIL", style = typography.caption)

                      Card(
                          modifier = Modifier
                              .padding(horizontal = 8.dp, vertical = 8.dp)
                              .fillMaxWidth(),
                          elevation = 2.dp,
                          backgroundColor = Color.White,
                          shape = RoundedCornerShape(corner = CornerSize(16.dp))

                      ) {
                          Row {
                              //   CountryImage(countryResponse)
                              Column(
                                  modifier = Modifier
                                      .padding(16.dp)
                                      .fillMaxWidth()
                                      .align(Alignment.CenterVertically)) {

                                  Text(text = countriesResponse!!.data?.get(5)!!.countryName!!, style = typography.h6)
                                  Text(text = "VIEW DETAIL", style = typography.caption)

                              }
                          }
                      }

                  }

              }
            }
            is Failure -> {
                ErrorButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.error_message),
                    onClick = { launch() }
                )
            }
        }

    }

}

@Composable
fun CountryListItem(countryResponse: CountryResponse) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            CountryImage(countryResponse)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                Text(text = countryResponse.countryName!!, style = typography.h6)
                Text(text = "VIEW DETAIL", style = typography.caption)

            }
        }
    }
}

@Composable
private fun CountryImage(countryResponse: CountryResponse) {
    Image(
        painter = rememberAsyncImagePainter(countryResponse.countryLogo),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailFragmentPreview() {
    FootballTheme {
        ListCountryScreen()
    }
}