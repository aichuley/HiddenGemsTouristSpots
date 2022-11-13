import androidx.lifecycle.ViewModel
import com.example.hiddengemstouristspots.R
import com.example.hiddengemstouristspots.model.TouristSpot
import com.example.hiddengemstouristspots.model.User

class AppViewModel: ViewModel() {
    private var _user: User = User("")

    //list of tourist spots for the User
    private var _spot: MutableList<TouristSpot> = mutableListOf(
        TouristSpot(
            R.drawable.maincampus_hero,
            "UT Austin",
            "College of Texas",
            "5/5 stars",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            , "Austin"
        ),
        TouristSpot(
            R.drawable.zilker_park,
            "Zilker Metropolitan Park",
            "Sprawling green...",
            "4.8/5 stars",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            , "Austin"
        ),
        TouristSpot(
            R.drawable.capitol,
            "Texas Capitol",
            "Home to the state...",
            "4.7/5 stars",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            , "Austin"
        ),
        TouristSpot(
            R.drawable.blanton,
            "Blanton Museum of Art",
            "Art collection of...",
            "4.6/5 stars",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            , "Austin"
        ),
    )


    fun addExperience(photo: Int, name: String, summaries: List<String>, rating: String, city: String){
        //create tourist spot here
        _spot.add(TouristSpot(photo, name, summaries[0], rating, summaries[1], city))
    }

    //set a new city for the user either manually through settings or through
//the GPS coordinates
    fun setCity(newCity: String){
        _user.userCity = newCity;
    }

    fun getCurrentSpots():  MutableList<TouristSpot> {
        return _spot
    }


    //update spot main list to only have tourist spots of a certain city
    fun getFiltered(touristCity: String){
        val listOfCitySpots: MutableList<TouristSpot> = mutableListOf()
        for(city in _spot){
            if(city.city.equals(touristCity)){
                listOfCitySpots.add(city);
            }
        }

        _spot = listOfCitySpots.toMutableList()

    }
}
