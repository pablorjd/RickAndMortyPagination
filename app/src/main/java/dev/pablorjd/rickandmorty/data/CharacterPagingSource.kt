package dev.pablorjd.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.pablorjd.rickandmorty.presentation.model.CharacterModel
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor( private val apiService: RickAndMortyApiService): PagingSource<Int, CharacterModel>(){

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getCharacters(page)
            val charcters = response.results

            val prevKey = if ( page > 0 ) page -1 else null
            val nextKey = if(response.info.next != null) page + 1 else null

            LoadResult.Page(data = charcters.map { it.toDomain() }, prevKey = prevKey, nextKey = nextKey)

        }catch (exception:IOException) {
            LoadResult.Error(exception)
        }
    }

}