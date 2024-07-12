package dev.pablorjd.rickandmorty.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.pablorjd.rickandmorty.presentation.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickRespository @Inject constructor(val apiService: RickAndMortyApiService) {

    companion object {
        const val MAX_ITEM = 10
        const val PREFETCH_ITEM = 3
    }

    fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
        return Pager(config = PagingConfig(pageSize = MAX_ITEM, prefetchDistance = PREFETCH_ITEM), pagingSourceFactory = {
            CharacterPagingSource(apiService)
        }).flow
    }
}