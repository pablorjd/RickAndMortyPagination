package dev.pablorjd.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pablorjd.rickandmorty.data.RickRespository
import dev.pablorjd.rickandmorty.presentation.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RickListViewModel @Inject constructor( val rickRespository: RickRespository ) : ViewModel() {

    val characters:Flow<PagingData<CharacterModel>> = rickRespository.getAllCharacters()

}