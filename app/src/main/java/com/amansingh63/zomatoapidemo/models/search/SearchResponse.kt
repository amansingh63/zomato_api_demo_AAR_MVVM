package com.amansingh63.zomatoapidemo.models.search

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class SearchResponse(
    val results_found: Int = -1,
    val results_start: Int = -1,
    val results_shown: Int = -1,
    val restaurants: List<Restaurants> = emptyList()
) : Parcelable

@Parcelize
@Serializable
data class Restaurants(
    val restaurant: Restaurant = Restaurant()
) : Parcelable

@Entity
@Parcelize
@Serializable
data class Restaurant @JvmOverloads constructor(
    @Ignore val R: R = R(),
    @Ignore val apikey: String = "",
    @PrimaryKey var id: Int = -1,
    var name: String = "",
    @Ignore val url: String = "",
    @Ignore val location: Location = Location(),
    @Ignore val switch_to_order_menu: Int = -1,
    var cuisines: String = "",
    @Ignore val timings: String = "",
    @Ignore val average_cost_for_two: Int = -1,
    @Ignore val price_range: Int = -1,
    @Ignore val currency: String = "",
    @Ignore val highlights: List<String> = emptyList(),
    @Ignore val offers: List<String> = emptyList(),
    @Ignore val opentable_support: Int = -1,
    @Ignore val is_zomato_book_res: Int = -1,
    @Ignore val mezzo_provider: String = "",
    @Ignore val is_book_form_web_view: Int = -1,
    @Ignore val book_form_web_view_url: String = "",
    @Ignore val book_again_url: String = "",
    @Ignore val thumb: String = "",
    @Ignore val user_rating: UserRating = UserRating(),
    @Ignore val all_reviews_count: Int = -1,
    @Ignore val photos_url: String = "",
    @Ignore val photo_count: Int = -1,
    @Ignore val photos: List<Photos> = emptyList(),
    @Ignore val menu_url: String = "",
    var featured_image: String = "",
    @Ignore val has_online_delivery: Int = -1,
    @Ignore val is_delivering_now: Int = -1,
    @Ignore val store_type: String = "",
    @Ignore val include_bogo_offers: Boolean = false,
    @Ignore val deeplink: String = "",
    @Ignore val is_table_reservation_supported: Int = -1,
    @Ignore val has_table_booking: Int = -1,
    @Ignore val events_url: String = "",
    @Ignore val phone_numbers: String = "",
    @Ignore val all_reviews: AllReviews = AllReviews(),
    @Ignore val establishment: List<String> = emptyList(),
    @Ignore val establishment_types: List<String> = emptyList(),
    var isFavourite: Boolean = false
) : Parcelable

@Parcelize
@Serializable
data class R(
    val has_menu_status: HasMenuStatus = HasMenuStatus(),
    val res_id: Int = -1
) : Parcelable

@Parcelize
@Serializable
data class AllReviews(
    val reviews: List<AllReviews> = emptyList()
) : Parcelable

@Parcelize
@Serializable
data class BgColor(
    val type: String = "",
    val tint: Int = -1
) : Parcelable

@Parcelize
@Serializable
data class HasMenuStatus(
    val delivery: Int = -1,
    val takeaway: Int = -1
) : Parcelable

@Parcelize
@Serializable
data class Location(
    val address: String = "",
    val locality: String = "",
    val city: String = "",
    val city_id: Int = -1,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val zipcode: String = "",
    val country_id: Int = -1,
    val locality_verbose: String = ""
) : Parcelable

@Parcelize
@Serializable
data class Title(
    val text: String = ""
) : Parcelable

@Parcelize
@Serializable
data class User(
    val name: String = "",
    val zomato_handle: String = "",
    val foodie_level: String = "",
    val foodie_level_num: Int = 0,
    val foodie_color: String = "",
    val profile_url: String = "",
    val profile_image: String = "",
    val profile_deeplink: String = ""
) : Parcelable

@Parcelize
@Serializable
data class UserRating(
    val aggregate_rating: Float = 0f,
    val rating_text: String = "",
    val rating_color: String = "",
    val rating_obj: RatingObj = RatingObj(),
    val votes: Int = -1,
    val custom_rating_text: String = "",
    val custom_rating_text_background: String = "",
    val rating_tool_tip: String = ""
) : Parcelable

@Parcelize
@Serializable
data class Photos(
    val photo: Photo = Photo()
) : Parcelable

@Parcelize
@Serializable
data class RatingObj(
    val title: Title = Title(),
    val bg_color: BgColor = BgColor()
) : Parcelable


@Parcelize
@Serializable
data class Photo(
    val id: String = "",
    val url: String = "",
    val thumb_url: String = "",
    val user: User = User(),
    val res_id: Int = -1,
    val caption: String = "",
    val timestamp: Int = -1,
    val friendly_time: String = "",
    val width: Int = -1,
    val height: Int = -1
) : Parcelable