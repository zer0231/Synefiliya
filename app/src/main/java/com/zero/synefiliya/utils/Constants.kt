package com.zero.synefiliya.utils

class Constants {

    companion object {
        const val BASE_URL = "https://yts.mx/api/v2/"
        const val LIST = "list_movies.json?limit=30&page="
        const val DETAIL = "movie_details.json?with_images=true&movie_id=" //REQUIRES MOVIE ID
        const val SUGGESTIONS = "movie_suggestions.json?movie_id=" //REQUIRES MOVIE ID

        /**--SHARED PREFERENCE CONSTANTS--**/
        const val USER_DATA = "user_data"

        const val MOVIE_DATABASE = "movies_db"
        const val MOVIE_COLUMN = "movies"
        const val ANIMATION_URL = "https://assets1.lottiefiles.com/packages/lf20_b88nh30c.json"
    }
}