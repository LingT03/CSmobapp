package edu.msudenver.cs3013.project03
/** Overpass class to handle response for Map. From project boilerplate code
 * */
data class OverpassResponse(
    val elements: List<OverpassElement>
)

//define location using latitude and longitude
data class OverpassElement(
    val id: Long,
    val lat: Double,
    val lon: Double,
    val tags: OverpassTags
)

//define tag of what to search for
data class OverpassTags(
    val name: String,
    val amenity: String
)