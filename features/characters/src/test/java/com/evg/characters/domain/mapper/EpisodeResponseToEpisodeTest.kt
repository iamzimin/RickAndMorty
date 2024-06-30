package com.evg.characters.domain.mapper

import com.evg.characters.utils.MockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EpisodeResponseToEpisodeTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 3, 100])
    fun `test EpisodeResponse to Episode conversion`(episodeId: Int) {
        // Given
        val expected = MockFactory.createMockEpisode(id = episodeId)

        // When
        val actual = MockFactory.createMockEpisodeResponse(id = episodeId).toEpisode()

        // Then
        Assertions.assertEquals(actual, expected)
    }
}